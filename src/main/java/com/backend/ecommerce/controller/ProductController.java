package com.backend.ecommerce.controller;

import com.backend.ecommerce.model.Product;
import com.backend.ecommerce.security.controller.Message;
import com.backend.ecommerce.service.IProduct;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@CrossOrigin(origins = {"http://localhost:4200","https://e-commerce-ac291.web.app/"},maxAge = 3600, allowCredentials="true")
@RequestMapping("/api")
@Tag(name="Products", description="Controller for Products")
public class ProductController {

    @Autowired
    private IProduct prdServ;

    /**
     * *
     * List Products
     *
     * @return
     */
    @GetMapping("/product/list")
    @ResponseBody
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> listProducts;

        try {
            listProducts = prdServ.listProducts();
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(listProducts, HttpStatus.OK);
    }

    /**
     * *
     * Get Product
     *
     * @param id
     * @return
     */
    @GetMapping("/product/get/{id}")
    public ResponseEntity<?> findProduct(@PathVariable Long id) {
        Object prd;

        try {
            if (!prdServ.existById(id)) {
                return new ResponseEntity(new Message("Error. No existe el ID indicado: " + id), HttpStatus.NOT_FOUND);
            }
            prd = prdServ.findProduct(id);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!!") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(prd, HttpStatus.OK);
    }

    /***
     * Alta Product
     * @param prd
     * @return 
     */
    @PostMapping("/product/add")
    public ResponseEntity<?> saveProduct(@RequestBody Product prd) {
        Object prdValid;
        try {
            if (prd.getName().equals("") || prd.getName() == null) {
                return responseMessage(prd.getName());
            }

            if (prd.getDescription().equals("") || prd.getDescription() == null) {
                return responseMessage(prd.getDescription());
            }

            if (prd.getCategory().equals("") || prd.getCategory() == null) {
                return responseMessage(prd.getCategory());
            }

            if (prd.getPrice() == null || prd.getPrice() < 0) {
                return responseMessage(prd.getPrice().toString());
            }

            if (prd.getDiscount() == null || prd.getDiscount() < 0) {
                return responseMessage(prd.getDiscount().toString());
            }

            if (prd.getStock() == null || prd.getStock() < 0) {
                return responseMessage(prd.getStock().toString());
            }
            
            if (prd.getState().equals("") || prd.getState() == null) {
                return responseMessage(prd.getState());
            }
            
            if (prd.getDischarge_date().equals("") || prd.getDischarge_date() == null) {
                return responseMessage(prd.getDischarge_date());
            }
            

            prdValid = prdServ.saveProduct(prd);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(prdValid, HttpStatus.OK);
    }
    
    /***
     * Delete Product
     * @param id
     * @return 
     */
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            if (!prdServ.existById(id)) {
                return new ResponseEntity(new Message("Error. No existe la Id: " + id), HttpStatus.NOT_FOUND);
            }

            prdServ.deleteProduct(id);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error!. Algo salio mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(new Message("Elemento con la id: " + id + " eliminado correctamente."), HttpStatus.OK);
    }
    
    /***
     * Update Product
     * @param prd
     * @return 
     */
    @PutMapping("/product/update")
    public ResponseEntity<?> updatePerson(@RequestBody Product prd) {
        Object prdValid;      
        try {
            if(!prdServ.existById(prd.getId())){
                return new ResponseEntity(new Message("Error!. No existe la Id: " + prd.getId()), HttpStatus.NOT_FOUND);
            }
            
            if (prd.getName().equals("") || prd.getName() == null) {
                return responseMessage(prd.getName());
            }

            if (prd.getDescription().equals("") || prd.getDescription() == null) {
                return responseMessage(prd.getDescription());
            }

            if (prd.getCategory().equals("") || prd.getCategory() == null) {
                return responseMessage(prd.getCategory());
            }

            if (prd.getPrice() == null || prd.getPrice() < 0) {
                return responseMessage(prd.getPrice().toString());
            }

            if (prd.getDiscount() == null || prd.getDiscount() < 0) {
                return responseMessage(prd.getDiscount().toString());
            }

            if (prd.getStock() == null || prd.getStock() < 0) {
                return responseMessage(prd.getStock().toString());
            }
            
            if (prd.getState().equals("") || prd.getState() == null) {
                return responseMessage(prd.getState());
            }
            
            if (prd.getDischarge_date().equals("") || prd.getDischarge_date() == null) {
                return responseMessage(prd.getDischarge_date());
            }
            
            //if (prd.getImg() == null || prd.getImg().length == 0) {
            //    return responseMessage("IMG");
            //}
            
            prdValid = prdServ.updateProduct(prd);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error!. Algo salio mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(prdValid, HttpStatus.OK);    
    }
    
    private ResponseEntity responseMessage(String data) {
        return new ResponseEntity(new Message(data + " Required"), HttpStatus.BAD_REQUEST);
    }
   
    /*
   @PostMapping("/product/upload/{productId}")
    public ResponseEntity<?> uploadImageToProduct(@PathVariable Long productId,
                                                  @RequestParam("file") MultipartFile multipartFile) {
        
            System.out.println("___________________________________________");
            System.out.println("ID Product:" + productId);
            System.out.println("IMG:");
            System.out.println(multipartFile);
        try { 
            System.out.println("1");
            Product productWithImage = prdServ.addImageToProduct(productId, multipartFile);
            return ResponseEntity.ok(productWithImage);
            //return ResponseEntity.ok("File Upload Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar la imagen: " + e.getMessage());
        }
    }*/
}
