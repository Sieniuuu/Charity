package pl.coderslab.charity.model;

import org.springframework.validation.annotation.Validated;
import pl.coderslab.charity.validation.EmailValidation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    public interface addUser { };

    public interface editUserDetails { };

    public interface editUserEmail { };

    public interface editUserPassword { };


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Proszę uzupełnić imię!", groups = {addUser.class, editUserDetails.class})
    private String firstName;

    @NotBlank(message = "Proszę uzupełnić nazwisko!", groups = {addUser.class, editUserDetails.class})
    private String lastName;

    @NotBlank(message = "Proszę uzupełnić email!", groups = {addUser.class, editUserEmail.class})
    @Email(message = "Proszę wpisać maila w formacie 'sample@sample.pl'", groups = {addUser.class, editUserEmail.class})
    @EmailValidation(message = "Podany mail jest już zajęty, proszę wybrać inny!", groups = {addUser.class, editUserEmail.class})
    private String email;

    @NotBlank (message = "Proszę uzupełnić hasło!" , groups = {addUser.class, editUserPassword.class})
    @Pattern(regexp = "(^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$)",
            message = "Hasło musi zawierać: jedną cyfer, jedną dużą i jedną małą literę oraz znak specialny ",
            groups = {addUser.class, editUserPassword.class})
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Donation> donations = new HashSet<>();

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JoinTable (name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return String.join(" ", firstName, lastName);
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Donation> getDonations() {
        return donations;
    }

    public void setDonations(Set<Donation> donations) {
        this.donations = donations;
    }


}
