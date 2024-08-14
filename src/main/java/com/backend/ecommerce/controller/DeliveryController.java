package com.backend.ecommerce.controller;

import com.backend.ecommerce.model.Delivery;
import com.backend.ecommerce.security.controller.Message;
import com.backend.ecommerce.service.IDelivery;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@Tag(name="Delivery", description="Controller for Delivery")
public class DeliveryController {

    @Autowired
    private IDelivery deliveryService;

    /**
     * *
     * List Deliverys
     *
     * @return
     */
    @GetMapping("/delivery/list")
    @ResponseBody
    public ResponseEntity<List<Delivery>> listDeliverys() {
        List<Delivery> listDelivery;

        try {
            listDelivery = deliveryService.listDelivery();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(listDelivery, HttpStatus.OK);
    }

    /**
     * *
     * Get Delivery
     *
     * @param id
     * @return
     */
    @GetMapping("/delivery/get/{id}")
    public ResponseEntity<?> findDelivery(@PathVariable Long id) {
        Object dv;

        try {
            if (!deliveryService.existById(id)) {
                return new ResponseEntity(new Message("Error. No existe el ID indicado: " + id), HttpStatus.NOT_FOUND);
            }
            dv = deliveryService.findDelivery(id);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(dv, HttpStatus.OK);
    }

    @PostMapping("/delivery/add")
    public ResponseEntity<?> saveDelivery(@RequestBody Delivery dv) {
        Object dvValid;

        try {
            if (dv.getName().equals("") || dv.getName() == null) {
                return responseMessage(dv.getName());
            }

            if (dv.getDescription().equals("") || dv.getDescription() == null) {
                return responseMessage(dv.getDescription());
            }

            if (dv.getImg().equals("") || dv.getImg() == null) {
                return responseMessage(dv.getImg());
            }
            
            if (dv.getPrice() < 0) {
                return responseMessage(dv.getPrice().toString());
            }
                      
            dvValid = deliveryService.saveDelivery(dv);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }
        
        return new ResponseEntity(dvValid, HttpStatus.OK);
    }

    @DeleteMapping("/delivery/delete/{id}")
    public ResponseEntity<?> deleteDelivery(@PathVariable Long id){
        try{
            if(!deliveryService.existById(id)){
                return new ResponseEntity(new Message("Error. No existe la Id: " + id), HttpStatus.NOT_FOUND);
            }
            
            deliveryService.deleteDelivery(id);
        }catch(Exception e){
           return new ResponseEntity(new Message("Error!. Algo salio mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }
        
        return new ResponseEntity(new Message("Elemento con la id: " + id + " eliminado correctamente."), HttpStatus.OK);
    }
    
    @PutMapping("/delivery/update")
    public ResponseEntity<?> updateDelivery(@RequestBody Delivery dv){
    Object dvValid;

        try {
            if (dv.getName().equals("") || dv.getName() == null) {
                return responseMessage(dv.getName());
            }

            if (dv.getDescription().equals("") || dv.getDescription() == null) {
                return responseMessage(dv.getDescription());
            }

            if (dv.getImg().equals("") || dv.getImg() == null) {
                return responseMessage(dv.getImg());
            }
            
            if (dv.getPrice() < 0) {
                return responseMessage(dv.getPrice().toString());
            }
                      
            dvValid = deliveryService.saveDelivery(dv);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }
        
        return new ResponseEntity(dvValid, HttpStatus.OK);
    }
    
    private ResponseEntity responseMessage(String data) {
        return new ResponseEntity(new Message(data + " Required"), HttpStatus.BAD_REQUEST);
    }
}
