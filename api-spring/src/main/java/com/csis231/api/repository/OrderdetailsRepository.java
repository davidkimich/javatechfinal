package com.csis231.api.repository;


import com.csis231.api.model.Orderdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderdetailsRepository extends JpaRepository<Orderdetails, Long> {

    @Query("SELECT MAX(od.orderDetailsId) FROM Orderdetails od")
    Long getMaxOrderDetailsId();
}
