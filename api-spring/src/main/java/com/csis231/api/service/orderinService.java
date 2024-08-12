package com.csis231.api.service;

import com.csis231.api.exception.ResourceNotFoundException;
import com.csis231.api.model.orderin;
import com.csis231.api.repository.orderinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class orderinService {

    private final orderinRepository orderinRepository;

    @Autowired
    public orderinService(orderinRepository orderinRepository) {
        this.orderinRepository = orderinRepository;
    }

    public List<orderin> getAllOrderin() {
        return orderinRepository.findAll();
    }

    public orderin getOrderinById(Long id) {
        return orderinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Orderin not found"));
    }

    public orderin createOrderin(orderin orderin) {
        return orderinRepository.save(orderin);
    }

    public orderin updateOrderin(Long id, orderin orderinDetails) {
        orderin orderin = orderinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orderin does not exist with id : " + id));

        // Update orderin fields with details
        // Example:
        // orderin.setAccountId(orderinDetails.getAccountId());
        // orderin.setOrderName(orderinDetails.getOrderName());
        // orderin.setOrderPrice(orderinDetails.getOrderPrice());
        // orderin.setOrderDescription(orderinDetails.getOrderDescription());
        // orderin.setOrderDetailsId(orderinDetails.getOrderDetailsId());
        // orderin.setSupplierId(orderinDetails.getSupplierId());
        // orderin.setOrderDate(orderinDetails.getOrderDate());

        return orderinRepository.save(orderin);
    }

    public Map<String, Boolean> deleteOrderin(Long id) {
        orderin orderin = orderinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orderin not found."));

        orderinRepository.delete(orderin);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public double getOrderinSum() {
        List<orderin> orderinList = orderinRepository.findAll();
        double sum = 0;
        for (orderin orderin : orderinList) {
            sum += orderin.getOrderPrice();
        }
        return sum;
    }

}