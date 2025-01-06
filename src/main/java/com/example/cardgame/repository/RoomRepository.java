package com.example.cardgame.repository;

import com.example.cardgame.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoomRepository extends MongoRepository<Room, String> {

}
