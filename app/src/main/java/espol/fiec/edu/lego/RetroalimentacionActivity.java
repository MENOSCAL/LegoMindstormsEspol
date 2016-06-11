package espol.fiec.edu.lego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import espol.fiec.edu.lego.domain.PreguntaRespuesta;

/**
 * Created by Usuario on 10/06/2016.
 */
public class RetroalimentacionActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private ArrayList<PreguntaRespuesta> listaRespuestasCorrectas;
    private static ArrayList<PreguntaRespuesta> listaRespuestasRespondidas;

    private TextView tvResultadoFinal;
    private TextView tvPregunta1,tvPregunta2, tvPregunta3, tvPregunta4, tvPregunta5;
    private TextView tvRespRespondida1, tvRespRespondida2, tvRespRespondida3, tvRespRespondida4, tvRespRespondida5;
    private TextView tvRespCorrecta1, tvRespCorrecta2, tvRespCorrecta3, tvRespCorrecta4, tvRespCorrecta5;

    private ViewGroup layoutRespuestaRespondida1, layoutRespuestaRespondida2, layoutRespuestaRespondida3, layoutRespuestaRespondida4, layoutRespuestaRespondida5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retroalimentacion);

        Bundle bolsaR = getIntent().getExtras();
        int puntaje = bolsaR.getInt("puntajeKey");


        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Resultado Final");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvResultadoFinal = (TextView) findViewById(R.id.txtResultadoFinal);

        tvPregunta1 = (TextView) findViewById(R.id.txtPregunta1);
        tvPregunta2 = (TextView) findViewById(R.id.txtPregunta2);
        tvPregunta3 = (TextView) findViewById(R.id.txtPregunta3);
        tvPregunta4 = (TextView) findViewById(R.id.txtPregunta4);
        tvPregunta5 = (TextView) findViewById(R.id.txtPregunta5);

        tvRespRespondida1 = (TextView) findViewById(R.id.txtRespuestaRespondida1);
        tvRespRespondida2 = (TextView) findViewById(R.id.txtRespuestaRespondida2);
        tvRespRespondida3 = (TextView) findViewById(R.id.txtRespuestaRespondida3);
        tvRespRespondida4 = (TextView) findViewById(R.id.txtRespuestaRespondida4);
        tvRespRespondida5 = (TextView) findViewById(R.id.txtRespuestaRespondida5);

        tvRespCorrecta1 = (TextView) findViewById(R.id.txtRespuestaCorrecta1);
        tvRespCorrecta2 = (TextView) findViewById(R.id.txtRespuestaCorrecta2);
        tvRespCorrecta3 = (TextView) findViewById(R.id.txtRespuestaCorrecta3);
        tvRespCorrecta4 = (TextView) findViewById(R.id.txtRespuestaCorrecta4);
        tvRespCorrecta5 = (TextView) findViewById(R.id.txtRespuestaCorrecta5);

        layoutRespuestaRespondida1 = (ViewGroup) findViewById(R.id.layoutRespuestaRespondida1);
        layoutRespuestaRespondida2 = (ViewGroup) findViewById(R.id.layoutRespuestaRespondida2);
        layoutRespuestaRespondida3 = (ViewGroup) findViewById(R.id.layoutRespuestaRespondida3);
        layoutRespuestaRespondida4 = (ViewGroup) findViewById(R.id.layoutRespuestaRespondida4);
        layoutRespuestaRespondida5 = (ViewGroup) findViewById(R.id.layoutRespuestaRespondida5);

        listaRespuestasCorrectas = TestActivity.listaRespuestasCorrectas;
        listaRespuestasRespondidas = TestActivity.listaRespuestasRespondidas;

        tvResultadoFinal.setText("Su puntaje final fue de: "+puntaje+"/100");

        //Primero lleno las textview de las preguntas
        tvPregunta1.setText(listaRespuestasCorrectas.get(0).getPregunta());
        tvPregunta2.setText(listaRespuestasCorrectas.get(1).getPregunta());
        tvPregunta3.setText(listaRespuestasCorrectas.get(2).getPregunta());
        tvPregunta4.setText(listaRespuestasCorrectas.get(3).getPregunta());
        tvPregunta5.setText(listaRespuestasCorrectas.get(4).getPregunta());


        for(int i=0; i<listaRespuestasCorrectas.size(); i++){
            PreguntaRespuesta pregRespRespondida = listaRespuestasRespondidas.get(i);
            PreguntaRespuesta pregRespCorrecta = listaRespuestasCorrectas.get(i);

            if(i==0){
                tvRespCorrecta1.setText(pregRespCorrecta.getRespuesta());
                tvRespRespondida1.setText(pregRespRespondida.getRespuesta());
            }
            if(i==1){
                tvRespCorrecta2.setText(pregRespCorrecta.getRespuesta());
                tvRespRespondida2.setText(pregRespRespondida.getRespuesta());
            }
            if(i==2){
                tvRespCorrecta3.setText(pregRespCorrecta.getRespuesta());
                tvRespRespondida3.setText(pregRespRespondida.getRespuesta());
            }
            if(i==3){
                tvRespCorrecta4.setText(pregRespCorrecta.getRespuesta());
                tvRespRespondida4.setText(pregRespRespondida.getRespuesta());
            }
            if(i==4){
                tvRespCorrecta5.setText(pregRespCorrecta.getRespuesta());
                tvRespRespondida5.setText(pregRespRespondida.getRespuesta());
            }

            if(pregRespRespondida.getRespuesta().equals(pregRespCorrecta.getRespuesta())){
                if(i==0){
                    layoutRespuestaRespondida1.setVisibility(View.GONE);
                }
                if(i==1){
                    layoutRespuestaRespondida2.setVisibility(View.GONE);
                }
                if(i==2){
                    layoutRespuestaRespondida3.setVisibility(View.GONE);
                }
                if(i==3){
                    layoutRespuestaRespondida4.setVisibility(View.GONE);
                }
                if(i==4){
                    layoutRespuestaRespondida5.setVisibility(View.GONE);
                }
            }
        }

    }
}
