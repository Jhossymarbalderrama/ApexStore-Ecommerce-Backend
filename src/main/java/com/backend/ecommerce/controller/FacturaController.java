package com.backend.ecommerce.controller;

import com.backend.ecommerce.model.Factura;
import com.backend.ecommerce.model.StateFactura;
import com.backend.ecommerce.security.controller.Message;
import com.backend.ecommerce.service.IFactura;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author balde
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200","https://e-commerce-ac291.web.app/"}, maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api")
@Tag(name="Facturas", description="Controller for Facturas")
public class FacturaController {

    @Autowired
    private IFactura ftService;

    @GetMapping("/factura/list")
    @ResponseBody
    public ResponseEntity<List<Factura>> listFactura() {
        List<Factura> listFt;

        try {
            listFt = ftService.listFacturas();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(listFt, HttpStatus.OK);
    }

    @GetMapping("/factura/list/{id}")
    public ResponseEntity<List<Factura>> listFacturasUser(@PathVariable Long id) {
        List<Factura> listFt;
        try {
            listFt = ftService.listFacturasUser(id);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(listFt, HttpStatus.OK);
    }

    @GetMapping("/factura/get/{id}")
    public ResponseEntity<?> findFactura(@PathVariable Long id) {
        Object ft;

        try {
            if (!ftService.existById(id)) {
                return new ResponseEntity(new Message("Error. No existe el ID indicado: " + id), HttpStatus.NOT_FOUND);
            }
            ft = ftService.findFactura(id);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(ft, HttpStatus.OK);
    }

    @PostMapping("/factura/add")
    public ResponseEntity<?> saveFactura(@RequestBody Map<String, Object> requestData) {
        Object facturaValid;
        Map<String, Object> facturaData = (Map<String, Object>) requestData.get("facturaData");

        try {
            Factura nFt = new Factura();
            nFt.setEmail(facturaData.get("email").toString());
            nFt.setName(facturaData.get("name").toString());
            nFt.setAddress(facturaData.get("address").toString());
            nFt.setCp(Long.parseLong(facturaData.get("cp").toString()));
            nFt.setMethod(Long.parseLong(facturaData.get("method").toString()));
            nFt.setSubtotal(Double.parseDouble(facturaData.get("subtotal").toString()));
            nFt.setTotal(Double.parseDouble(facturaData.get("total").toString()));
            nFt.setId_user(Long.parseLong(facturaData.get("id_user").toString()));
            nFt.setState(StateFactura.valueOf(facturaData.get("state").toString()));
            
            /*Fecha de alta*/
            Date fechaAlta = new Date();
            SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            nFt.setDate(fechaFormat.format(fechaAlta));
            

            List<Integer> listProductInteger = (List<Integer>) requestData.get("listProduct");
            List<Long> listProduct = listProductInteger.stream().map(Long::valueOf).collect(Collectors.toList());

            facturaValid = ftService.saveFactura(nFt, listProduct);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(facturaValid, HttpStatus.OK);
    }

    @PostMapping("/factura/cancel")
    public ResponseEntity<?> cancelFactura(@RequestBody Factura ft) {
        Object facturaValid;
        try {           
            facturaValid = ftService.cancelFactura(ft);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }
        return new ResponseEntity(facturaValid, HttpStatus.OK);
    }
    
    /***
     * Update Factura
     * @param ft
     * @return 
     */
    @PutMapping("/factura/update")
    public ResponseEntity<?> updatePerson(@RequestBody Factura ft) {
        Object facturaValid;      
        try {
            facturaValid = ftService.updateFactura(ft);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error!. Algo salio mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(facturaValid, HttpStatus.OK);    
    }
    

    private ResponseEntity responseMessage(String data) {
        return new ResponseEntity(new Message(data + " Required"), HttpStatus.BAD_REQUEST);
    }
}
