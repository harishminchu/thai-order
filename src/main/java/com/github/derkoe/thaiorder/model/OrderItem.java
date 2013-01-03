package com.github.derkoe.thaiorder.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable
{
    @Id
    @GeneratedValue
    public Integer id;

    @Column(length = 5)
    public String name;

    @ManyToOne
    public Product product;

    @Column
    public BigDecimal price;

    @Column
    public boolean paid;

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItem other = (OrderItem) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return new ToStringCreator(this)
            .append("id", id)
            .append("name", name)
            .append("product-no", product.number)
            .append("paid", paid)
            .toString();
    }
}
