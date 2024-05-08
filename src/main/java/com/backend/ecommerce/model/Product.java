package com.backend.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 *
 * @author balde
 */
@Getter @Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; //Nombre
    private String description; //Descripcion
    private String category; //Categoria
    private Long price; //Precio
    private Long discount; //Descuento
    private Long stock; //Stock del producto
    private String state; //Nuevo - Usado - Reacondicionado - etc
    private String discharge_date; //Fecha de alta del producto
    /*
     @Lob
    @ElementCollection
     @Column(length = 1000000)
    private List<byte[]> img = new ArrayList<>(); //Array de Imagenes del producto
    */
    @Column(length = 1000000)
    private String[] img;

    public Product() {
    }

    public Product(Long id, String name, String description, String category, Long price, Long discount, Long stock, String state, String discharge_date, String[] img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.state = state;
        this.discharge_date = discharge_date;
        this.img = img;
    }
}
