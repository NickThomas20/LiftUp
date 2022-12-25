package com.example.liftup.model;

public class Quote {

    private long quoteID;
    private String quote;

    public Quote() {
    }

    public Quote(long quoteID, String quote) {
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
