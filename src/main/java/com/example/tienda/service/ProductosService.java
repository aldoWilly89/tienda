package com.example.tienda.service;

import java.util.List;

import com.example.tienda.model.Producto;

public interface ProductosService {
	
	public List<Producto> getAllProductos();
	public List<Producto> buscarProducto(String producto);
	public Producto guardarProducto(Producto producto);
	public Producto actualizarProducto(Long id, Producto producto);
	public Producto eliminarProducto(Long id);

}
