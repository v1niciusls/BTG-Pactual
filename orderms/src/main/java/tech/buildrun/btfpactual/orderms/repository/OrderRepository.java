package tech.buildrun.btfpactual.orderms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.buildrun.btfpactual.orderms.entity.OrderEntity;

public interface OrderRepository extends MongoRepository <OrderEntity,Long> {
}
