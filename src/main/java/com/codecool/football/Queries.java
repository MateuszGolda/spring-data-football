package com.codecool.football;

public class Queries {
    static final String BEST_TEAM =
            """
                    select * from TEAMS where TEAM_ID = (select TEAM_ID from
                    (select TEAM_ID, count(RESULT) as WINS
                    from (select HOME_TEAM_ID        as TEAM_ID,
                                 case
                                     when GOALS_AWAY < GOALS_HOME then 'win'
                                     when GOALS_AWAY > GOALS_HOME then 'lose'
                                     else 'draw' end as RESULT
                          from MATCHES
                          union all
                          select AWAY_TEAM_ID        as TEAM_ID,
                                 case
                                     when GOALS_AWAY > GOALS_HOME then 'win'
                                     when GOALS_AWAY < GOALS_HOME then 'lose'
                                     else 'draw' end as RESULT
                          from MATCHES)
                    where RESULT = 'win'
                    group by TEAM_ID
                    order by WINS desc
                    limit 1));
                    """;
    static final String MATCH_MOST_GOALS =
            """
                    SELECT *
                    FROM MATCHES
                    WHERE MATCH_ID = (SELECT MATCH_ID
                                      FROM (SELECT MATCH_ID, (GOALS_HOME + GOALS_AWAY) AS GOALS
                                            FROM MATCHES
                                            ORDER BY GOALS DESC
                                            LIMIT 1));
                    """;
    static final String TEAMS_PLAYED_MORE_THAN_ONES =
            """
                    SELECT TEAM_ID, CITY, COUNTRY, NAME FROM TEAMS LEFT JOIN
                    (SELECT TEAM_ID AS TEAM
                    FROM (SELECT TEAM_ID, COUNT(TEAM_ID) AS MATCHES_PLAYED
                          FROM (SELECT TEAM_ID
                                FROM TEAMS
                                         INNER JOIN MATCHES ON HOME_TEAM_ID = TEAM_ID
                                UNION ALL
                                SELECT TEAM_ID
                                FROM TEAMS
                                         INNER JOIN MATCHES ON AWAY_TEAM_ID = TEAM_ID)
                          GROUP BY TEAM_ID)
                    WHERE MATCHES_PLAYED > 1) ON TEAM = TEAM_ID;
                    """;
    static final String GOALS_BY_TEAM_ID =
            """
                    SELECT coalesce(SUM(GOALS), 0) as SUM
                    FROM (SELECT TEAM_ID, GOALS_HOME AS GOALS
                          FROM TEAMS
                                   INNER JOIN MATCHES ON HOME_TEAM_ID = TEAM_ID
                          UNION ALL
                          SELECT TEAM_ID, GOALS_AWAY AS GOALS
                          FROM TEAMS
                                   INNER JOIN MATCHES ON AWAY_TEAM_ID = TEAM_ID)
                    WHERE TEAM_ID = :teamId ;
                    """;
    static final String LAST_WEEK_MATCHES =
            """
                    SELECT *
                    FROM MATCHES
                    WHERE DATE BETWEEN now() - 7 AND now();
                    """;
}
