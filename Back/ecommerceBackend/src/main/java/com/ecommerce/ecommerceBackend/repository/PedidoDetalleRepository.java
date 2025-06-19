package com.ecommerce.ecommerceBackend.repository;

import com.ecommerce.ecommerceBackend.model.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer> {
}
