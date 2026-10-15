package com.restro.controller;

import com.restro.dto.ApiResponse;
import com.restro.dto.ReservationRequest;
import com.restro.model.Reservation;
import com.restro.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Reservation>> createReservation(@Valid @RequestBody ReservationRequest request) {
        Reservation created = reservationService.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Table reservation confirmed successfully!", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Reservation>>> getAllReservations(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String email) {
        
        List<Reservation> reservations;
        if (date != null) {
            reservations = reservationService.getReservationsByDate(date);
        } else if (email != null && !email.isBlank()) {
            reservations = reservationService.getReservationsByEmail(email);
        } else {
            reservations = reservationService.getAllReservations();
        }
        return ResponseEntity.ok(ApiResponse.success(reservations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Reservation>> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(ApiResponse.success(reservation));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Reservation>> updateStatus(
            @PathVariable Long id, 
            @RequestParam Reservation.ReservationStatus status) {
        Reservation updated = reservationService.updateReservationStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Reservation status updated to " + status, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok(ApiResponse.success("Reservation cancelled successfully", null));
    }
}
