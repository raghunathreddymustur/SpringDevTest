package com.raghu.test.annotations.service;

import com.raghu.test.annotations.ds.Room;
import com.raghu.test.annotations.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HotelManagementService {

    @Autowired
    private RoomRepository roomRepository;

    public void registerRoom(Room room) {
        roomRepository.save(room);
    }

    public Set<Room> listRooms() {
        return roomRepository.findAll();
    }
}
