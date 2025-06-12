package com.ecommerce.ecommerceBackend.repository;

import com.ecommerce.ecommerceBackend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
