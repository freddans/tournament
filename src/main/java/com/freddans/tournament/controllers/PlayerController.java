package com.freddans.tournament.controllers;

import com.freddans.tournament.entities.Player;
import com.freddans.tournament.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerService.findAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") long id) {
        return ResponseEntity.ok(playerService.findPlayerById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Player> create(@RequestBody Player newPlayer) {
        return ResponseEntity.ok(playerService.create(newPlayer));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Player> update(@PathVariable("id") long existingPlayerId, @RequestBody Player newPlayerInfo) {
        return ResponseEntity.ok(playerService.update(existingPlayerId, newPlayerInfo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(playerService.delete(id));
    }
}
