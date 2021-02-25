package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;

import java.util.List;

public interface DonationRepository extends JpaRepository <Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    int sumDonationsQuantity();

    @Query("SELECT d FROM Donation d ORDER BY d.id DESC")
    List<Donation> reversDonationList();

    @Query(nativeQuery = true, value = "SELECT * FROM charity_donation.donation ORDER BY donation.id DESC LIMIT ?1")
    List<Donation> reversDonationListWithLimit(int limit);

    List<Donation> findByInstitution(Institution institution);

    List<Donation> findByUser (User user);

    List<Donation> findByUserOrderByIdDesc (User user);

    @Query("SELECT d FROM Donation d WHERE d.user = :user ORDER BY d.recived DESC")
    List<Donation> findDonationByUserOrderByRecivedAsc(@Param("user") User user);

    @Query("SELECT d FROM Donation d WHERE d.user = :user " +
            "AND d.recived = :recived ORDER BY d.pickUpDate DESC")
    List<Donation> findDonationByUserAAndRecivedOrderByReciveDateDesc(@Param("user") User user,
    @Param("recived") boolean recived);
}
