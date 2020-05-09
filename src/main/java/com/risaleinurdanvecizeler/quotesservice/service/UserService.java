package com.risaleinurdanvecizeler.quotesservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.risaleinurdanvecizeler.quotesservice.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private DocumentReference documentReference;

    public String createNewUser(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("users").document().set(person);
       if(collectionsApiFuture.isDone()){
            return "Kullanıcı Başarıyla Eklendi. " +collectionsApiFuture.get().getUpdateTime().toString();
        }else if (collectionsApiFuture.isCancelled()) {
           return "Kullanıcı Ekleme İşlemi Başarısız Oldu.";
       }
       return "İşlem Başarısız.";

    }

    public Person fetchUserDetails(String username) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        documentReference = dbFireStore.collection("users").document(username);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        Person person = null;

        if (documentSnapshot.exists()) {
            person = documentSnapshot.toObject(Person.class);
            return person;
        } else {
            return null;
        }
    }

    public String updateUser(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("users").document().set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String username) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFireStore.collection("users").document(username).delete();
        return username + "User deleted";
    }
}


