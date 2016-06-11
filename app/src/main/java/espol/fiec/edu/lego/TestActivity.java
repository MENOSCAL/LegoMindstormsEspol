package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import espol.fiec.edu.lego.domain.Pregunta;
import espol.fiec.edu.lego.domain.PreguntaRespuesta;
import espol.fiec.edu.lego.domain.Respuesta;

/**
 * Created by Usuario on 16/04/2016.
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ViewGroup layoutParent;



    private Button btnFinalizar;


    private RadioGroup rgPregunta1, rgPregunta2, rgPregunta3, rgPregunta4, rgPregunta5;
    private RadioButton respPregunta1, respPregunta2, respPregunta3, respPregunta4, respPregunta5;

    private int puntaje;

    private String tallerName;
    private int idTaller;

    //Web services
    private WebServicesConfiguration wsConf;

    private ArrayList<Pregunta> listPreguntas;
    private ArrayList<Respuesta> listRespuestasPreg1;
    private ArrayList<Respuesta> listRespuestasPreg2;
    private ArrayList<Respuesta> listRespuestasPreg3;
    private ArrayList<Respuesta> listRespuestasPreg4;
    private ArrayList<Respuesta> listRespuestasPreg5;

    public static ArrayList<PreguntaRespuesta> listaRespuestasCorrectas;
    public static ArrayList<PreguntaRespuesta> listaRespuestasRespondidas;

    private GetPreguntasTask getPreguntasTask;
    private GetRespuestasTask getRespuestasTask;
    private InsertUserTallerTask insertTallerUserTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_taller);

        Bundle bolsaR = getIntent().getExtras();
        tallerName = bolsaR.getString("tallerNameKey");
        idTaller = bolsaR.getInt("tallerIdKey");

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Test "+ tallerName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaRespuestasCorrectas =  new ArrayList<PreguntaRespuesta>();
        listaRespuestasRespondidas =  new ArrayList<PreguntaRespuesta>();

        listPreguntas =  new ArrayList<Pregunta>();
        listRespuestasPreg1 = new ArrayList<Respuesta>();
        listRespuestasPreg2 = new ArrayList<Respuesta>();
        listRespuestasPreg3 = new ArrayList<Respuesta>();
        listRespuestasPreg4 = new ArrayList<Respuesta>();
        listRespuestasPreg5 = new ArrayList<Respuesta>();

        wsConf = (WebServicesConfiguration) getApplicationContext();

        getPreguntasTask = new GetPreguntasTask();
        getPreguntasTask.execute((Void) null);

        puntaje = 0;

        rgPregunta1 = (RadioGroup) findViewById(R.id.rgPregunta1);
        rgPregunta2 = (RadioGroup) findViewById(R.id.rgPregunta2);
        rgPregunta3 = (RadioGroup) findViewById(R.id.rgPregunta3);
        rgPregunta4 = (RadioGroup) findViewById(R.id.rgPregunta4);
        rgPregunta5 = (RadioGroup) findViewById(R.id.rgPregunta5);

        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(this);
    }

    public void addPreguntaFromServer(String textPregunta, int numeroPregunta, int idPregunta){
        String txtPreguntaId = "txtPregunta"+numeroPregunta;
        TextView tvPregunta = (TextView) findViewById(getResources().getIdentifier(txtPreguntaId, "id", getPackageName()));
        tvPregunta.setText(textPregunta);


        wsConf = (WebServicesConfiguration) getApplicationContext();

        getRespuestasTask = new GetRespuestasTask();
        //getRespuestasTask.execute((Void) null);

        getRespuestasTask.execute(idPregunta, numeroPregunta);
    }

    public void addOpcionesPregunta(int numeroPregunta, int numOpcion, String textOpcion){
        String rbId = "rbPregunta"+numeroPregunta+"Opcion"+numOpcion;
        RadioButton resppregunta = (RadioButton) findViewById(getResources().getIdentifier(rbId, "id", getPackageName()));

        resppregunta.setText(textOpcion);
    }

    public boolean checkAnswer(String opcion, ArrayList<Respuesta> listRespuestas){
        for(int i=0; i<4; i++){
            Respuesta respuesta = listRespuestas.get(i);

            if(respuesta.getName().equals(opcion)  && respuesta.isValido()){
                return true;
            }
        }

        return false;
    }

    public ArrayList<PreguntaRespuesta> getRespuestasCorrectas(){

        ArrayList<PreguntaRespuesta> respuestasCorrectas = new ArrayList<PreguntaRespuesta>();

        PreguntaRespuesta pregResp1 = new PreguntaRespuesta(listPreguntas.get(0).getName(), getRespuestaCorrecta(listRespuestasPreg1));
        respuestasCorrectas.add(pregResp1);

        PreguntaRespuesta pregResp2 = new PreguntaRespuesta(listPreguntas.get(1).getName(), getRespuestaCorrecta(listRespuestasPreg2));
        respuestasCorrectas.add(pregResp2);

        PreguntaRespuesta pregResp3 = new PreguntaRespuesta(listPreguntas.get(2).getName(), getRespuestaCorrecta(listRespuestasPreg3));
        respuestasCorrectas.add(pregResp3);

        PreguntaRespuesta pregResp4 = new PreguntaRespuesta(listPreguntas.get(3).getName(), getRespuestaCorrecta(listRespuestasPreg4));
        respuestasCorrectas.add(pregResp4);

        PreguntaRespuesta pregResp5 = new PreguntaRespuesta(listPreguntas.get(4).getName(), getRespuestaCorrecta(listRespuestasPreg5));
        respuestasCorrectas.add(pregResp5);

        return respuestasCorrectas;
    }

    public String getRespuestaCorrecta(ArrayList<Respuesta> listRespuestas){

        for(int i=0; i<listRespuestas.size(); i++){
            Respuesta respuesta = listRespuestas.get(i);

            if(respuesta.isValido()){
                return respuesta.getName();
            }
        }
        return null;
    }

    public ArrayList<Respuesta> shuffleListRespuestaoptions(ArrayList<Respuesta> listRespuestas){

        boolean cent = true;

        do {
            long seed = System.nanoTime();
            Collections.shuffle(listRespuestas, new Random(seed));

            for (int i = 0; i < 4; i++) {
                Respuesta respuesta = listRespuestas.get(i);

                if (respuesta.isValido()) {
                    cent = false;
                }
            }

        }while(cent);

        return listRespuestas;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFinalizar: {
                puntaje = 0;

                int selectedPregunta1 = rgPregunta1.getCheckedRadioButtonId();
                int selectedPregunta2 = rgPregunta2.getCheckedRadioButtonId();
                int selectedPregunta3 = rgPregunta3.getCheckedRadioButtonId();
                int selectedPregunta4 = rgPregunta4.getCheckedRadioButtonId();
                int selectedPregunta5 = rgPregunta5.getCheckedRadioButtonId();

                if(selectedPregunta1 == -1 || selectedPregunta2 == -1 || selectedPregunta3 == -1 || selectedPregunta4 == -1 || selectedPregunta5 == -1){
                    Toast.makeText(getApplicationContext(), "Por favor, debe llenar todas las preguntas", Toast.LENGTH_SHORT).show();
                }
                else {
                    respPregunta1 = (RadioButton) findViewById(selectedPregunta1);
                    String resp1 = (String) respPregunta1.getText();
                    if(checkAnswer(resp1, listRespuestasPreg1)){
                        puntaje += 20;
                    }

                    respPregunta2 = (RadioButton) findViewById(selectedPregunta2);
                    String resp2 = (String) respPregunta2.getText();
                    if(checkAnswer(resp2, listRespuestasPreg2)){
                        puntaje += 20;
                    }

                    respPregunta3 = (RadioButton) findViewById(selectedPregunta3);
                    String resp3 = (String) respPregunta3.getText();
                    if(checkAnswer(resp3, listRespuestasPreg3)){
                        puntaje += 20;
                    }

                    respPregunta4 = (RadioButton) findViewById(selectedPregunta4);
                    String resp4 = (String) respPregunta4.getText();
                    if(checkAnswer(resp4, listRespuestasPreg4)){
                        puntaje += 20;
                    }

                    respPregunta5 = (RadioButton) findViewById(selectedPregunta5);
                    String resp5 = (String) respPregunta5.getText();
                    if(checkAnswer(resp5, listRespuestasPreg5)){
                        puntaje += 20;
                    }

                    wsConf = (WebServicesConfiguration) getApplicationContext();

                    insertTallerUserTask = new InsertUserTallerTask();
                    insertTallerUserTask.execute((Void) null);

                    //Guardo las respuestas que el usuario respondió
                    PreguntaRespuesta pregResp1 = new PreguntaRespuesta(listPreguntas.get(0).getName(), resp1);
                    listaRespuestasRespondidas.add(pregResp1);

                    PreguntaRespuesta pregResp2 = new PreguntaRespuesta(listPreguntas.get(1).getName(), resp2);
                    listaRespuestasRespondidas.add(pregResp2);

                    PreguntaRespuesta pregResp3 = new PreguntaRespuesta(listPreguntas.get(2).getName(), resp3);
                    listaRespuestasRespondidas.add(pregResp3);

                    PreguntaRespuesta pregResp4 = new PreguntaRespuesta(listPreguntas.get(3).getName(), resp4);
                    listaRespuestasRespondidas.add(pregResp4);

                    PreguntaRespuesta pregResp5 = new PreguntaRespuesta(listPreguntas.get(4).getName(), resp5);
                    listaRespuestasRespondidas.add(pregResp5);


                    //Aqui antes de ir a la siguiente activity, debo guardar en un arreglo las respuestas del usuario y las correctas en otro arreglo
                    listaRespuestasCorrectas = getRespuestasCorrectas();

                    //Me dirijo a la pantalla de retroalimentación
                    Intent i = new Intent(getApplicationContext(), RetroalimentacionActivity.class);

                    Bundle bolsa=new Bundle();
                    bolsa.putInt("puntajeKey", puntaje);
                    i.putExtras(bolsa);

                    startActivity(i);

                    Toast.makeText(getApplicationContext(), "Su calificación final fue de "+puntaje+"/100", Toast.LENGTH_SHORT).show();
                }

                break;
            }
        }
    }

    /**
     * Represents an asynchronous  task used to get preguntas from data base
     */
    public class GetPreguntasTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_PREGUNTAS());
                //Agregando parametros del método
                request.addProperty("idTaller",Integer.toString(idTaller));

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_TALLERES(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Vector<?> responseVector = (Vector<?>) response.getProperty(0);

                for (int i = 0; i <responseVector.size(); ++i) {
                    SoapObject datos =(SoapObject)responseVector.get(i);
                    listPreguntas.add(new Pregunta(datos.getProperty("idPregunta").toString(),datos.getProperty("Taller_idTaller").toString(),datos.getProperty("Name").toString()));

                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Respuesta", "excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

           for(int i=0; i<listPreguntas.size(); i++){
               Pregunta preg = listPreguntas.get(i);
               addPreguntaFromServer(preg.getName(),i+1,  preg.getIdPregunta());
           }
        }

        @Override
        protected void onCancelled() {
        }
    }

    /**
     * Represents an asynchronous  task used to get respuestas from data base
     */
    public class GetRespuestasTask extends AsyncTask<Integer, Integer, Boolean> {

        private int idPreguntaAsyncTask;
        private int numPreguntaAsyncTask;

        @Override
        protected Boolean doInBackground(Integer... params) {
            // TODO: attempt authentication against a network service.
            idPreguntaAsyncTask = params[0];
            numPreguntaAsyncTask = params[1];

            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_RESPUESTAS());
                //Agregando parametros del método
                request.addProperty("idPregunta",Integer.toString(idPreguntaAsyncTask));


                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_RESPUESTAS(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Vector<?> responseVector = (Vector<?>) response.getProperty(0);

                for (int i = 0; i <responseVector.size(); ++i) {
                    SoapObject datos =(SoapObject)responseVector.get(i);
                    if(numPreguntaAsyncTask == 1){
                        listRespuestasPreg1.add(new Respuesta(datos.getProperty("idRespuesta").toString(), datos.getProperty("Pregunta_idPregunta").toString(), datos.getProperty("Name").toString(), datos.getProperty("Valido").toString()));
                    }
                    if(numPreguntaAsyncTask == 2){
                        listRespuestasPreg2.add(new Respuesta(datos.getProperty("idRespuesta").toString(), datos.getProperty("Pregunta_idPregunta").toString(), datos.getProperty("Name").toString(), datos.getProperty("Valido").toString()));
                    }
                    if(numPreguntaAsyncTask == 3){
                        listRespuestasPreg3.add(new Respuesta(datos.getProperty("idRespuesta").toString(), datos.getProperty("Pregunta_idPregunta").toString(), datos.getProperty("Name").toString(), datos.getProperty("Valido").toString()));
                    }
                    if(numPreguntaAsyncTask == 4){
                        listRespuestasPreg4.add(new Respuesta(datos.getProperty("idRespuesta").toString(), datos.getProperty("Pregunta_idPregunta").toString(), datos.getProperty("Name").toString(), datos.getProperty("Valido").toString()));
                    }
                    if(numPreguntaAsyncTask == 5){
                        listRespuestasPreg5.add(new Respuesta(datos.getProperty("idRespuesta").toString(), datos.getProperty("Pregunta_idPregunta").toString(), datos.getProperty("Name").toString(), datos.getProperty("Valido").toString()));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Respuesta", "excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if(numPreguntaAsyncTask == 1){
                listRespuestasPreg1 = shuffleListRespuestaoptions(listRespuestasPreg1);
            }
            if(numPreguntaAsyncTask == 2){
                listRespuestasPreg2 = shuffleListRespuestaoptions(listRespuestasPreg2);
            }
            if(numPreguntaAsyncTask == 3){
                listRespuestasPreg3 = shuffleListRespuestaoptions(listRespuestasPreg3);
            }
            if(numPreguntaAsyncTask == 4){
                listRespuestasPreg4 = shuffleListRespuestaoptions(listRespuestasPreg4);
            }
            if(numPreguntaAsyncTask == 5){
                listRespuestasPreg5 = shuffleListRespuestaoptions(listRespuestasPreg5);
            }

            //Añadir texto a las opciones de la pregunta;
            for(int i=0; i<4; i++){
                Respuesta resp =  new Respuesta();
                if(numPreguntaAsyncTask == 1){
                    resp = listRespuestasPreg1.get(i);
                }
                if(numPreguntaAsyncTask == 2){
                    resp = listRespuestasPreg2.get(i);
                }
                if(numPreguntaAsyncTask == 3){
                    resp = listRespuestasPreg3.get(i);
                }
                if(numPreguntaAsyncTask == 4){
                    resp = listRespuestasPreg4.get(i);
                }
                if(numPreguntaAsyncTask == 5){
                    resp = listRespuestasPreg5.get(i);
                }
                addOpcionesPregunta(numPreguntaAsyncTask,  i+1, resp.getName());


            }
        }

        @Override
        protected void onCancelled() {
        }
    }

    /**
     * Represents an asynchronous  task used to get preguntas from data base
     */
    public class InsertUserTallerTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_INSERT_USER_TALLER());
                //Agregando parametros del método
                String idLoggedUser = LoginOwnActivity.idLoggedUser;

                request.addProperty("idUser",idLoggedUser);
                request.addProperty("idTaller",Integer.toString(idTaller));
                request.addProperty("puntaje",Integer.toString(puntaje));

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_INSERT_USER_TALLER(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                //Vector<?> responseVector = (Vector<?>) response.getProperty(0);

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Respuesta", "excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

        }

        @Override
        protected void onCancelled() {
        }
    }
}
