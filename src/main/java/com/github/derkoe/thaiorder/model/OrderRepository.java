package com.github.derkoe.thaiorder.model;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>, OrderRepositoryCustom
{
    @Query("FROM Order WHERE day > :date")
    Iterable<Order> findAfter(@Param("date") Date date);

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.id = :id")
    Order findOneWithItems(@Param("id") Integer id);
}
