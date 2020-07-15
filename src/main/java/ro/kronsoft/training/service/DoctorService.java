package ro.kronsoft.training.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.kronsoft.training.entities.DocTypeEnum;
import ro.kronsoft.training.entities.Doctor;
import ro.kronsoft.training.entities.Patient;
import ro.kronsoft.training.repository.DoctorRepository;
import ro.kronsoft.training.repository.PatientRepository;

@Service
@Transactional
public class DoctorService {

  @Autowired
  DoctorRepository doctorRepository;

  @Autowired
  PatientRepository patientRepository;

  @PostConstruct
  void init() {
    createStartupData();
    System.out.println("-----------------------------------------");
    printAllDoctors();
    System.out.println("-----------------------------------------");
    printAllGmailAddresses();
    System.out.println("-----------------------------------------");
    findByFirstName("Ionel");
    System.out.println("-----------------------------------------");
    printAllPatientsForDoctor(8l);
    System.out.println("-----------------------------------------");
    printAllDoctorsByPatientLastName("POP");
    System.out.println("-----------------------------------------");
    updateDoctorEmail(5l, "aumarin@yahoo.ro");
  }

  void printAllDoctors() {
    System.out.println("Printing all doctors of doctors: ");
    List<Doctor> doctors = this.doctorRepository.findAll();
    doctors.forEach(d -> System.out.println(d.toString()));
  }

  void printAllGmailAddresses() {
    int count = this.doctorRepository.countAllWithGmailAddress();
    System.out.println(count + " doctors have Gmail adresses.");
  }

  void findByFirstName(String firstName) {
    Optional<Doctor> optDoc = doctorRepository.findByFirstNameIgnoreCase(firstName);
    if (optDoc.isPresent()) {
      DocTypeEnum docType = optDoc.get().getDocType();
      System.out.println("Found a doctor with first name " + firstName + " and he/she is a " + docType);
    } else {
      System.out.println("No doctor found with first name " + firstName);
    }
  }

  void printAllPatientsForDoctor(Long id) {
    Optional<Doctor> optDoc = this.doctorRepository.findByIdFull(id);
    if (optDoc.isPresent()) {
      Set<Patient> patients = optDoc.get().getPatients();
      patients.forEach(p -> System.out.println(p.toString()));
    } else {
      System.out.println("No doctor with id " + id);
    }
  }

  void printAllDoctorsByPatientLastName(String patientLastName) {
    List<Doctor> doctors = this.doctorRepository.findAllDoctorsByPatientName(patientLastName);
    System.out.println("All doctors that have patients with last name " + patientLastName);
    doctors.forEach(d -> System.out.println(d.toString()));
  }

  void updateDoctorEmail(Long id, String email) {
    Optional<Doctor> optDoc = this.doctorRepository.findById(id);
    Doctor doc;
    if (optDoc.isPresent()) {
      doc = optDoc.get();
    } else {
      System.out.println("No doctor with id " + id);
      return;
    }
    doc.setEmail(email);
    this.doctorRepository.save(doc);
  }

  void createStartupData() {
    try {
      Doctor doc1 = doctorRepository.save(new Doctor("Aurelia", "Marin", "marinau@yahoo.com", "Str. Cl. Bucuresti", 32, DocTypeEnum.PHYSICIAN));
      Doctor doc2 = doctorRepository.save(new Doctor("Ionel", "Marcu", "ionel.doctor@yahoo.com", "Str. Nucului", 182, DocTypeEnum.THERAPIST));
      Doctor doc3 = doctorRepository.save(new Doctor("Damian", "Olaru", "damian-olaru@gmail.com", "Str. Florii", 11, DocTypeEnum.PHYSICIAN));
      Doctor doc4 = doctorRepository.save(new Doctor("Maria", "Pavel", "pmaria@gmail.com", "Bl. Saturn", 58, DocTypeEnum.SURGEON));
      patientRepository.save(new Patient("Alexandru", "Pop", "0754865895", doc4));
      patientRepository.save(new Patient("Mihai", "Pop", "0785642536", doc4));
      patientRepository.save(new Patient("Dorel", "Antonoaie", "0786472355", doc3));
      patientRepository.save(new Patient("Mihai", "Maxim", "0796387451", doc2));
      patientRepository.save(new Patient("Oana", "Pop", "0714752369", doc2));
      patientRepository.save(new Patient("Mihaela", "Roman", "0769835412", doc1));
      patientRepository.save(new Patient("Marcel", "Constantin", "0723986572", doc1));
      patientRepository.save(new Patient("Iulia", "Popescu", "0723456897", doc1));
    } catch (Exception e) {
      System.out.println("Fail silently as this is just dummy data insertion method");
    }
  }

}
