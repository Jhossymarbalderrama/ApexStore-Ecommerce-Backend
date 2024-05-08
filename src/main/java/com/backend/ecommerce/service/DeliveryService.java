/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.service;

import com.backend.ecommerce.model.Delivery;
import com.backend.ecommerce.repository.DeliveryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author balde
 */
@Service
public class DeliveryService implements IDelivery{
    
    @Autowired
    public DeliveryRepository deliveryRepo;
    
    @Override
    public List<Delivery> listDelivery() {      
        return deliveryRepo.findAll();
    }

    @Override
    public Delivery findDelivery(Long id) {      
        return deliveryRepo.findById(id).orElse(null);
    }

    @Override
    public Delivery saveDelivery(Delivery dl) {        
        return deliveryRepo.save(dl);
    }

    @Override
    public void deleteDelivery(Long id) {   
        deliveryRepo.deleteById(id);
    }   

    @Override
    public Delivery updateDelivery(Delivery dl) {    
        return deliveryRepo.save(dl);
    }

    @Override
    public boolean existById(Long id) {
        return deliveryRepo.existsById(id);
    }
    
}
