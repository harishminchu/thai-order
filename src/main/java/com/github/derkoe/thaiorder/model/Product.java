package com.github.derkoe.thaiorder.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable
{
    @Id
    @GeneratedValue
    public Integer id;

    @Column
    public Integer number;

    @Column(length = 50, nullable = false)
    public String name;

    @Column
    public BigDecimal price;

    Product()
    {
    }

    public Product(Integer number, String name, BigDecimal price)
    {
        super();
        this.number = number;
        this.name = name;
        this.price = price;
    }
}
