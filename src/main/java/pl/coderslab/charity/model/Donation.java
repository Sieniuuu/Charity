package pl.coderslab.charity.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Donation {

    public interface addDonation {
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Proszę wpisać liczbę przygotowanych worków.", groups = {addDonation.class})
    @Min(value = 1, message = "Darowizna musi być zapakowana w conajmniej jeden worek.", groups = {addDonation.class})
    private Integer quantity;

    @NotNull(message = "Proszę wybrać conajmniej jedną kategorię darowizy." , groups = {addDonation.class})
    @ManyToMany
    private Set<Category> categories;

    @NotNull(message = "Proszę wybrać fundację na którą ma być przekazana darowizna." , groups = {addDonation.class})
    @OneToOne
    private Institution institution;

    @ManyToOne
    private User user;

    @Size(max = 50, groups = {addDonation.class})
    @NotBlank(message = "Proszę wpisać nazwę ulicy.", groups = {addDonation.class})
    private String street;

    @Size(max = 50)
    @NotBlank(message = "Prosze wpisac nazwe miasta." , groups = {addDonation.class})
    private String city;

    @NotBlank(message = "Prosze wpisac kod pocztowy." , groups = {addDonation.class})
    @Pattern(regexp = "^\\d{2}\\-\\d{3}$", message = "Wymagany format kodu pocztowego '00-000'." , groups = {addDonation.class})
    private String zipCode;

    @NotBlank(message = "Proszę wpisać numer telefon." , groups = {addDonation.class})
    @Pattern(regexp = "^\\d{9}$", message = "Wymagany format numeru telefonu '000-000-000'." , groups = {addDonation.class})
    private String phone;

    @NotNull(message = "Proszę wybrać datę odbioru darowizny." , groups = {addDonation.class})
    @Future(message = "Proszę ustawić przyszłą datę odbioru darowizny." , groups = {addDonation.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotNull(message = "Proszę wybrać ogdzine odbioru darwoizny." , groups = {addDonation.class})
    private LocalTime pickUpTime;

    private String pickUpComment;

    private boolean recived;

    public Donation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullAddress() {
        return String.join(", ", city, zipCode, street);
    }

    public String deliver() {
        return String.join(", ", String.valueOf(pickUpDate), String.valueOf(pickUpTime), pickUpComment);
    }

    public boolean isRecived() {
        return recived;
    }

    public void setRecived(boolean recived) {
        this.recived = recived;
    }
}
