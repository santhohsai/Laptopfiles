package com.santhosh.java.Restcontroller;

import com.santhosh.java.model.AuthRequest;
import com.santhosh.java.model.Productmodel;
import com.santhosh.java.service.JwtService;
import com.santhosh.java.service.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class Productcontroller
{
    @Autowired
    Productservice productservice ;

    @Autowired
    JwtService jwtservice;

    @Autowired
    AuthenticationManager authenticationManager;

     @GetMapping("/list")
     public List<Productmodel> getData()
     {
         return productservice.getData();
     }

     @PostMapping
    public Productmodel create(@RequestBody Productmodel productmodel)
     {
         return productservice.createdata(productmodel) ;
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getbyid(@PathVariable Long id)
     {
         try {
             Productmodel pmodel = productservice.getById(id);

             return ResponseEntity.ok(pmodel);
         }
         catch (RuntimeException e){
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body(Map.of("message" ,e.getMessage()));
     }
     }

     @PutMapping("/editdetails/{id}")
    public  ResponseEntity<?> editcourse(@PathVariable Long id, @RequestBody Productmodel existingpd)
     {
         try {
             Productmodel existingproduct = productservice.editDetails(existingpd ,id);
             return  ResponseEntity.ok(existingproduct);
         }
         catch(RuntimeException e)
         {
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body(Map.of("message",e.getMessage())) ;
         }
     }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean deletingProduct = productservice.deleteData(id);

        if (deletingProduct) {
            // Product existed and deleted
            return ResponseEntity.ok(Map.of("message", "Product with ID " + id + " deleted successfully"));
        } else {
            // Product not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Product with ID " + id + " not found"));
        }
    }

    @PostMapping("/placeanorder")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> doTransaction(@RequestParam String model, @RequestParam int price, @RequestParam String mode,@RequestParam String name,@RequestParam String password,@RequestParam String color, @RequestParam String role,@RequestParam String status) {
        try {
            boolean placed = productservice.placeOrder(model, price, mode,name,password,color,role,status);
            if (placed) {
                return ResponseEntity.ok("Product with model " + model + " saved in database");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order failed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<?> deletaAll()
    {
        productservice.deleteAll();
        return ResponseEntity.status(HttpStatus.GONE).body("all data  deleted successfulyy") ;
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest)
    {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPwd()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(jwtservice.genearateToken(authRequest.getUsername()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(403).body("invalid username or password") ;
        }
        return  ResponseEntity.status(401).body("authentication failed") ;
    }


}

