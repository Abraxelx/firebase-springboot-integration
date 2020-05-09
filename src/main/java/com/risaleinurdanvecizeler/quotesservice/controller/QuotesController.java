package com.risaleinurdanvecizeler.quotesservice.controller;

import com.risaleinurdanvecizeler.quotesservice.model.Quotes;
import com.risaleinurdanvecizeler.quotesservice.service.QuoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class QuotesController {

    final QuoteService firebaseService;

    public QuotesController(QuoteService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @RequestMapping("/getQuotes")
    public List<Quotes> getAlllQuotes() throws ExecutionException, InterruptedException {
        return firebaseService.getAllQuotes();
    }

    @RequestMapping("/fetchQuote")
    public Quotes getQuoteDetails(@RequestHeader() String text) throws ExecutionException, InterruptedException {
        return firebaseService.fetchQuoteDetails(text);
    }

    @RequestMapping("/searchQuote")
    public Quotes findQuote(@RequestBody String text) throws ExecutionException, InterruptedException {
        return firebaseService.findQuote(text);
    }

    @PostMapping("/createQuote")
    public String postNewQuote(@RequestBody Quotes quote) throws ExecutionException, InterruptedException {
        return firebaseService.createNewQuote(quote);
    }

    @PutMapping("/updateQuote/{id}")
    public String updateQuote(@RequestBody Quotes quote, @PathVariable String id) throws ExecutionException, InterruptedException {
        return firebaseService.updateQuote(quote, id);
    }

    @DeleteMapping("/deleteQuote")
    public String deleteUser(@RequestHeader String text) throws ExecutionException, InterruptedException {
        return firebaseService.deleteQuote(text);
    }

}
