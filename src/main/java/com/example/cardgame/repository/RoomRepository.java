package com.example.cardgame.repository;

import com.example.cardgame.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    boolean existsBySimpleRoomId(String simpleRoomId);
    Optional<Room> findBySimpleRoomId(String simpleRoomId);
}
