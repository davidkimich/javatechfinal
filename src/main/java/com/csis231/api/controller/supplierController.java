package com.csis231.api.controller;

import com.csis231.api.model.supplier;
import com.csis231.api.service.supplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/suppliers")
public class supplierController {

    private final supplierService supplierService;

    public supplierController(supplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PostMapping
    public ResponseEntity<supplier> createSupplier(@Valid @RequestBody supplier supplier) {
        supplier savedSupplier = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<supplier> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSupplier(@PathVariable Long id, @Valid @RequestBody supplier supplierDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid supplier data");
        }
        supplier updatedSupplier = supplierService.updateSupplier(id, supplierDetails);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
