package com.raghu.springboot.test.web;

import com.raghu.springboot.test.application.ApplicationService;
import com.raghu.springboot.test.ds.BookingRequest;
import com.raghu.springboot.test.ds.Guest;
import com.raghu.springboot.test.ds.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationServiceController {

    @Autowired
    private ApplicationService applicationService;

    @PutMapping("guests")
    public Guest registerGuest(@RequestBody Guest guest) {
        return applicationService.registerGuest(guest);
    }

    @GetMapping("guests")
    public List<Guest> listGuests() {
        return applicationService.listGuests();
    }

    @PutMapping("bookings")
    public void bookRoom(@RequestBody BookingRequest bookingRequest) {
        applicationService.bookAnyRoomForRegisteredGuest(bookingRequest.getGuest(), bookingRequest.getBookingDate());
    }

    @GetMapping("bookings")
    public List<Reservation> listReservations() {
        return applicationService.listReservations();
    }
}
