package ro.kronsoft.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ro.kronsoft.training.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

  Optional<Doctor> findByEmail(String email);

  Optional<Doctor> findByFirstNameIgnoreCase(String firstName);

  @Query(nativeQuery = true, value = "SELECT count(*) FROM doctor WHERE email ilike '%gmail%'")
  int countAllWithGmailAddress();

  @Query("SELECT d FROM Doctor d LEFT JOIN FETCH d.patients p WHERE d.id = :id ")
  Optional<Doctor> findByIdFull(@Param("id") Long id);

  @Query("SELECT DISTINCT d FROM Doctor d LEFT JOIN FETCH d.patients p WHERE lower(p.lastName) = lower(:patientLastName)")
  List<Doctor> findAllDoctorsByPatientName(@Param("patientLastName") String patientLastName);
}
