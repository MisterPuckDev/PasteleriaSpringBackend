package com.cibertec.PasteleriaDulzuraProveedores.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.PasteleriaDulzuraProveedores.model.Producto;

public interface ProductoRepo extends JpaRepository<Producto, Integer>  {
	
	// Búsqueda de proveedores por razonSocial similar
    @Query("select p from Producto p where p.nombre like ?1")
    public abstract List<Producto> listaPorNombreLike(String nombre);
    
    // Validaciones CRUD Registrar
    @Query("select p from Producto p where p.nombre = ?1")
    public abstract List<Producto> listaPorNombreIgualRegistra(String nombre);
    
 
    // Validaciones CRUD Actualizar
    @Query("select p from Producto p where p.nombre = ?1")
    public abstract List<Producto> listaPorNombreIgualActualiza(String nombre);
     
 
    // Consulta compleja (como ejemplo, ajustar según necesidades)
    @Query("select p from Producto p where p.nombre like %?1%")
    public abstract List<Producto> listaConsulta(String nombre);
    
    @Query("select p from Producto p where p.nombre = ?1 and p.productoId != ?2")
    List<Producto> findByNombreAndProductoIdNot(String nombre, Integer productoId);

}
