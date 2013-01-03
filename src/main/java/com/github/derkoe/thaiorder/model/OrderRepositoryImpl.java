package com.github.derkoe.thaiorder.model;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderRepositoryImpl implements OrderRepositoryCustom
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Order updateItems(Order order, Set<OrderItem> items)
    {
        for (Iterator<OrderItem> orinalItemIter = order.items.iterator(); orinalItemIter.hasNext(); )
        {
            OrderItem item = orinalItemIter.next();

            if (!items.contains(item))
            {
                em.remove(item);
                orinalItemIter.remove();
            }
        }

        for (OrderItem item : items)
        {
            if (item.id == null)
            {
                em.persist(item);
            }
            else
            {
                item = em.merge(item);
            }
            order.items.add(item);
        }

        return order;
    }
}
