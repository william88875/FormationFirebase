package com.flash.formationfirebase.firestore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.flash.formationfirebase.entities.Recette;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RecetteDAOImplement {
    private final FirebaseFirestore firebaseFirestore;
    private final String collectionPath;

    public RecetteDAOImplement(DAO dao) {
        this.firebaseFirestore = dao.getInstance();
        this.collectionPath = Recette.class.getSimpleName() + "s";
    }

    public void addRecette(Recette recette) {
        this.firebaseFirestore
                .collection(this.collectionPath)
                .add(recette)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v("firebase success", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("firebase erreur", e.getMessage());
                    }
                });
    }
    public void getListRecettes(){
        this.firebaseFirestore
                .collection(this.collectionPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){

                                Recette recette = documentSnapshot.toObject(Recette.class);

                                Log.v("firebase success", recette.getUtilisateurId());
                            }
                        }else {
                            Log.v("firebase erreur", String.valueOf(task.getException()));
                        }
                    }
                });
    }
    public void updateRecette(Recette recette, String documentPath){
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .set(recette)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("firebase success", "Success update recette");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("firebase error", "Error update recette" + e);
                    }
                });
    }
    public void deleteRecette(String documentPath){
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("firebase success", "Success delete recette");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("firebase error", "Error delete recette" + e);
                    }
                });
    }
}
