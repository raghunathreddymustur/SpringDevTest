package com.raghu.springboot.test.service;

import com.google.common.collect.Sets;
import com.raghu.springboot.test.ds.Guest;
import com.raghu.springboot.test.ds.Reservation;
import com.raghu.springboot.test.ds.Room;
import com.raghu.springboot.test.repository.ReservationRepository;
import com.raghu.springboot.test.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    public Optional<Room> findAvailableRoom(LocalDate date) {
        Set<Room> allRooms = roomRepository.findAll();
        Set<Reservation> reservationsOnDate = reservationRepository.findAllByReservationDate(date);

        Set<Room> roomsReservedAtDate = reservationsOnDate.stream()
                .map(Reservation::getRoom)
                .collect(Collectors.toSet());

        Set<Room> roomsAvailableAtDate = Sets.difference(allRooms, roomsReservedAtDate).immutableCopy();

        return roomsAvailableAtDate.stream()
                .findAny();
    }

    public Optional<Reservation> bookRoom(String roomName, Guest guest, LocalDate date) {
        return bookRoom(roomRepository.findByName(roomName).get(), guest, date);
    }

    public Optional<Reservation> bookRoom(Room room, Guest guest, LocalDate date) {
        if (isRoomAvailableAtDate(room, date))
            return Optional.of(reservationRepository.save(
                    new Reservation(room, guest, date)
            ));
        else
            return Optional.empty();
    }

    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    private boolean isRoomAvailableAtDate(Room room, LocalDate date) {
        return !reservationRepository.existsByRoomAndReservationDate(room, date);
    }
}
