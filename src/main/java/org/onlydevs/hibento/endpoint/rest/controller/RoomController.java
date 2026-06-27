package org.onlydevs.hibento.endpoint.rest.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.CreateRoom;
import org.onlydevs.hibento.endpoint.rest.controller.dto.request.UpdateRoom;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.CreatedRoom;
import org.onlydevs.hibento.endpoint.rest.controller.dto.response.UpdatedRoom;
import org.onlydevs.hibento.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<CreatedRoom> createRoom(@Valid @RequestBody CreateRoom request) {
    CreatedRoom saved = roomService.createRoom(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdatedRoom> updateRoom(
      @PathVariable UUID id, @Valid @RequestBody UpdateRoom request) {
    return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoom(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRoom(@PathVariable UUID id) {
    roomService.deleteRoom(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
