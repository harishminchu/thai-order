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
        new Product(1, "Wan Tan", "Gefüllte Teigtaschen", new BigDecimal(2.5)),
        new Product(2, "Tom Yam Gung", "Zitronengrassuppe mit Garnelen", new BigDecimal(7.1)),
        new Product(3, "Tom Kha Gai", "Saure Kokosmilchsuppe mit Hühnerfleisch", new BigDecimal(6.7)),
        new Product(4, "Gäng Kiau Wan Gai", "Grünes Curry mit Hühnerfleisch", new BigDecimal(6.7)),
        new Product(5, "Gäng Däng Gai", "Rotes Curry Mit Hühnerfleisch", new BigDecimal(6.7)),
        new Product(6, "Phat Krapau Gai", "Gebratenes Huhn mit Basilikum", new BigDecimal(6.7)),
        new Product(7, "Phat Krapau Nua", "Gebratenes Rind mit Basilikum", new BigDecimal(7.1)),
        new Product(8, "Phat Krapau Blamük", "Gebratener Tintenfisch mit Basilikum", new BigDecimal(7.1)),
        new Product(9, "Gung Phat Pak Luam", "Gebratene Garnelen mit Gemüse", new BigDecimal(8.7)),
        new Product(10, "Phat Nua Nam Man Hoi", "Gebratenes Rind mit Gemüse", new BigDecimal(7.1)),
        new Product(11, "Gai Phat Pak Luam", "Gebratenes Huhn mit Gemüse", new BigDecimal(6.7)),
        new Product(12, "Phat Pak Luam", "Gebratenes Gemüse", new BigDecimal(6.1)),
        new Product(13, "Phat Prik King", "Gebratenes Huhn mit Ingwer und Morcheln", new BigDecimal(6.7)),
        new Product(15, "Khao Phad Gung", "Gebratener Reis mit Garnelen", new BigDecimal(8.7)),
        new Product(16, "Khao Phad Gai", "Gebratener Reis mit Huhn", new BigDecimal(6.7)),
        new Product(17, "Phat Thai", "Gebratene Reisnudeln nach Thai-Art", new BigDecimal(6.1)),
        new Product(18, "Phat Thai Gai", "Gebratene Reisnudeln nach Thai-Art mit Huhn", new BigDecimal(6.7)),
        new Product(19, "Phat Thai Gung", "Gebratene Reisnudeln nach Thai-Art mit Garnelen", new BigDecimal(8.7)),
        new Product(20, "Phat Khi Mau", "Gebratene Spaghetti", new BigDecimal(6.1)),
        new Product(21, "Phat Khi Mau Nua", "Gebratene Spaghetti mit Rind", new BigDecimal(7.1)),
    };

    @Inject
    private ProductRepository productRepository;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        productRepository.save(asList(defaultProducts));
    }
}
