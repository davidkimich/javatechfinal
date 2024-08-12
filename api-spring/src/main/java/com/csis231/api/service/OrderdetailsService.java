package com.csis231.api.service;

import com.csis231.api.exception.ResourceNotFoundException;
import com.csis231.api.model.Orderdetails;
import com.csis231.api.repository.OrderdetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderdetailsService {

    private final OrderdetailsRepository orderdetailsRepository;

    @Autowired
    public OrderdetailsService(OrderdetailsRepository orderdetailsRepository) {
        this.orderdetailsRepository = orderdetailsRepository;
    }

    public List<Orderdetails> getAllOrderdetails() {
        return orderdetailsRepository.findAll();
    }

    public Orderdetails getOrderdetailsById(Long id) {
        return orderdetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Orderdetails not found"));
    }

    public Orderdetails createOrderdetails(Orderdetails orderdetails) {
        return orderdetailsRepository.save(orderdetails);
    }

    public Orderdetails updateOrderdetails(Long id, Orderdetails orderdetailsDetails) {
        Orderdetails orderdetails = orderdetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orderdetails does not exist with id : " + id));

        // Update orderdetails fields with details
        // Example:
        // orderdetails.setOrderId(orderdetailsDetails.getOrderId());
        // orderdetails.setProductId(orderdetailsDetails.getProductId());
        // orderdetails.setQuantity(orderdetailsDetails.getQuantity());
        // orderdetails.setPrice(orderdetailsDetails.getPrice());
        // orderdetails.setDescription(orderdetailsDetails.getDescription());

        return orderdetailsRepository.save(orderdetails);
    }

    public Map<String, Boolean> deleteOrderdetails(Long id) {
        Orderdetails orderdetails = orderdetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orderdetails not found."));

        orderdetailsRepository.delete(orderdetails);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



    public Long getMaxOrderDetailsId() {
        return orderdetailsRepository.getMaxOrderDetailsId();
    }
}
