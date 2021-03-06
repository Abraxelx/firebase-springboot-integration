package com.risaleinurdanvecizeler.quotesservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.risaleinurdanvecizeler.quotesservice.model.Quotes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class QuoteService {
    private DocumentReference documentReference;
    ApiFuture<DocumentSnapshot> future;
    private DocumentSnapshot elements;


    public List<Quotes> getAllQuotes() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection("quotes").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documentSnapshotList  = querySnapshot.getDocuments();
        List<Quotes> quotesList = new ArrayList<>();
            for (DocumentSnapshot elements : documentSnapshotList) {
                if (elements.exists()) {
                    quotesList.add(elements.toObject(Quotes.class));
                }
            }
                return quotesList;
    }

    public Quotes fetchQuoteDetails(String text) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        documentReference = dbFireStore.collection("quotes").document(text);
        future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        Quotes quote = null;

        if (documentSnapshot.exists()) {
            quote = documentSnapshot.toObject(Quotes.class);
            return quote;
        } else {
            return null;
        }
    }

    public String createNewQuote(Quotes quote) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("quotes").document().set(quote);
        if(collectionsApiFuture.isDone()){
            return "Vecize Başarıyla Eklendi. " +collectionsApiFuture.get().getUpdateTime().toString();
        }else if (collectionsApiFuture.isCancelled()) {
            return "Vecize Ekleme İşlemi Başarısız Oldu.";
        }
        return "İşlem Başarısız.";
    }

    public String updateQuote(Quotes quote, String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("quotes").document(id).set(quote);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteQuote(String text) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFireStore.collection("users").document(text).delete();
        return text + "User deleted";
    }

    public Quotes findQuote(String text) throws ExecutionException, InterruptedException {
        Quotes seekQuote = new Quotes();
        List<Quotes> quotesList = getAllQuotes();
        for (Quotes quote : quotesList){
            boolean isFound = quote.getQuote().contains(text);
            if(isFound == true){
                seekQuote = quote;
            }
        }
        return  seekQuote;
    }
}
