/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.controller;

import com.backend.ecommerce.security.controller.Message;
import com.backend.ecommerce.service.IChart;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ChartController {

    @Autowired
    public IChart chartService;

    @GetMapping("/chart/listTotalIngresosXMes")
    @ResponseBody
    public ResponseEntity<Object> listTotalIngresosXMes() {
        Object lista;

        try {
            lista = chartService.listTotalIngresosXMes();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/chart/listTop5ProductosVendidos")
    @ResponseBody
    public ResponseEntity<Object> listTop5ProductosMasVendidos() {
        Object lista;

        try {
            lista = chartService.listTop5ProductosMasVendidos();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/chart/listCantUserXRole")
    @ResponseBody
    public ResponseEntity<Object> listCantUserXRole() {
        Object lista;

        try {
            lista = chartService.listCantUserXRole();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    @GetMapping("/chart/listCantidadDeFacturasMes")
    @ResponseBody
    public ResponseEntity<Object> listCantidadDeFacturasMes() {
        Object lista;

        try {
            lista = chartService.listCantidadDeFacturasMes();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    
    @GetMapping("/chart/listAltaProductosXMes")
    @ResponseBody
    public ResponseEntity<Object> listAltaProductosXMes() {
        Object lista;

        try {
            lista = chartService.listAltaProductosXMes();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    @GetMapping("/chart/getDataCharts")
    @ResponseBody
    public ResponseEntity<Object> getDataCharts(){
        Object res;
        
        try{
            res = chartService.getDataCharts();
        }catch (Exception e){
            return new ResponseEntity(new Message("Error. No se pudo generar los datos de los charts.") + e.toString(), HttpStatus.FOUND);
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    
    private ResponseEntity responseMessage(String data) {
        return new ResponseEntity(new Message(data + " Required"), HttpStatus.BAD_REQUEST);
    }
}
