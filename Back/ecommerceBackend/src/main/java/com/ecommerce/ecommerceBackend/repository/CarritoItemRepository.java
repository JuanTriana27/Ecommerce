package com.ecommerce.ecommerceBackend.repository;

import com.ecommerce.ecommerceBackend.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
}