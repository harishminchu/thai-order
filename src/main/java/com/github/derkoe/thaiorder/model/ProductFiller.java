package com.github.derkoe.thaiorder.model;

import static java.util.Arrays.asList;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ProductFiller implements InitializingBean
{
    private static final Product[] defaultProducts = {
        new Product(3, "Tom Yam Gung", new BigDecimal(6.5)),
        new Product(4, "Tom Kha Gai", new BigDecimal(6.5)),
        new Product(5, "Gäng Kiau Wan Gai", new BigDecimal(6.5)),
        new Product(6, "Gäng Däng Gai", new BigDecimal(6.5)),
        new Product(7, "Phat Krapau Gai", new BigDecimal(6.5)),
        new Product(8, "Phat Krapau Nua", new BigDecimal(6.5)),
        new Product(9, "Phat Krapau Blamük", new BigDecimal(6.5)),
        new Product(10, "Gung Phat Pak Luam", new BigDecimal(8.5)),
        new Product(11, "Phat Nua Nam Man Hoi", new BigDecimal(6.9)),
        new Product(12, "Gai Phat Pak Luam", new BigDecimal(6.5)),
        new Product(13, "Phat Pak Luam", new BigDecimal(5.9)),
        new Product(14, "Phat Prik King", new BigDecimal(6.5))
    };

    @Inject
    private ProductRepository productRepository;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        productRepository.save(asList(defaultProducts));
    }
}
