/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author balde
 */
@Getter @Setter
@Entity
public class FacturasProductos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long id_producto;
    private Long id_factura;

    public FacturasProductos() {
    }

    public FacturasProductos(Long id, Long id_producto, Long id_factura) {
        this.id = id;
        this.id_producto = id_producto;
        this.id_factura = id_factura;
    }       
}
