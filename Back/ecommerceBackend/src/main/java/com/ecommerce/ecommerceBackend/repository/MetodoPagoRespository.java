package com.ecommerce.ecommerceBackend.repository;

import com.ecommerce.ecommerceBackend.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRespository extends JpaRepository<MetodoPago, Integer> {
}
