package com.backend.ecommerce.repository;

import com.backend.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author balde
 */
@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
    
}
