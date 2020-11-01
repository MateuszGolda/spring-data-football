package com.codecool.football;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findAllByNameContains(String string);

    @Query(value = Queries.BEST_TEAM, nativeQuery = true)
    Team findBestTeam();

    @Query(value = Queries.TEAMS_PLAYED_MORE_THAN_ONES, nativeQuery = true)
    List<Team> findTeamsPlayedMoreThanOnce();

    @Query(value = Queries.GOALS_BY_TEAM_ID, nativeQuery = true)
    Integer findTotalGoalsByTeamId(@Param("teamId") Long teamId);
}
