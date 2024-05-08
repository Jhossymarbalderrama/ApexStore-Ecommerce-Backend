/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.service;

import com.backend.ecommerce.model.FacturasProductos;
import com.backend.ecommerce.repository.FacturasProductosRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author balde
 */
@Service
public class FacturasProductosService implements IFacturasProductos{

    @Autowired
    private FacturasProductosRepository fpRepo;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<FacturasProductos> listFacturasProductos() {
        return fpRepo.findAll();
    }

    @Override
    public FacturasProductos findFacturasProductos(Long id) {
        return fpRepo.findById(id).orElse(null);
    }

    @Override
    public List<Long> listIdProductsXfactura(Long idFactura){
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT fp.id_producto FROM FacturasProductos fp WHERE fp.id_factura = :idFactura",
                Long.class
        );
        
        query.setParameter("idFactura",idFactura);
        
        return query.getResultList();
    }
    
    @Override
    public FacturasProductos saveFacturasProductos(FacturasProductos fp) {
        return fpRepo.save(fp);
    }

    @Override
    public void deleteFacturasProductos(Long id) {
        fpRepo.deleteById(id);
    }

    @Override
    public FacturasProductos updateFacturasProductos(FacturasProductos fp) {
        return fpRepo.save(fp);
    }

    @Override
    public boolean existById(Long id) {
        return fpRepo.existsById(id);
    }
    
}
