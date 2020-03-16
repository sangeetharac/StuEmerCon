package launchcode.org.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

import launchcode.org.domain.enumeration.Gender;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(name = "first_name", length = 80, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(name = "last_name", length = 80, nullable = false)
    private String lastName;

    @Max(value = 5)
    @Column(name = "grade")
    private Integer grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(name = "emr_1_first_name", length = 80, nullable = false)
    private String emr1FirstName;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(name = "emr_1_last_name", length = 80, nullable = false)
    private String emr1LastName;

    @Column(name = "emr_1_relation_ship")
    private String emr1RelationShip;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "emr_1_email", nullable = false)
    private String emr1Email;

    @Column(name = "emr_1_phone_no")
    private Integer emr1PhoneNo;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(name = "emr_2_first_name", length = 80, nullable = false)
    private String emr2FirstName;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(name = "emr_2_last_name", length = 80, nullable = false)
    private String emr2LastName;

    @Column(name = "emr_2_relation_ship")
    private String emr2RelationShip;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "emr_2_email", nullable = false)
    private String emr2Email;

    @Column(name = "emr_2_phone_no")
    private Integer emr2PhoneNo;

    @OneToOne
    @JoinColumn(unique = true)
    private Parent parent;

    @ManyToOne
    @JsonIgnoreProperties("students")
    private Teacher teacher;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Student lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public Student grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Gender getGender() {
        return gender;
    }

    public Student gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Student birthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmr1FirstName() {
        return emr1FirstName;
    }

    public Student emr1FirstName(String emr1FirstName) {
        this.emr1FirstName = emr1FirstName;
        return this;
    }

    public void setEmr1FirstName(String emr1FirstName) {
        this.emr1FirstName = emr1FirstName;
    }

    public String getEmr1LastName() {
        return emr1LastName;
    }

    public Student emr1LastName(String emr1LastName) {
        this.emr1LastName = emr1LastName;
        return this;
    }

    public void setEmr1LastName(String emr1LastName) {
        this.emr1LastName = emr1LastName;
    }

    public String getEmr1RelationShip() {
        return emr1RelationShip;
    }

    public Student emr1RelationShip(String emr1RelationShip) {
        this.emr1RelationShip = emr1RelationShip;
        return this;
    }

    public void setEmr1RelationShip(String emr1RelationShip) {
        this.emr1RelationShip = emr1RelationShip;
    }

    public String getEmr1Email() {
        return emr1Email;
    }

    public Student emr1Email(String emr1Email) {
        this.emr1Email = emr1Email;
        return this;
    }

    public void setEmr1Email(String emr1Email) {
        this.emr1Email = emr1Email;
    }

    public Integer getEmr1PhoneNo() {
        return emr1PhoneNo;
    }

    public Student emr1PhoneNo(Integer emr1PhoneNo) {
        this.emr1PhoneNo = emr1PhoneNo;
        return this;
    }

    public void setEmr1PhoneNo(Integer emr1PhoneNo) {
        this.emr1PhoneNo = emr1PhoneNo;
    }

    public String getEmr2FirstName() {
        return emr2FirstName;
    }

    public Student emr2FirstName(String emr2FirstName) {
        this.emr2FirstName = emr2FirstName;
        return this;
    }

    public void setEmr2FirstName(String emr2FirstName) {
        this.emr2FirstName = emr2FirstName;
    }

    public String getEmr2LastName() {
        return emr2LastName;
    }

    public Student emr2LastName(String emr2LastName) {
        this.emr2LastName = emr2LastName;
        return this;
    }

    public void setEmr2LastName(String emr2LastName) {
        this.emr2LastName = emr2LastName;
    }

    public String getEmr2RelationShip() {
        return emr2RelationShip;
    }

    public Student emr2RelationShip(String emr2RelationShip) {
        this.emr2RelationShip = emr2RelationShip;
        return this;
    }

    public void setEmr2RelationShip(String emr2RelationShip) {
        this.emr2RelationShip = emr2RelationShip;
    }

    public String getEmr2Email() {
        return emr2Email;
    }

    public Student emr2Email(String emr2Email) {
        this.emr2Email = emr2Email;
        return this;
    }

    public void setEmr2Email(String emr2Email) {
        this.emr2Email = emr2Email;
    }

    public Integer getEmr2PhoneNo() {
        return emr2PhoneNo;
    }

    public Student emr2PhoneNo(Integer emr2PhoneNo) {
        this.emr2PhoneNo = emr2PhoneNo;
        return this;
    }

    public void setEmr2PhoneNo(Integer emr2PhoneNo) {
        this.emr2PhoneNo = emr2PhoneNo;
    }

    public Parent getParent() {
        return parent;
    }

    public Student parent(Parent parent) {
        this.parent = parent;
        return this;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", grade=" + getGrade() +
            ", gender='" + getGender() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", emr1FirstName='" + getEmr1FirstName() + "'" +
            ", emr1LastName='" + getEmr1LastName() + "'" +
            ", emr1RelationShip='" + getEmr1RelationShip() + "'" +
            ", emr1Email='" + getEmr1Email() + "'" +
            ", emr1PhoneNo=" + getEmr1PhoneNo() +
            ", emr2FirstName='" + getEmr2FirstName() + "'" +
            ", emr2LastName='" + getEmr2LastName() + "'" +
            ", emr2RelationShip='" + getEmr2RelationShip() + "'" +
            ", emr2Email='" + getEmr2Email() + "'" +
            ", emr2PhoneNo=" + getEmr2PhoneNo() +
            "}";
    }
}
