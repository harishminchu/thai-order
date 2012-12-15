package com.github.derkoe.thaiorder.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.derkoe.thaiorder.model.Product;
import com.github.derkoe.thaiorder.service.ProductService;

@Controller
public class ProductController
{
    @Inject
    private ProductService productService;

    @RequestMapping("/product")
    @ResponseBody
    public Iterable<Product> list()
    {
        return productService.getProducts();
    }
}
