package lk.sachinsilva.thogakade.service;




import lk.sachinsilva.thogakade.dto.CartDetailDTO;
import lk.sachinsilva.thogakade.dto.CustomerDTO;
import lk.sachinsilva.thogakade.dto.OrderDTO;

import java.util.ArrayList;

public interface PlaceOrderService {

    boolean placeOrder(OrderDTO orderDTO, ArrayList<CartDetailDTO> cartDetailDTOS, CustomerDTO customerDTO);
}
