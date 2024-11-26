package com.freddans.tournament.controllers;

import com.freddans.tournament.entities.Category;
import com.freddans.tournament.entities.Team;
import com.freddans.tournament.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") long id) {
        return ResponseEntity.ok(teamService.findTeamById(id));
    }

    @GetMapping("/name/{team}")
    public ResponseEntity<Team> getTeamByName(@PathVariable("team") String team) {
        return ResponseEntity.ok(teamService.findTeamByName(team));
    }

    @PostMapping("/create")
    public ResponseEntity<Team> create(@RequestBody Team newTeam) {
        return ResponseEntity.ok(teamService.create(newTeam));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Team> update(@PathVariable("id") long existingTeamId, @RequestParam("name") String newTeamName) {
        return ResponseEntity.ok(teamService.update(existingTeamId, newTeamName));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(teamService.delete(id));
    }

    @PutMapping("/addPlayerToTeam/{id}")
    public ResponseEntity<String> addPlayerToTeam(@PathVariable("id") long teamId, @RequestParam("playerId") long playerId) {
        return ResponseEntity.ok(teamService.addOrRemovePlayer(teamId, playerId));
    }
}
