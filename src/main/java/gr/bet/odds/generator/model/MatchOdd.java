package gr.bet.odds.generator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Match odd entity.
 */
@Data
@NoArgsConstructor
@Entity()
public class MatchOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odd_seq_generator")
    @SequenceGenerator(name = "match_odd_seq_generator", sequenceName = "match_odd_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @NotNull
    @JsonBackReference
    private Match match;

    @Column
    @NotNull
    private String specifier;

    @Column
    @NotNull
    private Double odd;
}
