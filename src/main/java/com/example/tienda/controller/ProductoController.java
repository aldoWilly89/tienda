package com.example.tienda.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tienda.model.Producto;
import com.example.tienda.service.ProductosService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	@Autowired
	private ProductosService productosService;
	
	@GetMapping("/consultarTodos")
	public ResponseEntity<List<Producto>> listarTodasLasPersona (){
		return ResponseEntity.ok(productosService.getAllProductos());
	}
	
	@GetMapping("/consultarProducto")
	public ResponseEntity<List<Producto>> buscarProducto (@RequestParam(value="producto", required = true) String producto){
		return ResponseEntity.ok(productosService.buscarProducto(producto));
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
		
		try {
			Producto productoGuardado = productosService.guardarProducto(producto);
			URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(productoGuardado.getIdproducto()).toUri();
			return ResponseEntity.created(uri).body(productoGuardado);
			
		} catch (DataIntegrityViolationException ex) {
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("ERROR", ex.getMessage())
					.build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/actualizar/{idproducto}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable Long idproducto, @RequestBody Producto producto){
		
		try {
			Producto productoGuardado = productosService.actualizarProducto(idproducto, producto);
			URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(productoGuardado.getIdproducto()).toUri();
			return ResponseEntity.created(uri).body(productoGuardado);
			
		} catch (DataIntegrityViolationException ex) {
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("ERROR", ex.getMessage())
					.build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<Producto> eliminarProducto(@RequestParam(value="idproducto", required = true) Long idproducto){
		
		try {
			Producto productoGuardado = productosService.eliminarProducto(idproducto);
			URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(productoGuardado.getIdproducto()).toUri();
			return ResponseEntity.created(uri).body(productoGuardado);
			
		} catch (DataIntegrityViolationException ex) {
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("ERROR", ex.getMessage())
					.build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
