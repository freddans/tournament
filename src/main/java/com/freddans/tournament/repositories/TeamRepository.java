package com.freddans.tournament.repositories;

import com.freddans.tournament.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByNameIgnoreCase(String name);
}
