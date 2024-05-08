package com.backend.ecommerce.service;

import com.backend.ecommerce.model.Product;
import java.util.List;

/**
 *
 * @author balde
 */
public interface IProduct {
    
    public List<Product> listProducts();
    
    public Product findProduct (Long id);
    
    public Product saveProduct (Product prod);
    
    public void deleteProduct (Long id);
    
    public  Product updateProduct (Product prod);
    
    public  boolean existById(Long id);
       
    //public Product addImageToProduct(Long productId, MultipartFile image);
    
}
