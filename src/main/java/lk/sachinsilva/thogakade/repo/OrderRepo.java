package lk.sachinsilva.thogakade.repo;

import lk.sachinsilva.thogakade.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders,String> {
}
