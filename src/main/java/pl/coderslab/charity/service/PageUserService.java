package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class PageUserService  {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DonationRepository donationRepository;

    public PageUserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.donationRepository = donationRepository;
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByRole(String name) {
        return userRepository.findUsersByRoles(roleRepository.findByName(name));
    }

    public void createAdmin (User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public User findById (Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser (Long id) {
        User user = userRepository.findById(id).get();
        donationRepository.findByUser(user).forEach(donation -> {
            donation.setUser(null);
            donationRepository.save(donation);
        });
        userRepository.delete(user);
    }

    public void blockUser (Long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(0);
        userRepository.save(user);
    }

    public void unblockUser (Long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(1);
        userRepository.save(user);
    }

    public void commitEdit(User user) {
        userRepository.save(user);
    }

    public void commitPasswordEdit(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


}
