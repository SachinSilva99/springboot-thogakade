package lk.sachinsilva.thogakade.controller;


import lk.sachinsilva.thogakade.dto.CustomerDTO;
import lk.sachinsilva.thogakade.regex.Validates;
import lk.sachinsilva.thogakade.regex.Validation;
import lk.sachinsilva.thogakade.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/*
Author : Sachin Silva
*/
@RestController
@RequestMapping("/thogakade/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final Validation validation = new Validation();

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody CustomerDTO customerDTO) {

        if (!validation.match(customerDTO.getId(), Validates.CUSTOMER_ID)) {
            return;
        }
        if (!validation.match(customerDTO.getName(), Validates.NAME)) {
            return;
        }
        if (!validation.match(customerDTO.getAddress(), Validates.ADDRESS)) {
            return;
        }
        customerService.save(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO get(@PathVariable String id) throws IOException {
        return customerService.get(id);
    }


    @PatchMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody CustomerDTO customerDTO) throws IOException {

        if (!validation.match(customerDTO.getId(), Validates.CUSTOMER_ID)) {
            return;
        }
        if (!validation.match(customerDTO.getName(), Validates.NAME)) {
            return;
        }
        if (!validation.match(customerDTO.getAddress(), Validates.ADDRESS)) {
            return;
        }
        //TODO: change service method
        customerService.update(customerDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws Exception {
        customerService.delete(id);
    }


    @GetMapping("/searches/{data}")
    private List<CustomerDTO> getAllCustomers(@PathVariable String data) {
        return customerService.searchCustomers(data);
    }

}
