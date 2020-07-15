package ro.kronsoft.training.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

@Entity
public class Patient extends BaseEntity {

  private static final long serialVersionUID = -188473368813065025L;

  @Column(name = "FIRST_NAME", nullable = false, length = 64)
  @Length(min = 0, max = 64)
  private String firstName;

  @Column(name = "LAST_NAME", nullable = false, length = 64)
  @Length(min = 0, max = 64)
  private String lastName;

  @Column(name = "PHONE_NUMBER", nullable = false, length = 32)
  @Length(min = 0, max = 32)
  private String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  public Patient(@Length(min = 0, max = 64) String firstName,
                 @Length(min = 0, max = 64) String lastName,
                 @Length(min = 0, max = 32) String phoneNumber,
                 Doctor doctor) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.doctor = doctor;
  }

  public Patient() {}

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  @Override
  public String toString() {
    return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + "]";
  }

}
