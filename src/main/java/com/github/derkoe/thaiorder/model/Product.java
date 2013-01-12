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
    private static final long serialVersionUID = -6801361353409503859L;

    @Id
    @GeneratedValue
    public Integer id;

    @Column
    public Integer number;

    @Column(length = 50, nullable = false)
    public String name;

    @Column(length = 200)
    public String description;

    @Column
    public BigDecimal price;

    Product()
    {
    }

    public Product(Integer number, String name, String description, BigDecimal price)
    {
        super();
        this.number = number;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
