/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.backend.ecommerce.service;

import com.backend.ecommerce.model.Delivery;
import java.util.List;

/**
 *
 * @author balde
 */
public interface IDelivery {
    public List<Delivery> listDelivery();
    
    public Delivery findDelivery (Long id);
    
    public Delivery saveDelivery (Delivery ft);
    
    public void deleteDelivery (Long id);
    
    public  Delivery updateDelivery (Delivery ft);
    
    public  boolean existById(Long id); 
}
