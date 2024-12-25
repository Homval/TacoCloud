package ru.example.tacocloud.data;

import ru.example.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
