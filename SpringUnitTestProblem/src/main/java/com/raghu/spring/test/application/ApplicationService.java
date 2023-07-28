package com.raghu.spring.test.application;


import com.raghu.spring.test.ds.BookingResult;
import com.raghu.spring.test.ds.Guest;
import com.raghu.spring.test.ds.Reservation;
import com.raghu.spring.test.ds.Room;
import com.raghu.spring.test.service.BookingService;
import com.raghu.spring.test.service.GuestRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private GuestRegistrationService guestRegistrationService;
    @Autowired
    private BookingService bookingService;

    public BookingResult bookAnyRoomForNewGuest(String firstName, String lastName, LocalDate date) {
        Optional<Room> availableRoom = bookingService.findAvailableRoom(date);

        if (availableRoom.isPresent()) {
            Guest registeredGuest = registerGuest(firstName, lastName);

            return BookingResult.createRoomBookedResult(
                    bookingService.bookRoom(availableRoom.get(), registeredGuest, date).get()
            );
        } else
            return BookingResult.createNoRoomAvailableResult();
    }

    public Guest registerGuest(String firstName, String lastName) {
        Guest guestToRegister = new Guest(firstName, lastName);
        return guestRegistrationService.registerGuest(guestToRegister);
    }

    public BookingResult bookAnyRoomForRegisteredGuest(Guest guest, LocalDate date) {
        Optional<Room> availableRoom = bookingService.findAvailableRoom(date);

        if (availableRoom.isPresent()) {
            return BookingResult.createRoomBookedResult(
                    bookingService.bookRoom(availableRoom.get(), guest, date).get()
            );
        } else
            return BookingResult.createNoRoomAvailableResult();
    }

    public BookingResult bookSpecificRoomForRegisteredGuest(Guest guest, String roomName, LocalDate date) {
        Optional<Reservation> reservation = bookingService.bookRoom(roomName, guest, date);

        return reservation
                .map(BookingResult::createRoomBookedResult)
                .orElseGet(BookingResult::createNoRoomAvailableResult);
    }
}
