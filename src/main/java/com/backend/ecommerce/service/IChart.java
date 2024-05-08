/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.backend.ecommerce.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author balde
 */
public interface IChart {
    public List<Map<String, Object>> listTotalIngresosXMes();
    
    public List<Map<String, Object>> listTop5ProductosMasVendidos();
       
    public List<Map<String, Object>> listCantUserXRole();
    
    public List<Map<String, Object>> listCantidadDeFacturasMes();        
    
    public List<Map<String, Object>> listAltaProductosXMes();
}
