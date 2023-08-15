package com.example.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tienda.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	public List<Producto> findByProductoContainingIgnoreCase(String nombre);
	
	public List<Producto> findByProductoIgnoreCase(String nombre);

}
