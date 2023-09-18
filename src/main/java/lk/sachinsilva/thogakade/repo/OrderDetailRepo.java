package lk.sachinsilva.thogakade.repo;

import lk.sachinsilva.thogakade.entity.OrderDetail;
import lk.sachinsilva.thogakade.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, OrderDetailId> {
}
