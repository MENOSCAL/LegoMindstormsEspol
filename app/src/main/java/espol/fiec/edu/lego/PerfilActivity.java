package espol.fiec.edu.lego;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import espol.fiec.edu.lego.domain.Person;
import espol.fiec.edu.lego.domain.Pregunta;
import lecho.lib.hellocharts.model.PieChartData;

import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class PerfilActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RelativeLayout mainLayout;

    private PieChartView chart;
    private PieChartData data;

    //Web services
    private WebServicesConfiguration wsConf;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserTask  mAuthTask = null;

    private int CantTalleres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mToolbar = (Toolbar) findViewById(R.id.tb_perfil);

        String name= LoginOwnActivity.nameS;

        if(name != null){
            mToolbar.setTitle(name);
        }else {
            mToolbar.setTitle("Usuario1");
        }

        wsConf = (WebServicesConfiguration) getApplicationContext();

        mAuthTask = new UserTask();
        mAuthTask.execute((Void) null);


        mToolbar.setLogo(R.drawable.foto);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        chart = new PieChartView(this);

    }

    private void generateData() {

        int  suma=0;
        float porcentaje=0;

        List<SliceValue> values = new ArrayList<SliceValue>();

        if(TallerActivity.Taller1==true)
            suma++;
        if(TallerActivity.Taller2==true)
            suma++;
        if(TallerActivity.Taller3==true)
            suma++;
        if(TallerActivity.Taller4==true)
            suma++;
        if(TallerActivity.Taller5==true)
            suma++;
        if(TallerActivity.Taller6==true)
            suma++;

        if (suma==0)
            porcentaje=0;
        else
            porcentaje=(suma * 100)/CantTalleres;



        SliceValue sliceValue = new SliceValue((int) 100-porcentaje, ChartUtils.COLOR_RED);
        sliceValue.setLabel(((int) sliceValue.getValue() + "%").toCharArray());
        values.add(sliceValue);

        SliceValue sliceValue1 = new SliceValue((int) porcentaje  , ChartUtils.COLOR_BLUE);
        sliceValue1.setLabel(((int) sliceValue1.getValue() + "%").toCharArray());
        values.add(sliceValue1);

        data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasLabelsOutside(true);

        chart.setPieChartData(data);
        chart.setCircleFillRatio(0.5f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }
        return true;
    }

    /**
     * Represents an asynchronous  task used to get preguntas from data base
     */
    public class UserTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            SoapObject request, response;
            Vector<?> responseVector;
            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_TALLERES());
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_TALLERES(), envelope);
                response = (SoapObject) envelope.bodyIn;
                responseVector = (Vector<?>) response.getProperty(0);

                CantTalleres=responseVector.size();

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
            chart.setRotation(0);
            generateData();
            mainLayout.addView(chart);
        }

        @Override
        protected void onCancelled() {
        }
    }

}