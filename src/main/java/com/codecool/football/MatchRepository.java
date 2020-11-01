package com.codecool.football;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findAll();

    @Query(value = Queries.MATCH_MOST_GOALS, nativeQuery = true)
    Match findMatchWithMostGoals();

    @Query(value = Queries.LAST_WEEK_MATCHES, nativeQuery = true)
    List<Match> findLastWeekMatches();
}
