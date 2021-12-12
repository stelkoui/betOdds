package gr.bet.odds.generator.controller;

import gr.bet.odds.generator.config.Constants;
import gr.bet.odds.generator.model.MatchOdd;
import gr.bet.odds.generator.repository.MatchOddRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Match odd controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/matchOdds")
public class MatchOddController {
    
    private final MatchOddRepository matchOddsRepository;

    /**
     * Gets all match odds.
     * @return the all match odds
     */
    @GetMapping()
    public List<MatchOdd> getAllMatchOdds() {
        return matchOddsRepository.findAll();
    }

    /**
     * Gets by id.
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<MatchOdd> getById(@PathVariable Long id) {
        Optional<MatchOdd> matchOdd = matchOddsRepository.findById(id);
        return matchOdd.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update match odd response entity.
     * @param matchOdd the match odd
     * @return the response entity
     */
    @PutMapping("/matchOdd")
    public ResponseEntity<MatchOdd> updateMatchOdd(@RequestBody MatchOdd matchOdd) {
        Optional<MatchOdd> optionalMatchOdd = matchOddsRepository.findById(matchOdd.getId());
        return optionalMatchOdd.isPresent() ? ResponseEntity.ok(matchOddsRepository.save(matchOdd)) : ResponseEntity.notFound().build();
    }

    /**
     * Create match odd response entity.
     * @param matchOdd the match odd
     * @return the response entity
     */
    @PostMapping("/matchOdd")
    public ResponseEntity<MatchOdd> createMatchOdd(@RequestBody MatchOdd matchOdd) {
        matchOddsRepository.save(matchOdd);
        return ResponseEntity.created(URI.create(Constants.MATCH_ODDS_LOCATION)).build();
    }

    /**
     * Delete match odds response entity.
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MatchOdd> deleteMatchOdds(@PathVariable Long id) {
        matchOddsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
