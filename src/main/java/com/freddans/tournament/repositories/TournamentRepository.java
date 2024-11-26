package com.freddans.tournament.repositories;

import com.freddans.tournament.entities.Category;
import com.freddans.tournament.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Tournament findTournamentByNameIgnoreCase(String name);
}
