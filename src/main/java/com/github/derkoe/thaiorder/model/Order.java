package com.github.derkoe.thaiorder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.core.style.ToStringCreator;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    public Set<OrderItem> items = new HashSet<OrderItem>();

    public static enum OrderStatus
    {
        NEW,
        ORDERED,
        CLOSED
    }

    @Override
    public String toString()
    {
        return new ToStringCreator(this)
            .append("id", id)
            .append("day", day)
            .append("status", status)
            .append("items", items)
            .toString();
    }
}
