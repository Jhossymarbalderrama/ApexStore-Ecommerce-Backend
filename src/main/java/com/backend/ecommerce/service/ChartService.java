/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.service;

import com.backend.ecommerce.model.Factura;
import com.backend.ecommerce.model.FacturasProductos;
import com.backend.ecommerce.model.Product;
import com.backend.ecommerce.model.User;
import com.backend.ecommerce.model.Role;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author balde
 */
@Service
public class ChartService implements IChart {

    @Autowired
    private IFactura servFactura;

    @Autowired
    private IFacturasProductos servFactProd;

    @Autowired
    private IProduct servProduct;

    @Autowired
    private IUser servUser;

    @Override
    public List<Map<String, Object>> listTotalIngresosXMes() {
        List<Factura> facturas = servFactura.listFacturas();
        int[] años = {2023, 2024};
        List<Map<String, Object>> res = new ArrayList<>();

        for (int año : años) {
            Map<String, Object> datosAño = new HashMap<>();
            List<Double> data = new ArrayList<>();
            Double totalAño = 0.0;

            for (int mes = 1; mes <= 12; mes++) {
                Double totalMes = 0.0;
                for (Factura ft : facturas) {
                    String fechaFacturaStr = ft.getDate();
                    try {
                        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date fechaFactura = formatoFecha.parse(fechaFacturaStr);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaFactura);
                        int mesFactura = calendar.get(Calendar.MONTH) + 1;
                        int añoFactura = calendar.get(Calendar.YEAR);

                        if (mesFactura == mes && añoFactura == año) {
                            totalMes += ft.getTotal();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                data.add(totalMes);
                totalAño += totalMes;
            }

            datosAño.put("name", Integer.toString(año));
            datosAño.put("data", data);
            res.add(datosAño);
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> listTop5ProductosMasVendidos() {
        List<FacturasProductos> listFacturasProductos = servFactProd.listFacturasProductos();
        List<Product> listProductos = servProduct.listProducts();
        Map<Long, Integer> productoCantidadMap = new HashMap<>();

        for (FacturasProductos fp : listFacturasProductos) {
            Long idProducto = fp.getId_producto();
            productoCantidadMap.put(idProducto, productoCantidadMap.getOrDefault(idProducto, 0) + 1);
        }

        List<Map.Entry<Long, Integer>> sortedEntries = productoCantidadMap.entrySet()
                .stream()
                .sorted((Map.Entry.<Long, Integer>comparingByValue().reversed()))
                .collect(Collectors.toList());

        List<Map<String, Object>> top5Productos = new ArrayList<>();
        for (int i = 0; i < Math.min(sortedEntries.size(), 5); i++) {
            Map.Entry<Long, Integer> entry = sortedEntries.get(i);
            Map<String, Object> productoCantidad = new HashMap<>();
            productoCantidad.put("id_producto", entry.getKey());
            productoCantidad.put("cantidad_vendida", entry.getValue());

            Long idProducto = entry.getKey();
            String nombreProducto = null;
            String categoria = null;
            Long precio = null;
            for (Product producto : listProductos) {
                if (Objects.equals(producto.getId(), idProducto)) {
                    nombreProducto = producto.getName();
                    categoria = producto.getCategory();
                    precio = producto.getPrice();
                    break;
                }
            }

            productoCantidad.put("nombre_producto", nombreProducto);
            productoCantidad.put("categoria", categoria);
            productoCantidad.put("precio", precio);

            top5Productos.add(productoCantidad);
        }

        return top5Productos;
    }

    @Override
    public List<Map<String, Object>> listCantUserXRole() {
        List<User> listUser = servUser.listUsers();
        List<Map<String, Object>> res = new ArrayList<>();
        var cantUserADMIN = 0;
        var cantUserUSER = 0;

        for (User usr : listUser) {
            if (usr.getRole() == Role.ADMIN) {
                cantUserADMIN++;
            } else if (usr.getRole() == Role.USER) {
                cantUserUSER++;
            }
        }

        Map<String, Object> adminMap = new HashMap<>();
        adminMap.put("name", Role.ADMIN);
        adminMap.put("value", cantUserADMIN);
        res.add(adminMap);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", Role.USER);
        userMap.put("value", cantUserUSER);
        res.add(userMap);

        return res;
    }

    @Override
    public List<Map<String, Object>> listCantidadDeFacturasMes() {
        List<Map<String, Object>> res = new ArrayList<>();

        List<Factura> facturas = servFactura.listFacturas();

        int[] años = {2023, 2024};

        for (int año : años) {
            Map<String, Object> datosAños = new HashMap<>();
            List<Number> data = new ArrayList<>();

            //System.out.println("Año: " + año);
            for (int mes = 1; mes <= 12; mes++) {
                int cantFtMes = 0;
                //System.out.println("Mes: " + mes);
                for (Factura ft : facturas) {
                    String fechaFacturaStr = ft.getDate();
                    try {
                        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date fechaFactura = formatoFecha.parse(fechaFacturaStr);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaFactura);
                        int mesFactura = calendar.get(Calendar.MONTH) + 1;
                        int añoFactura = calendar.get(Calendar.YEAR);

                        if (mesFactura == mes && añoFactura == año) {
                            cantFtMes = cantFtMes + 1;
                            // System.out.println("Cantidad Factura Mes:" + cantFtMes);                         
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                data.add(cantFtMes);
            }
            datosAños.put("name", Integer.toString(año));
            datosAños.put("data", data);
            res.add(datosAños);
            // System.out.println("Datos de Año:" + datosAños);
        }

        return res;
    }

    /**
     * [
     * { name: 'Procesadores', data: [0, 120, 80, 160, 180, 200, 220, 240, 260,
     * 280, 300, 320] }, { name: 'RAM', data: [80, 90, 100, 110, 120, 130, 140,
     * 150, 160, 170, 180, 190] }, ]
     */
    @Override
    public List<Map<String, Object>> listAltaProductosXMes() {
        List<Map<String, Object>> res = new ArrayList<>();

         List<Product> productos = servProduct.listProducts();

        int[] años = {2023, 2024};

        for (int año : años) {
            Map<String, Object> datosAños = new HashMap<>();
            List<Number> data = new ArrayList<>();

            //System.out.println("Año: " + año);
            for (int mes = 1; mes <= 12; mes++) {
                int cantFtMes = 0;
                //System.out.println("Mes: " + mes);
                for (Product pd : productos) {
                    String fechaProductStr = pd.getDischarge_date();
                    try {
                        //DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaProducto = formatoFecha.parse(fechaProductStr);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaProducto);
                        int mesProducto = calendar.get(Calendar.MONTH) + 1;
                        int añoProducto = calendar.get(Calendar.YEAR);

                        if (mesProducto == mes && añoProducto == año) {
                            cantFtMes = cantFtMes + 1;
                            // System.out.println("Cantidad Factura Mes:" + cantFtMes);                         
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                data.add(cantFtMes);
            }
            datosAños.put("name", Integer.toString(año));
            datosAños.put("data", data);
            res.add(datosAños);
        }
        
        return res;
    }
    
    @Override
    public List<Map<String, Object>> getDataCharts(){
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> objListAltaProductosXMes = new HashMap<>();
        Map<String, Object> objListCantUserXRole = new HashMap<>();
        Map<String, Object> objListCantidadDeFacturasMes = new HashMap<>();
        Map<String, Object> objListTop5ProductosMasVendidos = new HashMap<>();
        Map<String, Object> objListTotalIngresosXMes = new HashMap<>();
        
    
        objListAltaProductosXMes.put("listAltaProductosXMes", this.listAltaProductosXMes());
        objListCantUserXRole.put("listCantUserXRole", this.listCantUserXRole());
        objListCantidadDeFacturasMes.put("listCantidadDeFacturasMes", this.listCantidadDeFacturasMes());
        objListTop5ProductosMasVendidos.put("listTop5ProductosMasVendidos", this.listTop5ProductosMasVendidos());
        objListTotalIngresosXMes.put("listTotalIngresosXMes", this.listTotalIngresosXMes());
        
        res.add(objListAltaProductosXMes);
        res.add(objListCantUserXRole);
        res.add(objListCantidadDeFacturasMes);
        res.add(objListTop5ProductosMasVendidos);
        res.add(objListTotalIngresosXMes);
        
        return res;
    }
}
