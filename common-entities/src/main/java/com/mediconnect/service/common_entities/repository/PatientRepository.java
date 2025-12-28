package com.mediconnect.service.common_entities.repository;


import com.mediconnect.service.common_entities.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
