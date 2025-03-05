package tech.buildrun.btfpactual.orderms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import tech.buildrun.btfpactual.orderms.Controller.dto.OrderResponse;
import tech.buildrun.btfpactual.orderms.entity.OrderEntity;

public interface OrderRepository extends MongoRepository <OrderEntity,Long> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
