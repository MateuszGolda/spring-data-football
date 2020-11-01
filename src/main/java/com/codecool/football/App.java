package com.codecool.football;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner demo(TeamRepository teamRepository, MatchRepository matchRepository) {
        return (args -> {
            List<Team> teams = populateTeams(teamRepository);
            populateMatches(matchRepository, teams);

            log.info("All teams:");
            log.info("-------------------------------");
            for (Team team : teamRepository.findAll()) {
                log.info(team.toString());
            }
            log.info("");

            log.info("Best team:");
            log.info("-------------------------------");
            log.info(teamRepository.findBestTeam().toString());
            log.info("");

            log.info("Teams with name containing 'FC':");
            log.info("--------------------------------------------");
            teamRepository
                    .findAllByNameContains("FC")
                    .forEach(fc -> log.info(fc.toString()));
            log.info("");

            log.info("Teams played more than once:");
            log.info("--------------------------------------------");
            teamRepository
                    .findTeamsPlayedMoreThanOnce()
                    .forEach(t -> log.info(t.toString()));
            log.info("");

            log.info("Teams and their total goals:");
            log.info("--------------------------------------------");
            teamRepository
                    .findAll()
                    .forEach(fc -> {
                        log.info(fc.toString()
                                + " Goals: "
                                + teamRepository.findTotalGoalsByTeamId(fc.getTeamId()));
                    });
            log.info("");

            log.info("All matches:");
            log.info("-------------------------------");
            for (Match match : matchRepository.findAll()) {
                log.info(match.toString());
            }
            log.info("");

            log.info("Last week mMatches:");
            log.info("-------------------------------");
            for (Match match : matchRepository.findLastWeekMatches()) {
                log.info(match.toString());
            }
            log.info("");

            log.info("Match with most goals:");
            log.info("-------------------------------");
            log.info(matchRepository.findMatchWithMostGoals().toString());
            log.info("");
        });
    }

    private List<Team> populateTeams(TeamRepository teamRepository) {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("team_1", "some_country_1", "some_city_1"));
        teams.add(new Team("team_2", "some_country_2", "some_city_2"));
        teams.add(new Team("team_3", "some_country_3", "some_city_3"));
        teams.add(new Team("team_FC_4", "some_country_4", "some_city_4"));
        teamRepository.saveAll(teams);
        return teams;
    }

    private void populateMatches(MatchRepository matchRepository, List<Team> teams) {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match(Date.valueOf("2020-10-30"), teams.get(0), teams.get(1), 3, 0));
        matches.add(new Match(Date.valueOf("2020-09-16"), teams.get(0), teams.get(2), 2, 1));
        matches.add(new Match(Date.valueOf("2020-08-06"), teams.get(0), teams.get(2), 0, 1));
        matches.add(new Match(Date.valueOf("2020-08-17"), teams.get(1), teams.get(0), 2, 3));
        matches.add(new Match(Date.valueOf("2020-01-29"), teams.get(2), teams.get(1), 2, 0));
        matchRepository.saveAll(matches);
    }
}
