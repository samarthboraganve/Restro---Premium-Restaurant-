package com.restro.service;

import com.restro.dto.ReservationRequest;
import com.restro.exception.ResourceNotFoundException;
import com.restro.model.Reservation;
import com.restro.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByDate(LocalDate date) {
        return reservationRepository.findByReservationDateOrderByReservationTimeAsc(date);
    }

    public List<Reservation> getReservationsByEmail(String email) {
        return reservationRepository.findByCustomerEmailIgnoreCaseOrderByReservationDateDesc(email);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    public Reservation createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation(
                request.getCustomerName().trim(),
                request.getCustomerEmail().trim().toLowerCase(),
                request.getCustomerPhone().trim(),
                request.getReservationDate(),
                request.getReservationTime().trim(),
                request.getGuestCount(),
                request.getSpecialRequests()
        );
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservationStatus(Long id, Reservation.ReservationStatus status) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus(Reservation.ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }
}
