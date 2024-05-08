package com.backend.ecommerce.repository;

import com.backend.ecommerce.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author balde
 */
@Repository
public interface UserRepository  extends JpaRepository <User, Long>{
    Optional<User> findByUsername(String username);
}
