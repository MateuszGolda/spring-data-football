package com.codecool.football;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "MATCHES")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long matchId;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Team home;

    @ManyToOne
    private Team away;

    private int goalsHome;

    private int goalsAway;

    public Match(Date date, Team home, Team away, int goalsHome, int goalsAway) {
        this.date = date;
        this.home = home;
        this.away = away;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    public Match() {
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team homeTeam) {
        this.home = homeTeam;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team awayTeam) {
        this.away = awayTeam;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", date=" + date +
                ", homeTeam=" + home +
                ", awayTeam=" + away +
                ", goalsHome=" + goalsHome +
                ", goalsAway=" + goalsAway +
                '}';
    }
}
