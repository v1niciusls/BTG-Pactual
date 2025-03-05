package tech.buildrun.btfpactual.orderms.listener;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;
import tech.buildrun.btfpactual.orderms.listener.dto.OrderCreatedEvent;
import org.springframework.messaging.Message;

import org.slf4j.Logger;
import tech.buildrun.btfpactual.orderms.service.OrderService;

import static tech.buildrun.btfpactual.orderms.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){
        logger.info("Message consumed: {}",message);
        orderService.save(message.getPayload());
    }

}
