package ro.kronsoft.training.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ro.kronsoft.training.repository.PatientRepository;

@Service
@Transactional
public class PatientService {

  @Autowired
  PatientRepository patientRepository;

  @PostConstruct
  void init() {
    findAllOrderByFirstName();
    System.out.println("-----------------------------------------");
    findAllPageAndOrder(0, 2);
    System.out.println("-----------------------------------------");
    deletePatientByFirstAndLastName("Dorel", "Antonoaie");
  }

  void findAllOrderByFirstName() {
    Sort sort = Sort.by(Direction.ASC, "firstName");
    this.patientRepository.findAll(sort).forEach(p -> System.out.println(p.toString()));
  }

  void findAllPageAndOrder(int page, int size) {
    Sort sort = Sort.by(Direction.ASC, "lastName", "firstName");
    PageRequest pageRequest = PageRequest.of(page, size, sort);
    this.patientRepository.findAll(pageRequest).forEach(p -> System.out.println(p.toString()));
  }

  void deletePatientByFirstAndLastName(String firstName, String lastName) {
    int numberDeleted = this.patientRepository.deleteByFirstNameAndLastName(firstName, lastName);
    System.out.println(numberDeleted + " record/s deleted.");
  }

}
