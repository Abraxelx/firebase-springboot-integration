package com.risaleinurdanvecizeler.quotesservice.controller;

import com.risaleinurdanvecizeler.quotesservice.model.Quotes;
import com.risaleinurdanvecizeler.quotesservice.service.QuoteService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class QuotesController {

    final QuoteService firebaseService;

    public QuotesController(QuoteService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @RequestMapping("/getQuotes")
    public Quotes getAlllQuotes() throws ExecutionException, InterruptedException {
        return firebaseService.getAllQuotes();
    }

    @RequestMapping("/fetchQuote")
    public Quotes getQuoteDetails(@RequestHeader() String text) throws ExecutionException, InterruptedException {
        return firebaseService.fetchQuoteDetails(text);
    }

    @PostMapping("/createQuote")
    public String postNewQuote(@RequestBody Quotes quote) throws ExecutionException, InterruptedException {
        return firebaseService.createNewQuote(quote);
    }

    @PutMapping("/updateQuote")
    public String updateQuote(@RequestBody Quotes quote) throws ExecutionException, InterruptedException {
        return firebaseService.updateQuote(quote);
    }

    @DeleteMapping("/deleteQuote")
    public String deleteUser(@RequestHeader String text) throws ExecutionException, InterruptedException {
        return firebaseService.deleteQuote(text);
    }

}
