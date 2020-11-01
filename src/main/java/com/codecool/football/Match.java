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
