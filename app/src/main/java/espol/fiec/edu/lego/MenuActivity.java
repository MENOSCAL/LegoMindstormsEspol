package espol.fiec.edu.lego;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.domain.Taller;
import espol.fiec.edu.lego.fragments.RobotFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Web services
    private WebServicesConfiguration wsConf;

    public static ArrayList<Taller> listTalleres;
    public static ArrayList<Robot> listRobots;

    private GetTalleresTask getTalleresTask;
    private GetUserTask getUserTask;
    private UserBloqueTask getBloqueTask;

    public static int CantTalleres;
    public static int CantTalleresReal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listTalleres =  new ArrayList<Taller>();

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
       //     return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent it = null;
        wsConf = (WebServicesConfiguration) getApplicationContext();

        if (id == R.id.nav_perfil) {

            getUserTask = new GetUserTask();
            getUserTask.execute((Void) null);

        } else if (id == R.id.nav_programacion) {
            listRobots =  new ArrayList<Robot>();

            getBloqueTask = new UserBloqueTask();
            getBloqueTask.execute((Void) null);

           // startActivity(new Intent(this,FirstActivity.class));
        }
        else if (id == R.id.nav_talleres) {

            getTalleresTask = new GetTalleresTask();
            getTalleresTask.execute((Void) null);
            //startActivity(new Intent(this,ListaTalleresActivity.class));

        } else if (id == R.id.nav_manuales) {
            //Open Guide activity
            startActivity(new Intent(this,GuiaUsoActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Represents an asynchronous  task used to get talleres from data base
     */
    public class GetTalleresTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(),wsConf.getMETHOD_GET_TALLER_BY_USER());
                //Agregando parametros del método
                request.addProperty("idUser",LoginOwnActivity.idLoggedUser);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_TALLER_BY_USER(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Vector<?> responseVector = (Vector<?>) response.getProperty(0);

                for (int i = 0; i <responseVector.size(); ++i) {
                    SoapObject datos =(SoapObject)responseVector.get(i);

                    String puntaje;
                    try{
                        puntaje = datos.getProperty("Puntaje").toString();
                    }
                     catch(Exception e){
                        puntaje = "0";
                     }

                     listTalleres.add(new Taller(datos.getProperty("Title").toString(),datos.getProperty("idTaller").toString(),datos.getProperty("Image").toString(), puntaje));
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Respuesta", "excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            startActivity(new Intent(getApplicationContext(),ListaTalleresActivity.class));
        }

        @Override
        protected void onCancelled() {
        }
    }

    /**
     * Represents an asynchronous  task used to get talleres from data base
     */
    public class GetUserTask extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            CantTalleres=0;
            CantTalleresReal=0;
            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(),wsConf.getMETHOD_GET_TALLER_BY_USER());
                //Agregando parametros del método
                request.addProperty("idUser",LoginOwnActivity.idLoggedUser);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_TALLER_BY_USER(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Vector<?> responseVector = (Vector<?>) response.getProperty(0);

                CantTalleres=responseVector.size();

                for (int i = 0; i <responseVector.size(); ++i) {
                    SoapObject datos =(SoapObject)responseVector.get(i);

                    String puntaje;
                    try{
                        puntaje = datos.getProperty("Puntaje").toString();
                    }
                    catch(Exception e){
                        puntaje = "0";
                    }
                    if(!puntaje.equals("0")){
                        CantTalleresReal++;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Respuesta", "excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            startActivity(new Intent(getApplicationContext(),PerfilActivity.class));
        }
    }

    /**
     * Represents an asynchronous task to get bloques data from database
     */
    public class UserBloqueTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            SoapObject request, response;
            Vector<?> responseVector, responseVector1;
            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_BLOQUES());
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_BLOQUES(), envelope);
                response = (SoapObject) envelope.bodyIn;
                responseVector = (Vector<?>) response.getProperty(0);

                request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_CATEGORIES());
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_CATEGORIES(), envelope);
                response = (SoapObject) envelope.bodyIn;
                responseVector1 = (Vector<?>) response.getProperty(0);



                String categoryB = "";
                String titleB = "";
                String descriptionB = "";
                String urlB = "";

                /*String[] url = new String[]{"motor_mediano","motor_grande","mover_direccion","mover_tanque","pantalla","sonido","luz_estado",
                        "iniciar","esperar","bucle","interruptor","interrupcion_bucle",
                        "sensor_ultrasonico","sensor_infrarrojo","sensor_girosensor","sensor_color","rotacion_motor","sensor_tactil","temporizador","botones","sensor_sonido","sensor_temperatura","energia",
                        "constante","variable","operaciones_secuenciales","operaciones_logicas","matematica","redondear","comparar","rango","texto","aleatorio",
                        "acceso_archivo","mandar_mensaje","conexion_bluetooth","mantener_activo","comentario","sensor_sin_procesar","detener","invertir_motor","motor_sin_regular"};
                */

                for (int i = 0; i <responseVector.size(); ++i) {
                    SoapObject datos =(SoapObject)responseVector.get(i);
                    categoryB          = datos.getProperty("Category_idCategory").toString();
                    titleB             = datos.getProperty("Title").toString();
                    descriptionB       = datos.getProperty("Description").toString();
                    urlB               = datos.getProperty("Image").toString();

                    for (int j = 0; j <responseVector1.size(); ++j) {
                        SoapObject datos1 =(SoapObject)responseVector1.get(j);
                        if(categoryB.equals(datos1.getProperty("idCategory").toString()) ){

                            Robot c = new Robot( titleB, datos1.getProperty("Name").toString(), descriptionB );

                            //c.setPhoto(photos[i % photos.length]);
                            c.setCategory(Integer.parseInt(datos1.getProperty("idCategory").toString()));
                            c.setUrl(urlB);
                            //c.setUrl(url[i % url.length]);

                            listRobots.add(c);

                            break;
                        }
                    }
                }

            } catch (Exception e) {
                Log.i("Respuesta", "excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            startActivity(new Intent(getApplicationContext(),FirstActivity.class));
        }
    }

}
