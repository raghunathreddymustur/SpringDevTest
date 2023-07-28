package com.raghu.spring.test.repository;

import com.raghu.spring.test.ds.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GuestRepository extends CrudRepository<Guest, UUID> {
}
