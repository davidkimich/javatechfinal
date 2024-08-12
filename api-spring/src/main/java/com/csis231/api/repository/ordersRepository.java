package com.csis231.api.repository;


import com.csis231.api.model.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ordersRepository extends JpaRepository<orders, Long> {

}
