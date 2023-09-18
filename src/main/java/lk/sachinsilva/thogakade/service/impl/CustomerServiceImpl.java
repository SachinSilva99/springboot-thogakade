package lk.sachinsilva.thogakade.service.impl;


import lk.sachinsilva.thogakade.dto.CustomerDTO;
import lk.sachinsilva.thogakade.entity.Customer;
import lk.sachinsilva.thogakade.repo.CustomerRepo;
import lk.sachinsilva.thogakade.service.CustomerService;
import lk.sachinsilva.thogakade.service.exception.DuplicateException;
import lk.sachinsilva.thogakade.service.exception.NotFoundException;
import lk.sachinsilva.thogakade.util.Mapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final Mapper mapper = new Mapper();

    public CustomerServiceImpl(CustomerRepo customerRep) {
        this.customerRepo = customerRep;
    }

    @Override
    public String save(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
            throw new DuplicateException(customerDTO.getId() + " customer already exists");
        }
        return customerRepo.save(mapper.toCustomer(customerDTO)).getId();
    }


    @Override
    public List<String> loadCustomerIds() {
        return customerRepo.findAll().stream().map(Customer::getId).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(mapper::toCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException, ConstraintViolationException {
        if (!customerRepo.existsById(id)) {
            throw new NotFoundException(id + " customer not found");
        }
        customerRepo.deleteById(id);
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        String id = customerDTO.getId();
        Optional<Customer> byId = customerRepo.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException(id + " customer not found");
        }
        Customer customer = byId.get();
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
    }

    @Override
    public CustomerDTO get(String id) throws NotFoundException {
        Optional<Customer> byId = customerRepo.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException(id + " customer not found");
        }
        return mapper.toCustomerDTO(byId.get());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String data) {
        return customerRepo.findCustomersByNameContainingOrAddressContaining(data, data).stream().map(mapper::toCustomerDTO).collect(Collectors.toList());
    }
}