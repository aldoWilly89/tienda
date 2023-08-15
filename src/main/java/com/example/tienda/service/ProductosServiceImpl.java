package com.example.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;

@Service
public class ProductosServiceImpl implements ProductosService{
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> getAllProductos(){
		return productoRepository.findAll();
	}
	
	public List<Producto> buscarProducto(String producto){
		return productoRepository.findByProductoContainingIgnoreCase(producto);
	}
	
	public Producto guardarProducto(Producto producto){
		
		if(producto != null && producto.getProducto() != null) {
			List<Producto> productos = productoRepository.findByProductoIgnoreCase(producto.getProducto());
			
			if(productos == null || productos.isEmpty()) {
				
				if(producto.getEstatus() == null || producto.getEstatus().isEmpty()) {
					producto.setEstatus("ACTIVO");
				}else {
					producto.setEstatus(producto.getEstatus().toUpperCase());
				}
				
				Producto p = productoRepository.save(producto);
				return p;
			}else {
				throw new DataIntegrityViolationException("producto existente");
			}
		}else {
			throw new DataIntegrityViolationException("sin producto");
		}
			
		
	}
	
	public Producto actualizarProducto(Long id, Producto producto){
		if(id != null) {
		Optional<Producto> productoConsulta = productoRepository.findById(id);
			
			if((productoConsulta != null && productoConsulta.isPresent()) &&
					(producto != null && !producto.getProducto().isEmpty() && !producto.getMarca().isEmpty())) {
				
				Producto productoAux = productoConsulta.get();
				if(!producto.getProducto().equals(productoAux.getProducto())) {
					productoAux.setMarca(producto.getMarca());
					productoAux.setProducto(producto.getProducto());
					productoAux.setPrecio(producto.getPrecio());
					productoAux.setDescripcion(producto.getDescripcion());
					productoAux.setEstatus(producto.getEstatus() != null ?producto.getEstatus().toUpperCase():producto.getEstatus());
					
					Producto p = productoRepository.save(productoAux);
					return p;
				}
				else {
					throw new DataIntegrityViolationException("producto existente");
				}
			}else {
				throw new DataIntegrityViolationException("no se encontro el producto");
			}
		}else {
			throw new DataIntegrityViolationException("sin ID");
		}
	}
	
	
	public Producto eliminarProducto(Long id){
		if(id != null) {
			Optional<Producto> productoConsulta = productoRepository.findById(id);

			if(productoConsulta != null && productoConsulta.isPresent()) {

				Producto productoAux = productoConsulta.get();
				productoAux.setEstatus("INACTIVO");

				Producto p = productoRepository.save(productoAux);
				return p;
			}
			else {
				throw new DataIntegrityViolationException("producto existente");
			}

		}else {
			throw new DataIntegrityViolationException("sin ID");
		}
	}


}
