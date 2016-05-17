package espol.fiec.edu.lego;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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

import espol.fiec.edu.lego.domain.Person;
import espol.fiec.edu.lego.domain.Taller;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Web services
    private WebServicesConfiguration wsConf;

    public static ArrayList<Taller> listTalleres;

    private GetTalleresTask getTalleresTask;

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent it = null;

        if (id == R.id.nav_perfil) {
            startActivity(new Intent(this,PerfilActivity.class));

        } else if (id == R.id.nav_programacion) {
            startActivity(new Intent(this,FirstActivity.class));
        }
        else if (id == R.id.nav_talleres) {
            wsConf = (WebServicesConfiguration) getApplicationContext();

            getTalleresTask = new GetTalleresTask();
            getTalleresTask.execute((Void) null);
            //startActivity(new Intent(this,ListaTalleresActivity.class));

        } else if (id == R.id.nav_manuales) {
            //Open Guide activity
            startActivity(new Intent(this,GuiaUsoActivity.class));

        } else if (id == R.id.nav_share) {
            it = new Intent(Intent.ACTION_VIEW);
            it.setData(Uri.parse("https://www.facebook.com/LEGO"));
            startActivity(it);

        } else if (id == R.id.nav_send) {

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
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_TALLERES());

                System.out.println("Id Logged User: " + LoginOwnActivity.idLoggedUser);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_TALLERES(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Vector<?> responseVector = (Vector<?>) response.getProperty(0);

                for (int i = 0; i <responseVector.size(); ++i) {
                    SoapObject datos =(SoapObject)responseVector.get(i);
                    listTalleres.add(new Taller(datos.getProperty("Title").toString(),datos.getProperty("idTaller").toString(),datos.getProperty("Image").toString()));
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
            startActivity(new Intent(getApplicationContext(),ListaTalleresActivity.class));
        }

        @Override
        protected void onCancelled() {
        }
    }
}
