package lk.sachinsilva.thogakade.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderDTO {
    private CustomerDTO customerDto;
    private String orderId;
    private ArrayList<CartDetailDTO> cartDetailDTOS = new ArrayList<>();
}
