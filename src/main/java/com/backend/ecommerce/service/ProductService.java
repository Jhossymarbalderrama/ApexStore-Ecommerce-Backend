package com.backend.ecommerce.service;

import com.backend.ecommerce.model.Product;
import com.backend.ecommerce.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author balde
 */
@Service
public class ProductService implements IProduct {

    @Autowired
    public ProductRepository prodRepo;

    @Override
    public List<Product> listProducts() {
        return prodRepo.findAll();
    }

    @Override
    public Product findProduct(Long id) {
        return prodRepo.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product prod) {
        return prodRepo.save(prod);
    }

    @Override
    public void deleteProduct(Long id) {
        prodRepo.deleteById(id);
    }

    @Override
    public Product updateProduct(Product prod) {
        return prodRepo.save(prod);
    }

    @Override
    public boolean existById(Long id) {
        return prodRepo.existsById(id);
    }


    /*
    @Override
    @Transactional  // Asegura que la operación se realice en una transacción
    public Product addImageToProduct(Long productId, MultipartFile multipartFile) {
        Product product = prodRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + productId));

        byte[] imageBytes = imageService.convertMultipartFileToByteArray(multipartFile);

        if (product.getImg() == null) {
            product.setImg(new ArrayList<>());
        }

        product.getImg().add(imageBytes);

        return prodRepo.save(product);
    }*/

}
