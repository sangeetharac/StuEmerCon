package launchcode.org.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import launchcode.org.domain.enumeration.ParentOrGuardien;

/**
 * A Parent.
 */
@Entity
@Table(name = "parent")
public class Parent implements Serializable {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "parent_or_guardien")
    private ParentOrGuardien parentOrGuardien;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_no")
    private Integer phoneNo;

    @Size(min = 3, max = 80)
    @Column(name = "address_line_1", length = 80)
    private String addressLine1;

    @Size(min = 3, max = 80)
    @Column(name = "address_line_2", length = 80)
    private String addressLine2;

    @Column(name = "zipcode")
    private Integer zipcode;

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

    public Parent firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Parent lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ParentOrGuardien getParentOrGuardien() {
        return parentOrGuardien;
    }

    public Parent parentOrGuardien(ParentOrGuardien parentOrGuardien) {
        this.parentOrGuardien = parentOrGuardien;
        return this;
    }

    public void setParentOrGuardien(ParentOrGuardien parentOrGuardien) {
        this.parentOrGuardien = parentOrGuardien;
    }

    public String getEmail() {
        return email;
    }

    public Parent email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNo() {
        return phoneNo;
    }

    public Parent phoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public Parent addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public Parent addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public Parent zipcode(Integer zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parent)) {
            return false;
        }
        return id != null && id.equals(((Parent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Parent{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", parentOrGuardien='" + getParentOrGuardien() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNo=" + getPhoneNo() +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", zipcode=" + getZipcode() +
            "}";
    }
}
