package com.freddans.tournament.services;

import com.freddans.tournament.entities.Player;
import com.freddans.tournament.entities.Team;
import com.freddans.tournament.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    private PlayerService playerService;

    @Autowired
    public TeamService(TeamRepository teamRepository, PlayerService playerService) {
        this.teamRepository = teamRepository;
        this.playerService = playerService;
    }

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Team findTeamById(long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);

        return optionalTeam.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Team with ID: " + id));
    }

    public Team findTeamByName(String name) {
        Team team = teamRepository.findTeamByNameIgnoreCase(name);

        if (team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Team named: " + name);
        }

        return team;
    }

    public Team create(Team newTeam) {
        if (newTeam.getName() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing name of Team");
        }

        return teamRepository.save(newTeam);
    }

    public Team update(long id, String newTeamName) {
        Team team = findTeamById(id);

        if (newTeamName == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing new name of Team");
        }

        team.setName(newTeamName);

        return teamRepository.save(team);
    }

    public String delete(long id) {
        Team teamToDelete = findTeamById(id);

        teamRepository.delete(teamToDelete);

        return "Successfully deleted Team ID: " + id;
    }

    public String addOrRemovePlayer(long teamId, long playerId) {
        Team team = findTeamById(teamId);
        Player player = playerService.findPlayerById(playerId);

        return team.addOrRemovePlayer(player);
    }
}
