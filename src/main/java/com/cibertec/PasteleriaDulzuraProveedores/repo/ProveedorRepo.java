package com.cibertec.PasteleriaDulzuraProveedores.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;

public interface ProveedorRepo extends JpaRepository<Proveedor, Integer>  {
	
	// Búsqueda de proveedores por razonSocial similar
    @Query("select p from Proveedor p where p.razonSocial like ?1")
    public abstract List<Proveedor> listaPorRazonSocialLike(String razonSocial);
    
    // Validaciones CRUD Registrar
    @Query("select p from Proveedor p where p.razonSocial = ?1")
    public abstract List<Proveedor> listaPorRazonSocialIgualRegistra(String razonSocial);
    
    @Query("select p from Proveedor p where p.ruc = ?1")
    public abstract List<Proveedor> listaPorRUCIgualRegistra(String ruc);      
    
    // Validaciones CRUD Actualizar
    @Query("select p from Proveedor p where p.razonSocial = ?1")
    public abstract List<Proveedor> listaPorRazonSocialIgualActualiza(String razonSocial);
    
    @Query("select p from Proveedor p where p.ruc = ?1")
    public abstract List<Proveedor> listaPorRUCIgualActualiza(String ruc);        
      
    
 // Consulta compleja (como ejemplo, ajustar según necesidades)
    @Query("SELECT p FROM Proveedor p WHERE " +
            "(:razonSocial IS NULL OR p.razonSocial LIKE %:razonSocial%) AND " +
            "(:ruc IS NULL OR :ruc = '' OR p.ruc = :ruc) AND " +
            "(:estado IS NULL OR p.estado = :estado)")
    List<Proveedor> listaConsulta (
        @Param("razonSocial") String razonSocial,
        @Param("ruc") String ruc, 
        @Param("estado") int estado 
    );
    
    
}


