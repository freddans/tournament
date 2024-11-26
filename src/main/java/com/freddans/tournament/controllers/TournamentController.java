package com.freddans.tournament.controllers;

import com.freddans.tournament.entities.Category;
import com.freddans.tournament.entities.Tournament;
import com.freddans.tournament.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.findAllTournaments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable("id") long id) {
        return ResponseEntity.ok(tournamentService.findTournamentById(id));
    }

    @GetMapping("/name/{tournament}")
    public ResponseEntity<Tournament> getTournamentByName(@PathVariable("tournament") String tournament) {
        return ResponseEntity.ok(tournamentService.findTournamentByName(tournament));
    }

    @PostMapping("/create")
    public ResponseEntity<Tournament> create(@RequestBody Tournament newTournament) {
        return ResponseEntity.ok(tournamentService.create(newTournament));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tournament> update(@PathVariable("id") long existingTournamentId, @RequestParam("name") String newTournamentName) {
        return ResponseEntity.ok(tournamentService.update(existingTournamentId, newTournamentName));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(tournamentService.delete(id));
    }
}
