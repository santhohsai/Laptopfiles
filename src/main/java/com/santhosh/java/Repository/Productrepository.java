package com.santhosh.java.Repository;

import com.santhosh.java.model.Productmodel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepository extends JpaRepository<Productmodel,Long>
{

}
