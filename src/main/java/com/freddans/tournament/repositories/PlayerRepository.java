package com.freddans.tournament.repositories;

import com.freddans.tournament.entities.Player;
import com.freddans.tournament.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
