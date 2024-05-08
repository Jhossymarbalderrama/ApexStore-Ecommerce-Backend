/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.controller;

import com.backend.ecommerce.model.User;
import com.backend.ecommerce.security.controller.Message;
import com.backend.ecommerce.service.IUser;
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
public class UserController {
    
    @Autowired
    private IUser usrServ;
    
    /***
     * List Users
     * @return 
     */
    @GetMapping("/user/list")
    @ResponseBody
    public ResponseEntity<List<User>> listUsers(){
        List<User> listUsers;
        
        try{
            listUsers = usrServ.listUsers();
        }catch(Exception e){
            return new ResponseEntity(new Message("Error. Algo salio mal... !!!") + e.toString(), HttpStatus.FOUND);
        }
        
        return new ResponseEntity(listUsers, HttpStatus.OK);
    }
     
    /***
     * Get User ID
     * @param id
     * @return 
     */
    @GetMapping("/user/get/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id){
        Object usr;
        
        try{
            if(!usrServ.existById(id)){
                return new ResponseEntity(new Message("Error. No existe el ID indicado: " + id), HttpStatus.NOT_FOUND);
            }       
            usr = usrServ.findUser(id);
        }catch(Exception e){
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!!") + e.toString(), HttpStatus.FOUND);
        }
        
        return new ResponseEntity(usr, HttpStatus.OK);
    }
    
    /**
     * Get User Details for UserName
     * @param username
     * @return 
     */
    @GetMapping("/user/get/detail/{username}")
    public ResponseEntity<?> findUserDetails(@PathVariable String username){
        Object usr = new User(); 
        
        try{            
            if(username != null){
                usr = usrServ.findUserDetails(username);
            }
        }catch(Exception e){
            return new ResponseEntity(new Message("Error. Algo salio Mal... !!!") + e.toString(), HttpStatus.FOUND);
        }
        
        return new ResponseEntity(usr, HttpStatus.OK);
    }
    
    
    /***
     * Delete User
     * @param id
     * @return 
     */
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            if (!usrServ.existById(id)) {
                return new ResponseEntity(new Message("Error. No existe la Id: " + id), HttpStatus.NOT_FOUND);
            }

            usrServ.deleteUser(id);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error!. Algo salio mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(new Message("Elemento con la id: " + id + " eliminado correctamente."), HttpStatus.OK);
    }
    
    @PutMapping("/user/update")
    public ResponseEntity<?> updatePerson(@RequestBody User usr) {
        Object usrValid;
        try {
            if(!usrServ.existById(usr.getId())){
                return new ResponseEntity(new Message("Error!. No existe la Id: " + usr.getId()), HttpStatus.NOT_FOUND);
            }
            /*
            if(usr.getFirstname().equals("") || usr.getFirstname() == null){
                return new ResponseEntity(new Message("First name Required"), HttpStatus.BAD_REQUEST);
            }
            
            if(usr.getLastname().equals("") || usr.getLastname() == null){
                return new ResponseEntity(new Message("Last name Required"), HttpStatus.BAD_REQUEST);
            }           */ 
            
            usrValid = usrServ.updateUser(usr);
        } catch (Exception e) {
            return new ResponseEntity(new Message("Error!. Algo no salio mal... !!! ") + e.toString(), HttpStatus.FOUND);
        }

        return new ResponseEntity(usrValid, HttpStatus.OK);
    
    }
}
