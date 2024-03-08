package com.cibertec.PasteleriaDulzuraProveedores.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.PasteleriaDulzuraProveedores.model.Categoria;


public interface CategoriaRepo extends JpaRepository<Categoria, Integer>  {
	
	// Búsqueda de categoria por nombre similar
    @Query("select c from Categoria c where c.nombre like ?1")
    public abstract List<Categoria> listaPorNombreLike(String nombre);
    
    
    // Validaciones CRUD Registrar
    @Query("select c from Categoria c where c.nombre = ?1")
    public abstract List<Categoria> listaPorNombreIgualRegistra(String nombre);
    
    @Query("select c from Categoria c where c.descripcion = ?1")
    public abstract List<Categoria> listaPorDescripcionIgualRegistra(String descripcion);      
    
    // Validaciones CRUD Actualizar
    @Query("select c from Categoria c where c.nombre = ?1")
    public abstract List<Categoria> listaPorNombreIgualActualiza(String nombre);
    
    @Query("select c from Categoria c where c.descripcion = ?1")
    public abstract List<Categoria> listaPorDescripcionIgualActualiza(String descripcion);    
    
    // Consulta compleja (como ejemplo, ajustar según necesidades)
    @Query("select c from Categoria c where "
    						+ "(c.nombre like ?1) and "
    						+ "(?2 = '' or c.descripcion = ?2)")
    public abstract List<Categoria> listaConsulta(String nombre, String descripcion);
    
    @Query("select c from Categoria c where c.descripcion = ?1 and c.categoriaId != ?2")
    public abstract List<Categoria> listaPorDescripcionIgualActualiza(String descripcion, Integer categoriaId);

}
