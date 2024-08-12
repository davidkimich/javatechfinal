package com.csis231.api.service;

import com.csis231.api.exception.ResourceNotFoundException;
import com.csis231.api.model.supplier;
import com.csis231.api.repository.supplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class supplierService {

    private final supplierRepository supplierRepository;

    @Autowired
    public supplierService(supplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public supplier createSupplier(supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public supplier updateSupplier(Long id, supplier supplierDetails) {
        supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier does not exist with id : " + id));

        // Update supplier fields with details
        // Example:
        // supplier.setSupplierName(supplierDetails.getSupplierName());
        // supplier.setSupplierDescription(supplierDetails.getSupplierDescription());

        return supplierRepository.save(supplier);
    }

    public Map<String, Boolean> deleteSupplier(Long id) {
        supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found."));

        supplierRepository.delete(supplier);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
