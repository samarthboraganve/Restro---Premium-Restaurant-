package com.restro.repository;

import com.restro.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationDateOrderByReservationTimeAsc(LocalDate reservationDate);
    List<Reservation> findByCustomerEmailIgnoreCaseOrderByReservationDateDesc(String customerEmail);
    List<Reservation> findByStatusOrderByReservationDateDesc(Reservation.ReservationStatus status);
}
