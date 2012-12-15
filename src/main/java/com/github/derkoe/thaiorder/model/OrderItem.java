package com.github.derkoe.thaiorder.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable
{
    @Id
    @Column(length = 5)
    public String name;

    @Id
    @ManyToOne
    public Product product;

    @Column
    public boolean paid;
}
