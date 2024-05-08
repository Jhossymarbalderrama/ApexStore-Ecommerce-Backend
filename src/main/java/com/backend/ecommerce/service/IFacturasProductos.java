/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.backend.ecommerce.service;

import com.backend.ecommerce.model.FacturasProductos;
import java.util.List;

/**
 *
 * @author balde
 */
public interface IFacturasProductos {
    public List<FacturasProductos> listFacturasProductos();
    
    public FacturasProductos findFacturasProductos (Long id);
    
    public List<Long> listIdProductsXfactura(Long idFactura);
    
    public FacturasProductos saveFacturasProductos (FacturasProductos ft);
    
    public void deleteFacturasProductos (Long id);
    
    public  FacturasProductos updateFacturasProductos (FacturasProductos ft);
    
    public  boolean existById(Long id);     
}
