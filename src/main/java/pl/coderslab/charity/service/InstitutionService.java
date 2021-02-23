package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

    public InstitutionService(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    public Institution createInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    public Institution findById (Long id) { return institutionRepository.findById(id).get(); }

    public void deleteInstitution (Long id) {
        Institution institution = institutionRepository.findById(id).get();
        donationRepository.findByInstitution(institution).forEach(donation -> {
            donation.setInstitution(null);
            donationRepository.save(donation);
        });
        institutionRepository.delete(institution);
    }
}
