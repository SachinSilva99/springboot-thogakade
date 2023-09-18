package lk.sachinsilva.thogakade.service.impl;


import jakarta.transaction.Transactional;
import lk.sachinsilva.thogakade.dto.CartDetailDTO;
import lk.sachinsilva.thogakade.dto.CustomerDTO;
import lk.sachinsilva.thogakade.dto.OrderDTO;
import lk.sachinsilva.thogakade.entity.Customer;
import lk.sachinsilva.thogakade.entity.Item;
import lk.sachinsilva.thogakade.entity.OrderDetail;
import lk.sachinsilva.thogakade.entity.Orders;
import lk.sachinsilva.thogakade.repo.ItemRepo;
import lk.sachinsilva.thogakade.repo.OrderDetailRepo;
import lk.sachinsilva.thogakade.repo.OrderRepo;
import lk.sachinsilva.thogakade.service.PlaceOrderService;
import lk.sachinsilva.thogakade.util.Mapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {
    private final OrderRepo orderRepo;
    private final ItemRepo itemRepo;
    private final OrderDetailRepo orderDetailRepo;

    private final Mapper mapper = new Mapper();

    public PlaceOrderServiceImpl(OrderRepo orderRepo, ItemRepo itemRepo, OrderDetailRepo orderDetailRepo) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.orderDetailRepo = orderDetailRepo;
    }

    @Override
    public boolean placeOrder(OrderDTO orderDTO, ArrayList<CartDetailDTO> cartDetailDTOS, CustomerDTO customerDTO) {

        List<OrderDetail> orderDetails = new ArrayList<>();
        Orders orders = mapper.toOrders(orderDTO);


        for (CartDetailDTO cartDetailDTO : cartDetailDTOS) {
            Item item = mapper.toItem(cartDetailDTO.getItemDTO());
            OrderDetail od = new OrderDetail(orders, item, cartDetailDTO.getQty(), cartDetailDTO.getUnitPrice());
            orderDetails.add(od);
        }

        orders.setOrderDetails(orderDetails);
        Customer customer = mapper.toCustomer(customerDTO);
        List<Orders> orders1 = new ArrayList<>();
        orders1.add(orders);
        orders.setCustomer(customer);
        customer.setOrders(orders1);
        orderRepo.save(orders);
        for (OrderDetail od : orderDetails) {
            od.getItem().setOrderDetails(orderDetails);
            orderDetailRepo.save(od);
        }
        orderDetails.forEach(orderDetail -> itemRepo
                .findById(orderDetail.getItem().getCode())
                .ifPresent(item -> item.setQtyOnHand(orderDetail.getItem().getQtyOnHand() - orderDetail.getQty())));
        return true;
    }
}

