package com.backend.ecommerce.repository;


import com.backend.ecommerce.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author balde
 */
@Repository
public interface FacturaRepository extends JpaRepository <Factura, Long>{    
}
