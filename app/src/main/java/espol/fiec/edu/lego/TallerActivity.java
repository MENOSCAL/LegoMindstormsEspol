package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import espol.fiec.edu.lego.asynctasks.ImageLoadTask;
import espol.fiec.edu.lego.domain.ImagenTaller;
import espol.fiec.edu.lego.domain.Pregunta;

/**
 * Created by Usuario on 26/03/2016.
 */
public class TallerActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ViewFlipper viewFlipperInstrucciones;
    private Button btnAnterior, btnSiguiente;
    private TextView txtIniciarTaller;
    private String tallerName;
    private int idTaller;

    private WebServicesConfiguration wsConf;
    private ArrayList<ImagenTaller> listImagenesTaller;
    private GetImagenesTallerTask getImagenesTallerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taller);

        Bundle bolsaR = getIntent().getExtras();
        tallerName = bolsaR.getString("tallerNameKey");
        idTaller = bolsaR.getInt("tallerIdKey");

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(tallerName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtIniciarTaller = (TextView) findViewById(R.id.txtIniciarTest);

        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnAnterior.setEnabled(false);

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        viewFlipperInstrucciones = (ViewFlipper) findViewById(R.id.vfInstrucciones);

        wsConf = (WebServicesConfiguration) getApplicationContext();
        listImagenesTaller =  new ArrayList<ImagenTaller>();

        getImagenesTallerTask= new GetImagenesTallerTask();
        getImagenesTallerTask.execute((Void) null);

        btnAnterior.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);

        txtIniciarTaller.setOnClickListener(this);
    }

    private void setFlipperImage( String urlImagen) {
        ImageView image = new ImageView(getApplicationContext());
        new ImageLoadTask(urlImagen, image).execute();

        viewFlipperInstrucciones.addView(image);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSiguiente:{
                viewFlipperInstrucciones.showNext();

                if(viewFlipperInstrucciones.getDisplayedChild() == (listImagenesTaller.size() -1)){
                    btnSiguiente.setEnabled(false);
                    txtIniciarTaller.setVisibility(View.VISIBLE);
                }
                else{
                    btnSiguiente.setEnabled(true);
                }

                if(viewFlipperInstrucciones.getDisplayedChild()!=0){
                    btnAnterior.setEnabled(true);
                }

                break;
            }
            case R.id.btnAnterior: {
                viewFlipperInstrucciones.showPrevious();

                if(viewFlipperInstrucciones.getDisplayedChild() != (listImagenesTaller.size() -1)){
                    btnSiguiente.setEnabled(true);
                    txtIniciarTaller.setVisibility(View.GONE);
                }

                if (viewFlipperInstrucciones.getDisplayedChild() == 0) {
                    btnAnterior.setEnabled(false);
                } else {
                    btnSiguiente.setEnabled(true);
                }
                break;
            }
            case R.id.txtIniciarTest:{
                //Abrir Test
                Intent testActivity = new Intent(this, TestActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("tallerNameKey", tallerName);
                bolsa.putInt("tallerIdKey", idTaller);

                testActivity.putExtras(bolsa);

                startActivity(testActivity);
                break;
            }
        }
    }

    /**
     * Represents an asynchronous  task used to get preguntas from data base
     */
    public class GetImagenesTallerTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_IMAGEN_TALLER());
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
                    listImagenesTaller.add(new ImagenTaller(datos.getProperty("idImagenTaller").toString(),datos.getProperty("Foto").toString(),datos.getProperty("Taller_idTaller").toString()));

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

            for(int i=0; i<listImagenesTaller.size(); i++){
                ImagenTaller imagenTaller = listImagenesTaller.get(i);
                //Pregunta preg = listPreguntas.get(i);
                //addPreguntaFromServer(preg.getName(),i+1,  preg.getIdPregunta());
                setFlipperImage(imagenTaller.getUrlImagen());
            }
        }

        @Override
        protected void onCancelled() {
        }
    }
}