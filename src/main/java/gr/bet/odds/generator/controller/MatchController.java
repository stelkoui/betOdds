package gr.bet.odds.generator.controller;

import gr.bet.odds.generator.config.Constants;
import gr.bet.odds.generator.model.Match;
import gr.bet.odds.generator.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * The type Match controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchRepository matchRepository;

    /**
     * Gets all matches.
     * @return the all matches
     */
    @GetMapping()
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    /**
     * Gets by id.
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable Long id) {
        Optional<Match> match = matchRepository.findById(id);
        return match.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update match response entity.
     * @param match the match
     * @return the response entity
     */
    @PutMapping("/match")
    public ResponseEntity<Match> updateMatch(@RequestBody Match match) {
        Optional<Match> optionalMatch = matchRepository.findById(match.getId());
        return optionalMatch.isPresent() ? ResponseEntity.ok(matchRepository.save(match)) : ResponseEntity.notFound().build();
    }

    /**
     * Create match response entity.
     * @param match the match
     * @return the response entity
     */
    @PostMapping("/match")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        matchRepository.save(match);
        return ResponseEntity.created(URI.create(Constants.MATCH_LOCATION)).build();
    }

    /**
     * Delete match response entity.
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Match> deleteMatch(@PathVariable Long id) {
        matchRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
