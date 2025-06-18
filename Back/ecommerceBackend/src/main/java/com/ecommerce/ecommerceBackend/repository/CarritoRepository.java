package com.ecommerce.ecommerceBackend.repository;

import com.ecommerce.ecommerceBackend.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
