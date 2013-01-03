package com.github.derkoe.thaiorder.model;

import java.util.Set;

public interface OrderRepositoryCustom
{
    Order updateItems(Order order, Set<OrderItem> items);
}
