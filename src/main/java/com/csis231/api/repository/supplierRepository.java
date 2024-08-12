package com.csis231.api.repository;


import com.csis231.api.model.supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface supplierRepository extends JpaRepository<supplier, Long> {
}
