package com.backend.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author balde
 */
@Getter @Setter
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String name;
    private String address;
    private Long cp;
    private Long method;
    private Double subtotal;
    private Double total;
    
    private Long id_user;
    private StateFactura state;
    private String date;  

    public Factura() {
    }

    public Factura(Long id, String email, String name, String address, Long cp, Long method, Double subtotal, Double total, Long id_user, StateFactura state, String date) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.cp = cp;
        this.method = method;
        this.subtotal = subtotal;
        this.total = total;
        this.id_user = id_user;
        this.state = state;
        this.date = date;
    }
    
    
}
