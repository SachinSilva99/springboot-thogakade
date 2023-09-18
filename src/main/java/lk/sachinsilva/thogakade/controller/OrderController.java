package lk.sachinsilva.thogakade.controller;


import lk.sachinsilva.thogakade.dto.CustomerDTO;
import lk.sachinsilva.thogakade.dto.OrderDTO;
import lk.sachinsilva.thogakade.dto.OrderDetailDTO;
import lk.sachinsilva.thogakade.dto.PlaceOrderDTO;
import lk.sachinsilva.thogakade.service.OrderService;
import lk.sachinsilva.thogakade.service.PlaceOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/*
Author : Sachin Silva
*/
@RestController
@RequestMapping("/thogakade/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final PlaceOrderService placeOrderService;

    public OrderController(OrderService orderService, PlaceOrderService placeOrderService) {
        this.orderService = orderService;
        this.placeOrderService = placeOrderService;
    }


    @GetMapping
    protected List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orderDetails")
    protected List<OrderDetailDTO> getAllOrderDetails() {
        return orderService.getAllOrderDetails();
    }

    @PostMapping
    protected void createOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        CustomerDTO customerDto = placeOrderDTO.getCustomerDto();
        OrderDTO orderDTO = new OrderDTO(placeOrderDTO.getOrderId(), new Date(), customerDto.getId());
        boolean placed = placeOrderService.placeOrder(orderDTO, placeOrderDTO.getCartDetailDTOS(), placeOrderDTO.getCustomerDto());
        if (placed) {
        } else {
        }
    }
}
