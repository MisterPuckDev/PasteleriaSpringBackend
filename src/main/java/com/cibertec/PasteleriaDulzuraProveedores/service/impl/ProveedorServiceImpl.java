package com.cibertec.PasteleriaDulzuraProveedores.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.PasteleriaDulzuraProveedores.model.Proveedor;
import com.cibertec.PasteleriaDulzuraProveedores.repo.ProveedorRepo;
import com.cibertec.PasteleriaDulzuraProveedores.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService{
	
	@Autowired
	private ProveedorRepo proveedorRepo;


	@Override
	public Proveedor insertaActualizaProveedor(Proveedor proveedor) {
		return proveedorRepo.save(proveedor);
	}
	
	@Override
    public Proveedor obtienePorId(int proveedorId) {
        Optional<Proveedor> optionalProveedor = proveedorRepo.findById(proveedorId);
        if (optionalProveedor.isPresent()) {
            return optionalProveedor.get();
        } else {
            return null; // O manejarlo de otra manera, dependiendo de tu lógica de negocio
        }
    }

  
	@Override
	public List<Proveedor> listaProveedorPorRazonSocialLike(String razonSocial) {
		return proveedorRepo.listaPorRazonSocialLike(razonSocial);
	}

	@Override
	public void eliminaProveedor(int proveedorId) {
		proveedorRepo.deleteById(proveedorId);
	}


	@Override
	public List<Proveedor> listaPorRazonSocialIgualRegistra(String razonSocial) {
		return proveedorRepo.listaPorRazonSocialIgualRegistra(razonSocial);
	}


	@Override
	public List<Proveedor> listaPorRUCIgualRegistra(String ruc) {
		return proveedorRepo.listaPorRUCIgualRegistra(ruc);
	}


	@Override
	public List<Proveedor> listaPorRazonSocialIgualActualiza(String razonSocial) {
		return proveedorRepo.listaPorRazonSocialIgualActualiza(razonSocial);
	}


	@Override
	public List<Proveedor> listaPorRUCIgualActualiza(String ruc) {
		return proveedorRepo.listaPorRUCIgualActualiza(ruc);
	}


	@Override
	public List<Proveedor> listaConsulta(String razonSocial, String ruc, int estado) {
		return proveedorRepo.listaConsulta(razonSocial, ruc, estado);
	}


}
