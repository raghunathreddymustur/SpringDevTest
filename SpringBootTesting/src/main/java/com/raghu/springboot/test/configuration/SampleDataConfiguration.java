package com.raghu.springboot.test.configuration;

import com.raghu.springboot.test.ds.Room;
import com.raghu.springboot.test.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SampleDataConfiguration {

    public static final String GREEN_ROOM = "Green Room";
    public static final String YELLOW_ROOM = "Yellow Room";
    public static final String BLUE_ROOM = "Blue Room";

    @Autowired
    private RoomRepository roomRepository;

    @PostConstruct
    private void registerHotelRooms() {
        registerRoom(new Room(GREEN_ROOM, "A01"));
        registerRoom(new Room(YELLOW_ROOM, "A02"));
        registerRoom(new Room(BLUE_ROOM, "B01"));
    }

    private void registerRoom(Room room) {
        roomRepository.save(room);
    }
}
