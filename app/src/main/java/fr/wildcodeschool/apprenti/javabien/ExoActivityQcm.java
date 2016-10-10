package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class ExoActivityQcm extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_qcm);

        //récupération de l'objet
        Intent intent = getIntent();
        final Contenant exo = (Contenant)intent.getSerializableExtra("amont");

        //mise en place du cours

        TextView info = (TextView)findViewById(R.id.info);
        info.setText(exo.getCours());

        //mise en place de la question

        TextView question = (TextView)findViewById(R.id.question);
        question.setText(exo.getQuestion());


        final Button boutonVrai=(Button)findViewById(R.id.boutonVrai); // Récupération de l'instance bouton 1
        boutonVrai.setText(exo.getPropositon());// mise en place du texte du boutton 1
        boutonVrai.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testAndToast(exo.getPropositon(),exo.getReponse(),getApplicationContext(),exo);
                    }
                }
        );

        Button boutonFaux=(Button)findViewById(R.id.boutonFaux); // Récupération de l'instance bouton 1
        boutonFaux.setText(exo.getProposition2()); //mise en place du texte du boutton 2
        boutonFaux.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               testAndToast(exo.getProposition2(),exo.getReponse(),getApplicationContext(),exo);
                                           }
                                       }
        );

        Button boutonFaux2=(Button)findViewById(R.id.boutonFaux2); // Récupération de l'instance bouton 1
        boutonFaux2.setText(exo.getProposition3()); // mise en place du texte du boutton 3
        boutonFaux2.setOnClickListener( new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                testAndToast(exo.getProposition3(),exo.getReponse(),getApplicationContext(),exo);
                                            }
                                        }
        );
        // récupération réponse
        String reponse = exo.getReponse();

        // vérification de la réponse

    }

    // Méthode déclecnchée par le listener lorsqu'on appui sur le bouton se produit
    public void testAndToast(String test, String reponse, final Context context, final Contenant exo){








        if (test.equals(reponse)){
            Toast.makeText(this,"Super", Toast.LENGTH_SHORT).show();
            //sauvegarde
            Sauvegarde.sauvegardeExo(exo,context);

            // apparition du bouton suivant et création de l'intent
            Button suivant = (Button)findViewById(R.id.suivant);
            suivant.setVisibility(View.VISIBLE);
            suivant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent adios = new Intent();
                    adios.putExtra("listExercices",ListCategorie.redirect(exo,exo.getId_exos(),getApplicationContext()));
                    setResult(1,adios);

                    finish();
                }
            });

        }else {
            Toast.makeText(this,"Faux", Toast.LENGTH_SHORT).show();
        }

    }


}