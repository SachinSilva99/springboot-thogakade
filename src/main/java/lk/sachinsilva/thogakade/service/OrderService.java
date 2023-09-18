package lk.sachinsilva.thogakade.service;


import lk.sachinsilva.thogakade.dto.OrderDTO;
import lk.sachinsilva.thogakade.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService  {


    List<OrderDetailDTO> getAllOrderDetails();

    List<OrderDTO> getAllOrders();
}
