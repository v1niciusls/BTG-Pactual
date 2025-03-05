package tech.buildrun.btfpactual.orderms.service;

import org.springframework.stereotype.Service;
import tech.buildrun.btfpactual.orderms.entity.OrderEntity;
import tech.buildrun.btfpactual.orderms.entity.OrderItem;
import tech.buildrun.btfpactual.orderms.listener.dto.OrderCreatedEvent;
import tech.buildrun.btfpactual.orderms.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event){
        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        //entity.setTotal();

        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));
        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens().stream().map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade()))).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream().map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco())).toList();
    }

}
