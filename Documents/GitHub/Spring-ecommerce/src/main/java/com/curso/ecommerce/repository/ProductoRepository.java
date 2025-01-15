package com.curso.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.ecommerce.model.Producto;

//es un DAO
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
