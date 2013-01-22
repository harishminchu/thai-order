package com.github.derkoe.thaiorder.service;

import static com.github.derkoe.thaiorder.model.Order.OrderStatus.NEW;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.derkoe.thaiorder.model.Order;
import com.github.derkoe.thaiorder.model.Order.OrderStatus;
import com.github.derkoe.thaiorder.model.OrderItem;
import com.github.derkoe.thaiorder.model.OrderRepository;

@Service
public class OrderService
{
    @Inject
    private OrderRepository orderRepository;

    public Iterable<OrderListDto> getOrdersAfter(Date date)
    {
        List<OrderListDto> result = new LinkedList<OrderListDto>();
        for (Order order : orderRepository.findAfter(date))
        {
            result.add(new OrderListDto(order.id, order.day, order.status));
        }
        return result;
    }

    public static class OrderListDto
    {
        public final Integer id;
        public final Date day;
        public final OrderStatus status;

        public OrderListDto(Integer id, Date day, OrderStatus status)
        {
            super();
            this.id = id;
            this.day = day;
            this.status = status;
        }
    }

    public Order create()
    {
        Order order = new Order();
        order.day = new Date();
        order.status = OrderStatus.NEW;
        return orderRepository.save(order);
    }

    public Order get(Integer orderId)
    {
        return orderRepository.findOneWithItems(orderId);
    }

    @Transactional
    public Order update(Integer orderId, Set<OrderItem> items, OrderStatus status)
    {
        Order order = orderRepository.findOneWithItems(orderId);
        if (order.status != NEW)
        {
            throw new IllegalArgumentException("Order may only be changed in state NEW");
        }
        order.status = status;

        order = orderRepository.updateItems(order, items);

        return order;
    }

    @Transactional
    public void delete(Integer id)
    {
        orderRepository.delete(id);
    }
}
