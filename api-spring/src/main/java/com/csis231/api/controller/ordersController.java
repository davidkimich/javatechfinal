package com.csis231.api.controller;

import com.csis231.api.model.orders;
import com.csis231.api.service.ordersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class ordersController {

    private final ordersService ordersService;

    public ordersController(ordersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public List<orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<orders> createOrders(@Valid @RequestBody orders orders) {
        orders savedOrders = ordersService.createOrders(orders);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<orders> getOrdersById(@PathVariable Long id) {
        return ResponseEntity.ok(ordersService.getOrdersById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrders(@PathVariable Long id, @Valid @RequestBody orders ordersDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid orders data");
        }
        orders updatedOrders = ordersService.updateOrders(id, ordersDetails);
        return ResponseEntity.ok(updatedOrders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long id) {
        ordersService.deleteOrders(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sum")
    public ResponseEntity<Double> getOrdersSum() {
        double sum = ordersService.getOrdersSum();
        return ResponseEntity.ok(sum);
    }
}
