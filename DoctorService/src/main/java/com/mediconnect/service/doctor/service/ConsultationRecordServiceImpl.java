package com.mediconnect.service.doctor.service;

import com.mediconnect.service.common_entities.entity.Enums.Role;
import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.common_entities.exception.DBException;
import com.mediconnect.service.common_entities.exception.UserNotFoundException;
import com.mediconnect.service.common_entities.repository.DoctorRepository;
import com.mediconnect.service.common_entities.repository.PatientRepository;
import com.mediconnect.service.doctor.repository.ConsultationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ConsultationRecordServiceImpl implements ConsultationRecordService {

    private final ConsultationRepository consultationRepository;

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    @Override
    @Transactional
    public void addConsultationRecord(PatientConsultationRecordDto patientConsultationRecordDto) {
        /*
        because of relationship mapping,

        The problem:
        we only have patientId and doctorId, we don’t want to fetch full Patient and Doctor entities bcoz no need, but JPA seems to force us to load them
        to avoid this unnecessary db call:

        Solution:
        Note - JPA needs ENTITY REFERENCES, not entity DATA
        so, use patientRepository.getReferenceById(patientId) instead of patientRepository.findById(patientId).get()
        What it does
	    •	Creates a proxy
	    •	Does NOT hit the database
	    •	Just holds the ID

	    but this comes with a problem - If the ID does not exist, the error will occur only when accessed (patientConsultationRecordDto.getPatient().getName()) , not immediately.

	    Solution:
	    use - patientRepository.existsById(patientId)
	    ✔ Fast (existsById uses indexed lookup)
        ✔ Clear error
        ✔ No unnecessary entity fetch

         */

        if(!patientRepository.existsById(patientConsultationRecordDto.getPatientId()))
            throw new UserNotFoundException("Patient not found with id: "+  patientConsultationRecordDto.getPatientId(), Role.PATIENT);

        if(!doctorRepository.existsById(patientConsultationRecordDto.getDoctorId()))
            throw new UserNotFoundException("Doctor not found with id: "+  patientConsultationRecordDto.getDoctorId(), Role.DOCTOR);


        /*
        Even with existsById() and getReferenceById(), race conditions(what if b/w the check time and insert time, patient is deleted), FK violations, transient entity issues,
        and transactional boundaries can cause failures.
        the best way to handle all of this is db level constrains of foreign key - DB enforce integrity
        Proper validation, DB constraints, and exception translation are essential.
         */

        try {
            consultationRepository.save(
                    PatientConsultationRecord.builder()
                            .patient(patientRepository.getReferenceById(patientConsultationRecordDto.getPatientId()))
                            .doctor(doctorRepository.getReferenceById(patientConsultationRecordDto.getDoctorId()))
                            .consultancyDateTime(LocalDateTime.now())
                            .age(1)
                            .weight(patientConsultationRecordDto.getWeight())
                            .symptoms(patientConsultationRecordDto.getSymptoms())
                            .clinicalDiagnosis(patientConsultationRecordDto.getClinicalDiagnosis())
                            .diagnosisReportsId(new ArrayList<>())
                            .medicinePrescriptionList(patientConsultationRecordDto.getMedicinePrescriptionList())
                            .build());
        } catch (Exception e) {
            throw new DBException("Unable to save consultation record in DB");
        }


    }
}
