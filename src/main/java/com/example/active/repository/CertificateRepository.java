package com.example.active.repository;

import com.example.active.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    List<Certificate> findAllByUserID(Integer userID);
    Certificate findByID(Integer certificateID);
}
