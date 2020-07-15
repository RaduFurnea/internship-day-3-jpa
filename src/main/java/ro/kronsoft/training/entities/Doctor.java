package ro.kronsoft.training.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

@Entity
public class Doctor extends BaseEntity {

  private static final long serialVersionUID = -1147056757488768262L;

  @Column(name = "FIRST_NAME", nullable = false, length = 64)
  @Length(min = 0, max = 64)
  private String firstName;

  @Column(name = "LAST_NAME", nullable = false, length = 64)
  @Length(min = 0, max = 64)
  private String lastName;

  @Column(name = "EMAIL", nullable = false, length = 64, unique = true)
  @Email
  @Length(min = 0, max = 64)
  private String email;

  @Column(name = "ADDRESS", nullable = false, length = 128)
  @Length(min = 0, max = 128)
  private String address;

  @Column(name = "HOUSE_NUMBER", nullable = false)
  private int houseNumber;

  @Column(name = "DOC_TYPE", length = 128)
  @Enumerated(EnumType.STRING)
  private DocTypeEnum docType;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.EAGER)
  private Set<Patient> patients = new HashSet<>();

  public Doctor(@Length(min = 0, max = 64) String firstName,
                @Length(min = 0, max = 64) String lastName,
                @Email @Length(min = 0, max = 64) String email,
                @Length(min = 0, max = 128) String address,
                int houseNumber,
                DocTypeEnum docType) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.houseNumber = houseNumber;
    this.docType = docType;
  }

  public Doctor() {}

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(int houseNumber) {
    this.houseNumber = houseNumber;
  }

  public DocTypeEnum getDocType() {
    return docType;
  }

  public void setDocType(DocTypeEnum docType) {
    this.docType = docType;
  }

  public Set<Patient> getPatients() {
    return patients;
  }

  public void setPatients(Set<Patient> patients) {
    this.patients = patients;
  }

  @Override
  public String toString() {
    return "Doctor [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", docType=" + docType + "]";
  }

}
