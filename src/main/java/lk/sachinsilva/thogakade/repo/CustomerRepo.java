package lk.sachinsilva.thogakade.repo;

import lk.sachinsilva.thogakade.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    List<Customer> findCustomersByNameContainingOrAddressContaining(String name, String address);
}
