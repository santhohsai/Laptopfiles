package com.santhosh.java.service;

import com.santhosh.java.Repository.PaymentRepo;
import com.santhosh.java.Repository.Productrepository;
import com.santhosh.java.model.PaymentModel;
import com.santhosh.java.model.Productmodel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Productservice
{
    @Autowired
    Productrepository productrepository;

    @Autowired
    PaymentRepo paymentrepo;

    public Productmodel  createdata(Productmodel productmodel)
    {
       return productrepository.save(productmodel);
    }

    public List<Productmodel> getData()
    {
        return productrepository.findAll();
    }

    public Productmodel getById(Long id)
    {
        Optional<Productmodel> product = productrepository.findById(id);

        if(!product.isPresent())
        {
            throw  new RuntimeException("product with this id  " +  id + " does not exists ") ;
        }
        else
        {
          return product.get() ;
        }

    }

    public Productmodel editDetails(Productmodel uproductmodel, Long id)
    {
       Optional<Productmodel> existingproduct = productrepository.findById(id) ;

       if(existingproduct.isPresent())
       {
          Productmodel  exspmodel = existingproduct.get();

          exspmodel.setName(uproductmodel.getName());
          exspmodel.setColour(uproductmodel.getColour());
          exspmodel.setModel(uproductmodel.getModel());
          exspmodel.setPassword(uproductmodel.getPassword());
          exspmodel.setPrice(uproductmodel.getPrice());
          exspmodel.setRole(uproductmodel.getRole());
          exspmodel.setStatus(uproductmodel.getStatus()) ;
          productrepository.save(exspmodel);

          return  exspmodel ;
       }
       else
       {
           throw new RuntimeException("This  product doesnot exists") ;
       }
    }

     public boolean deleteData(Long id)
    {
        Optional<Productmodel> deleteproduct = productrepository.findById(id);

        if(deleteproduct.isPresent())
        {
            Productmodel delproduct = deleteproduct.get();
            productrepository.deleteById(delproduct.getId()) ;

            return true;
        }
        return false;
    }

    @Transactional
    public boolean placeOrder(String model, int price, String mode,String name,String password,String color,String role,String status)
    {
        Productmodel modelobj = new Productmodel(name,password,price,model,color,role,status);
        productrepository.save(modelobj);

        PaymentModel paymodel = new PaymentModel(price, mode, "Initaited");
        paymentrepo.save(paymodel) ;

        if(price > 1000)
        {
            throw  new RuntimeException("amount should be less than 1000. so payment got failed") ;
        }

        modelobj.setStatus("confirmed");
        paymodel.setPaymentstatus("payment success");
        productrepository.save(modelobj);
        paymentrepo.save(paymodel);

      return true ;
    }

    public void deleteAll()
    {
        paymentrepo.deleteAll();
        productrepository.deleteAll();
    }



}
