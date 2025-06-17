package com.ecommerce.ecommerceBackend.repository;

import com.ecommerce.ecommerceBackend.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
}
