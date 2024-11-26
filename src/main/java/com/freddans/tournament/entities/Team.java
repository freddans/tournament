package com.freddans.tournament.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "team_points")
    private int teamPoints;

    @ManyToMany
    @JoinTable(
            name = "player_team",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Player> players = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tournament_team",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Tournament> tournaments = new ArrayList<>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
        this.teamPoints = 0;
    }

    public Team(String name, List<Player> players) {
        this.name = name;
        this.teamPoints = 0;
        this.players = players;
    }

    public Team(String name, int teamPoints, List<Player> players, List<Tournament> tournaments) {
        this.name = name;
        this.teamPoints = teamPoints;
        this.players = players;
        this.tournaments = tournaments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamPoints() {
        return teamPoints;
    }

    public void setTeamPoints(int teamPoints) {
        this.teamPoints = teamPoints;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Tournament> getTournament() {
        return tournaments;
    }

    public void setTournament(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public String addOrRemovePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);

            return "Removed Player from Team";
        } else {
            players.add(player);

            return "Added Player to Team";
        }
    }
}
