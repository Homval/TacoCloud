package ru.example.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.example.tacocloud.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
