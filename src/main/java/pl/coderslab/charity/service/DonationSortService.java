package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.Collections;
import java.util.List;

@Service
public class DonationSortService {

    private final DonationRepository donationRepository;
    private final DonationService donationService;

    public DonationSortService(DonationRepository donationRepository,
                               DonationService donationService) {
        this.donationRepository = donationRepository;
        this.donationService = donationService;
    }

    public List<Donation> executeQuery(String searchMode, User user) {
        if (StringUtils.isEmpty(searchMode)) {
            return donationService.findAllByUserRevers(user);
        }
        switch (searchMode) {
            case "status":
                return donationRepository
                        .findDonationByUserOrderByRecivedAsc(user);
            case "id":
                return donationService
                        .findAllByUserRevers(user);
            case "pickUpDate":
                return donationRepository
                        .findDonationByUserAAndRecivedOrderByReciveDateDesc(user, true);
        }

        return Collections.emptyList();
    }

}

