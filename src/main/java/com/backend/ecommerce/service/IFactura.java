package com.backend.ecommerce.service;

import com.backend.ecommerce.model.Factura;
import java.util.List;

/**
 *
 * @author balde
 */
public interface IFactura {
    
    public List<Factura> listFacturas();
    
    public List<Factura> listFacturasUser(Long id_user);

    public Factura findFactura (Long id);
    
    public Factura saveFactura (Factura ft, List<Long> listIdProductos);
    
    public void deleteFactura (Long id);
    
    public  Factura updateFactura (Factura ft);
    
    public Factura cancelFactura(Factura ft);
    
    public  boolean existById(Long id);         
}
