package com.csis231.api.service;

import com.csis231.api.exception.ResourceNotFoundException;
import com.csis231.api.model.orderin;
import com.csis231.api.model.orders;
import com.csis231.api.repository.ordersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ordersService {

    private final ordersRepository ordersRepository;

    @Autowired
    public ordersService(ordersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public orders getOrdersById(Long id) {
        return ordersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Orders not found"));
    }

    public orders createOrders(orders orders) {
        return ordersRepository.save(orders);
    }

    public orders updateOrders(Long id, orders ordersDetails) {
        orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orders does not exist with id : " + id));

        // Update orders fields with details
        // Example:
        // orders.setAccountId(ordersDetails.getAccountId());
        // orders.setOrderName(ordersDetails.getOrderName());
        // orders.setOrderPrice(ordersDetails.getOrderPrice());
        // orders.setOrderRecipient(ordersDetails.getOrderRecipient());
        // orders.setOrderDescription(ordersDetails.getOrderDescription());
        // orders.setOrderDetailsId(ordersDetails.getOrderDetailsId());
        // orders.setOrderDate(ordersDetails.getOrderDate());

        return ordersRepository.save(orders);
    }

    public Map<String, Boolean> deleteOrders(Long id) {
        orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orders not found."));

        ordersRepository.delete(orders);

       Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    public double getOrdersSum() {
        List<orders> orderinList = ordersRepository.findAll();
        double sum = 0;
        for (orders orders : orderinList) {
            sum += orders.getOrderPrice();
        }
        return sum;
    }
}
