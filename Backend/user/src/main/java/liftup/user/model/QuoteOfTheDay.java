package liftup.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuoteOfTheDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quoteID;
    private String quote;

    public QuoteOfTheDay() {

    }

    public QuoteOfTheDay(long quoteID, String quote) {
        this.quoteID = quoteID;
        this.quote = quote;
    }

    public long getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(long quoteID) {
        this.quoteID = quoteID;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
