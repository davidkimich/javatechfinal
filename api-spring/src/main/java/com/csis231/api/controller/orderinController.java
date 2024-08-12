package com.csis231.api.controller;

import com.csis231.api.model.orderin;
import com.csis231.api.service.orderinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/orderin")
public class orderinController {

    private final orderinService orderinService;

    public orderinController(orderinService orderinService) {
        this.orderinService = orderinService;
    }

    @GetMapping
    public List<orderin> getAllOrderin() {
        return orderinService.getAllOrderin();
    }

    @PostMapping
    public ResponseEntity<orderin> createOrderin(@Valid @RequestBody orderin orderin) {
        orderin savedOrderin = orderinService.createOrderin(orderin);
        return new ResponseEntity<>(savedOrderin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<orderin> getOrderinById(@PathVariable Long id) {
        return ResponseEntity.ok(orderinService.getOrderinById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrderin(@PathVariable Long id, @Valid @RequestBody orderin orderinDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid orderin data");
        }
        orderin updatedOrderin = orderinService.updateOrderin(id, orderinDetails);
        return ResponseEntity.ok(updatedOrderin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderin(@PathVariable Long id) {
        orderinService.deleteOrderin(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/sum")
    public ResponseEntity<Double> getOrderinSum() {
        double sum = orderinService.getOrderinSum();
        return ResponseEntity.ok(sum);
    }
}