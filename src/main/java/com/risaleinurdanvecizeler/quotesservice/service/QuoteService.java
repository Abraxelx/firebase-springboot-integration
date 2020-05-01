package com.risaleinurdanvecizeler.quotesservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.risaleinurdanvecizeler.quotesservice.model.Person;
import com.risaleinurdanvecizeler.quotesservice.model.Quotes;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class QuoteService {
    private DocumentReference documentReference;

    public String createNewQuote(Quotes quote) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("quotes").document().set(quote);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Quotes fetchQuoteDetails(String text) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        documentReference = dbFireStore.collection("quotes").document(text);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        Quotes quote = null;

        if (documentSnapshot.exists()) {
            quote = documentSnapshot.toObject(Quotes.class);
            return quote;
        } else {
            return null;
        }
    }
    public String updateQuote(Quotes quote) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("quotes").document().set(quote);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteQuote(String text) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFireStore.collection("users").document(text).delete();
        return text + "User deleted";
    }
}
