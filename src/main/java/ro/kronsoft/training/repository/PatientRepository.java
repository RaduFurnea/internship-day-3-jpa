package ro.kronsoft.training.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.kronsoft.training.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

  @Transactional
  int deleteByFirstNameAndLastName(String firstName, String lastName);

}
