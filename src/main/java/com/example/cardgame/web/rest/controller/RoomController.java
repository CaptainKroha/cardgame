package com.example.cardgame.web.rest.controller;

import com.example.cardgame.model.CreateRoomRequest;
import com.example.cardgame.model.Room;
import com.example.cardgame.web.rest.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public String createRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        return roomService.createRoom(createRoomRequest);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomId) {
        return roomService.getRoomById(roomId)
                .map(room -> ResponseEntity.ok().body(room))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
