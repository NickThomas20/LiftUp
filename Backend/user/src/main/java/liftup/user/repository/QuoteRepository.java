package liftup.user.repository;

import liftup.user.model.QuoteOfTheDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<QuoteOfTheDay, Long> {
    QuoteOfTheDay findByQuoteID(long QuoteID);
}
