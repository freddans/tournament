package com.freddans.tournament.services;

import com.freddans.tournament.entities.Tournament;
import com.freddans.tournament.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament findTournamentById(long id) {
        Optional<Tournament> optionalTournament = tournamentRepository.findById(id);

        return optionalTournament.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Tournament with ID: " + id));
    }

    public Tournament findTournamentByName(String name) {
        Tournament tournament = tournamentRepository.findTournamentByNameIgnoreCase(name);

        if (tournament == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Tournament named: " + name);
        }

        return tournament;
    }

    public Tournament create(Tournament newTournament) {
        if (newTournament.getName() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing name of Tournament");
        }

        return tournamentRepository.save(newTournament);
    }

    public Tournament update(long id, String newTournamentName) {
        Tournament tournament = findTournamentById(id);

        if (newTournamentName == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing new name of Tournament");
        }

        tournament.setName(newTournamentName);

        return tournamentRepository.save(tournament);
    }

    public String delete(long id) {
        Tournament tournamentToDelete = findTournamentById(id);

        tournamentRepository.delete(tournamentToDelete);

        return "Successfully deleted Tournament ID: " + id;
    }
}
