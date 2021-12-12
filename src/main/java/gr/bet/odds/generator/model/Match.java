package gr.bet.odds.generator.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Match.
 */
@Data
@NoArgsConstructor
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_seq_generator")
    @SequenceGenerator(name = "match_seq_generator", sequenceName = "match_seq", allocationSize = 1)
    private long id;

    @Column
    @NotNull
    private String description;
    @Column(name = "match_date")
    @NotNull
    private Date matchDate;
    @Column(name = "match_time")
    @NotNull
    private Time matchTime;
    @Column(name = "team_a")
    @NotNull
    private String teamA;
    @Column(name = "team_b")
    @NotNull
    private String teamB;
    @Column(name = "sport")
    @NotNull
    private SportEnum SPORT;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MatchOdd> matchOdds;

    /**
     * The enum Sport enum.
     */
    public enum SportEnum {
        /**
         * Football sport enum.
         */
        FOOTBALL,
        /**
         * Basketball sport enum.
         */
        BASKETBALL;
    }
}
