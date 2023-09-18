package lk.sachinsilva.thogakade.service;




import lk.sachinsilva.thogakade.dto.CustomerDTO;
import lk.sachinsilva.thogakade.service.exception.NotFoundException;

import java.util.List;

public interface CustomerService {
    String save(CustomerDTO customerDTO);
    List<String> loadCustomerIds();
    List<CustomerDTO>getAllCustomers();

    void delete(String id) throws Exception;

    void update(CustomerDTO customerDTO);
    CustomerDTO get(String id)throws NotFoundException;
    List<CustomerDTO> searchCustomers(String data);
}
