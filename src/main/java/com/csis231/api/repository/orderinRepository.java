package com.csis231.api.repository;


import com.csis231.api.model.orderin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderinRepository extends JpaRepository<orderin, Long> {
}
