package com.github.derkoe.thaiorder.web;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Calendar;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.derkoe.thaiorder.model.Order;
import com.github.derkoe.thaiorder.service.OrderService;
import com.github.derkoe.thaiorder.service.OrderService.OrderListDto;

@Controller
@RequestMapping("/order")
public class OrderController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderService orderService;

    @RequestMapping("{id}")
    @ResponseBody
    public Order get(@PathVariable("id") Integer id)
    {
        return orderService.get(id);
    }

    @RequestMapping(value = "{id}", method = POST)
    @ResponseBody
    public Order update(@PathVariable("id") Integer id, @RequestBody Order order)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            LOGGER.debug("Update order: {}", mapper.writeValueAsString(order));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return orderService.update(id, order.items, order.status);
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public Iterable<OrderListDto> list()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, -5);
        return orderService.getOrdersAfter(calendar.getTime());
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public Order create()
    {
        return orderService.create();
    }

    @RequestMapping(value = "{id}", method = DELETE)
    public void delete(@PathVariable("id") Integer id)
    {
        orderService.delete(id);
    }
}
