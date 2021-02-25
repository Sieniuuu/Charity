package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DonationService {

    private DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int sumQuantity() {
        return donationRepository.sumDonationsQuantity();
    }

    public int countAll() {
        return donationRepository.findAll().size();
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public Donation findById(Long id) { return donationRepository.findById(id).get(); }

    public List<Donation> findAllByUserRevers(User user){
        return donationRepository.findByUserOrderByIdDesc(user);
    }

    public Donation createDonation(Donation donation) {
        donation.setRecived(false);
        return donationRepository.save(donation);
    }

    public Donation createDonationWithUser(Donation donation, User user) {
        donation.setUser(user);
        donation.setRecived(false);
        return donationRepository.save(donation);
    }

    public List<Donation> reversDonationList() {
        return donationRepository.reversDonationList();
    }

    public void recived(Long id) {
        Donation donation = donationRepository.findById(id).get();
        donation.setRecived(true);
        donation.setReciveDate(LocalDate.now());
        donationRepository.save(donation);
    }

    public void unclimed(Long id) {
        Donation donation = donationRepository.findById(id).get();
        donation.setRecived(false);
        donationRepository.save(donation);
    }

}
