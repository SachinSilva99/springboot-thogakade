package lk.sachinsilva.thogakade.service.impl;


import lk.sachinsilva.thogakade.dto.OrderDTO;
import lk.sachinsilva.thogakade.dto.OrderDetailDTO;
import lk.sachinsilva.thogakade.repo.OrderDetailRepo;
import lk.sachinsilva.thogakade.repo.OrderRepo;
import lk.sachinsilva.thogakade.service.OrderService;
import lk.sachinsilva.thogakade.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailRepo;
    private final Mapper mapper = new Mapper();

    public OrderServiceImpl(OrderRepo orderRepo, OrderDetailRepo orderDetailRepo) {
        this.orderRepo = orderRepo;
        this.orderDetailRepo = orderDetailRepo;
    }


    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailRepo
                .findAll()
                .stream()
                .map(mapper::toOrderDetailDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
       return orderRepo.findAll().stream().map(mapper::toOrderDto).collect(Collectors.toList());
    }
}
