package liftup.user.controller;

import liftup.user.repository.QuoteRepository;
import liftup.user.model.QuoteOfTheDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteOfTheDayController {

    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/all-quotes")
    List<QuoteOfTheDay> getQuotes() {
        return quoteRepository.findAll();
    }

    @PostMapping("/add-quote")
    QuoteOfTheDay addQuote(@RequestBody QuoteOfTheDay quote) {
        return quoteRepository.save(quote);
    }

    //Randomize quotes
    @GetMapping("/all-quotes/random")
    QuoteOfTheDay getRandomQuote(){
        long count = quoteRepository.count();
        Random random = new Random();
        long num = random.nextInt((int) count);
        return quoteRepository.findByQuoteID(num + 1);

    }



}
