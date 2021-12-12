package gr.bet.odds.generator.repository;

import gr.bet.odds.generator.model.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchOddRepository extends JpaRepository<MatchOdd, Long> {
}
