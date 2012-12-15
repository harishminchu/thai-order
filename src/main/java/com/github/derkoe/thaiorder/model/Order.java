package com.github.derkoe.thaiorder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order implements Serializable
{
    @Id
    @GeneratedValue
    public Integer id;

    @Column
    @Temporal(TemporalType.DATE)
    public Date day;

    @Column
    public OrderStatus status;

    @OneToMany
    public List<OrderItem> items = new ArrayList<OrderItem>();

    public static enum OrderStatus
    {
        NEW,
        ORDERED,
        CLOSED
    }
}
