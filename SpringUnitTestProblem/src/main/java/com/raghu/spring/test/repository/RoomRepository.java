package com.raghu.spring.test.repository;

import com.raghu.spring.test.ds.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoomRepository extends CrudRepository<Room, UUID> {

    Set<Room> findAll();

    Optional<Room> findByName(String name);
}
