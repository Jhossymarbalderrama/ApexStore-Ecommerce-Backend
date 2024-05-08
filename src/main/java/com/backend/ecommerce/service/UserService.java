package com.backend.ecommerce.service;

import com.backend.ecommerce.model.User;
import com.backend.ecommerce.repository.UserRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author balde
 */
@Service
public class UserService implements IUser{

    @Autowired
    public UserRepository usrRepo;
    
    @Override
    public List<User> listUsers() {
        return usrRepo.findAll();
    }

    @Override
    public User findUser(Long id) {
        return usrRepo.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User usr) {
        return usrRepo.save(usr);
    }

    @Override
    public void deleteUser(Long id) {
        usrRepo.deleteById(id);
    }

    @Override
    public User updateUser(User usr) {
        return usrRepo.save(usr);
    }

    @Override
    public boolean existById(Long id) {
        return usrRepo.existsById(id);
    }
    
    @Override
    public Object findUserDetails(String usrName){
        return usrRepo.findByUsername(usrName);
    }
    
}
