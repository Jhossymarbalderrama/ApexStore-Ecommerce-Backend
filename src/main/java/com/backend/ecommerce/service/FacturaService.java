package com.backend.ecommerce.service;

import com.backend.ecommerce.model.Factura;
import com.backend.ecommerce.model.FacturasProductos;
import com.backend.ecommerce.model.Product;
import com.backend.ecommerce.model.StateFactura;
import com.backend.ecommerce.repository.FacturaRepository;
import com.backend.ecommerce.repository.FacturasProductosRepository;
import com.backend.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author balde
 */
@Service
public class FacturaService implements IFactura {

    @Autowired
    public FacturaRepository facturaRepo;

    @Autowired
    private FacturasProductosRepository fpRepo;

    @Autowired
    private ProductRepository pdRepo;    

    @Autowired
    private IFacturasProductos fpServ;
    
    @Override
    public List<Factura> listFacturas() {
        return facturaRepo.findAll();
    }

    @Override
    public List<Factura> listFacturasUser(Long id_user) {
        List<Factura> facturas = facturaRepo.findAll();
        List<Factura> facturasUser = new ArrayList<>();
        for (Factura ft : facturas) {
            if (ft.getId_user().equals(id_user)) {
                facturasUser.add(ft);
            }
        }
        return facturasUser;
    }

    @Override
    public Factura findFactura(Long id) {
        return facturaRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura saveFactura(Factura ft, List<Long> listIdProductos) {
        
        Factura facturaCreada =  facturaRepo.save(ft);
        /*Alta de Productos-Factura*/
        for (Long idProducto : listIdProductos) {
            Product pd = pdRepo.findById(idProducto).orElse(null);
            if (pd != null) {
                /*Alta de Productos-Factura*/
                FacturasProductos fp = new FacturasProductos();
                fp.setId_factura(facturaCreada.getId()); //Este id no existe aun ya que no hice el save para que me genere la id
                fp.setId_producto(idProducto);
                fpRepo.save(fp);
                                
                /*Actualizo el stock del producto*/
                pd.setStock(pd.getStock() - 1);
                pdRepo.save(pd);
            }
        }

        return facturaCreada;
    }

    @Override
    public void deleteFactura(Long id) {
        facturaRepo.deleteById(id);
    }

    @Override
    public Factura updateFactura(Factura ft) {
        return facturaRepo.save(ft);
    }

    @Override
    public Factura cancelFactura(Factura ft) {

        /*Cambio el estado de la factura a cancelado*/
        ft.setState(StateFactura.CANCELADO);

        /*Actualizo Fecha de alta y ahora es Fecha de Cancelacion*/
        Date fechaAlta = new Date();
        SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ft.setDate(fechaFormat.format(fechaAlta));        
         
        List<Long> idProducts = fpServ.listIdProductsXfactura(ft.getId());       
        /*Modifico el stock de los productos*/     
        for(Long id : idProducts){        
            Product pd = pdRepo.findById(id).orElse(null);
            if (pd != null) {                
                //Actualizo el stock del producto
                pd.setStock(pd.getStock() + 1);
                pdRepo.save(pd);                
            }
        }       
        
         
        return facturaRepo.save(ft);
    }

    @Override
    public boolean existById(Long id) {
        return facturaRepo.existsById(id);
    }

}
