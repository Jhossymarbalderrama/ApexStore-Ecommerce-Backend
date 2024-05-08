package com.backend.ecommerce.service;


import com.backend.ecommerce.model.User;
import java.util.List;

/**
 *
 * @author balde
 */
public interface IUser {
    public List<User> listUsers();
    
    public User findUser (Long id);
    
    public User saveUser (User usr);
    
    public void deleteUser (Long id);
    
    public  User updateUser (User usr);
    
    public  boolean existById(Long id);  
    
    public Object findUserDetails(String usrName);
}
