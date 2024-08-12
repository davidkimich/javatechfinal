package com.csis231.api.controller;

import com.csis231.api.model.Orderdetails;
import com.csis231.api.service.OrderdetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/orderdetails")
public class OrderdetailsController {

    private final OrderdetailsService orderdetailsService;

    public OrderdetailsController(OrderdetailsService orderdetailsService) {
        this.orderdetailsService = orderdetailsService;
    }

    @GetMapping
    public List<Orderdetails> getAllOrderdetails() {
        return orderdetailsService.getAllOrderdetails();
    }

    @PostMapping
    public ResponseEntity<Orderdetails> createOrderdetails(@Valid @RequestBody Orderdetails orderdetails) {
        Orderdetails savedOrderdetails = orderdetailsService.createOrderdetails(orderdetails);
        return new ResponseEntity<>(savedOrderdetails, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orderdetails> getOrderdetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(orderdetailsService.getOrderdetailsById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrderdetails(@PathVariable Long id, @Valid @RequestBody Orderdetails orderdetailsDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid orderdetails data");
        }
        Orderdetails updatedOrderdetails = orderdetailsService.updateOrderdetails(id, orderdetailsDetails);
        return ResponseEntity.ok(updatedOrderdetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderdetails(@PathVariable Long id) {
        orderdetailsService.deleteOrderdetails(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/max")
    public ResponseEntity<Long> getMaxOrderDetailsId() {
        Long maxOrderDetailsId = orderdetailsService.getMaxOrderDetailsId();
        return ResponseEntity.ok(maxOrderDetailsId);
    }
}
