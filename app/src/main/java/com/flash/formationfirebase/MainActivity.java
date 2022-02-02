package com.flash.formationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.flash.formationfirebase.entities.Recette;
import com.flash.formationfirebase.entities.Utilisateur;
import com.flash.formationfirebase.firestore.DAO;
import com.flash.formationfirebase.firestore.RecetteDAOImplement;
import com.flash.formationfirebase.firestore.UtilisateurDAOImplement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    Utilisateur user = new Utilisateur("Marie Paul updated","maripaul@gmail.com");
        DAO dao = new DAO();

      //  UtilisateurDAOImplement utilisateurDAOImplement = dao.getUtilisateurDAOImplement();
       // utilisateurDAOImplement.addUtilisateur(user);
       // utilisateurDAOImplement.getListUtilisateurs();
      //  utilisateurDAOImplement.deleteUtilisateur("ANW7gzTpllD2vskoMIIR");

        Recette recette = new Recette("Libelle 1","Description 1","0u0CJoaJshKFtkPTvdie");

        RecetteDAOImplement recetteDAOImplement = dao.getRecetteDAOImplement();
        recetteDAOImplement.addRecette(recette);
    }
}