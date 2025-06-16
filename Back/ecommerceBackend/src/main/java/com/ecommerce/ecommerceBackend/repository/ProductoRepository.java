package com.ecommerce.ecommerceBackend.repository;

import com.ecommerce.ecommerceBackend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
