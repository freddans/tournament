package com.freddans.tournament.services;

import com.freddans.tournament.entities.Player;
import com.freddans.tournament.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);

        return optionalPlayer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Player with ID: " + id));
    }

    public Player create(Player newPlayer) {
        if (newPlayer.getFirstName() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing first name of Player");
        }
        if (newPlayer.getLastName() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing last name of Player");
        }
        if (newPlayer.getAlias() == null) {
            newPlayer.setAlias("N/A");
        }

        return playerRepository.save(newPlayer);
    }

    public Player update(long id, Player newPlayerInfo) {
        Player existingPlayer = findPlayerById(id);

        if (newPlayerInfo.getFirstName() != null && !newPlayerInfo.getFirstName().isEmpty()) {
            existingPlayer.setFirstName(newPlayerInfo.getFirstName());
        }
        if (newPlayerInfo.getLastName() != null && !newPlayerInfo.getLastName().isEmpty()) {
            existingPlayer.setLastName(newPlayerInfo.getLastName());
        }
        if (newPlayerInfo.getAlias() != null && !newPlayerInfo.getAlias().isEmpty()) {
            existingPlayer.setAlias(newPlayerInfo.getAlias());
        }

        return playerRepository.save(existingPlayer);
    }

    public String delete(long id) {
        Player playerToDelete = findPlayerById(id);

        playerRepository.delete(playerToDelete);

        return "Successfully deleted Player ID: " + id;
    }
}
