package com.flash.formationfirebase.firestore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.flash.formationfirebase.entities.Utilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UtilisateurDAOImplement {
    private final FirebaseFirestore firebaseFirestore;
    private final String collectionPath;

    public UtilisateurDAOImplement(DAO dao) {
        this.firebaseFirestore = dao.getInstance();
        this.collectionPath = Utilisateur.class.getSimpleName() + "s";
    }

    public void addUtilisateur(Utilisateur utilisateur) {
        this.firebaseFirestore
                .collection(this.collectionPath)
                .add(utilisateur)
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
    public void getListUtilisateurs(){
        this.firebaseFirestore
                .collection(this.collectionPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){

                                Utilisateur user = documentSnapshot.toObject(Utilisateur.class);

                                Log.v("firebase success", user.getEmail());
                            }
                        }else {
                            Log.v("firebase erreur", String.valueOf(task.getException()));
                        }
                    }
                });
    }
    public void updateUtilisateur(Utilisateur utilisateur, String documentPath){
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .set(utilisateur)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("firebase success", "Success update user");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("firebase error", "Error update user" + e);
                    }
                });
    }
    public void deleteUtilisateur(String documentPath){
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("firebase success", "Success delete user");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("firebase error", "Error delete user" + e);
                    }
                });
    }
}
