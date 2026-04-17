package com.santhosh.java.Repository;

import com.santhosh.java.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentModel, Long> {
}
