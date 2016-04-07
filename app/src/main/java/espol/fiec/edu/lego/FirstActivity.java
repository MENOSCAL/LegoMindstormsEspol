package espol.fiec.edu.lego;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.fragments.RobotFragment;

public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "TSbcJHnOJWFuah2HTFxmUwn9b";
    private static final String TWITTER_SECRET = "pJZeESURODEhqfNQEUJ9hAi88025Ih5QHgMyoMqqO01iQQLcAO";


    private Toolbar mToolbar;
    private int mItemDrawerSelected;
    private List<Robot> listRobots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_first);


        if(savedInstanceState != null){
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
            listRobots = savedInstanceState.getParcelableArrayList("listRobots");
        }
        else{
            listRobots = getSetRobotList();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("ALL Bloques");
        mToolbar.setLogo(R.drawable.ic_launcher);

        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //FRAGMENT
        RobotFragment frag = (RobotFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){
            frag = new RobotFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container,frag, "mainFrag");
            ft.commit();
        }
    }

    public List<Robot> getSetRobotList(){
        int category=0;
        String[] models = new String[]{"Motor Mediano", "Motor Grande","Mover Dirección","Mover Tanque","Avanzados"};
        String[] brands = new String[]{"Acción", "Acción","Acción","Acción","Avanzado"};
        int[] categories = new int[]{1,1,1,1,5};
        int[] photos = new int[]{R.drawable.motor_mediano, R.drawable.motor_grande,R.drawable.mover_direccion,R.drawable.mover_tanque,R.drawable.avanzado};
        String[] description = new String[]{"El Motor mediano incluye un Sensor de rotación incorporado con resolución de 1 grado), " +
                "pero es más pequeño y más liviano que el Motor grande. Esto significa que puede responder más rápidamente que el Motor grande.\n" +
                "El Motor mediano puede programarse para encenderse o apagarse, controlar su nivel de energía o para funcionar durante una cantidad " +
                "de tiempo o de rotaciones especificada.\n" +
                "El Motor mediano funciona a 240–250 rpm, con un torque de rotación de 8 Ncm y un torque de rotor bloqueado de 12 Ncm (más rápido, pero menos potente).",

                "El Motor grande es un motor  potente. Tiene un Sensor de rotación incorporado con resolución de 1 grado para un control preciso. " +
                        "Está diseñado principalmente para controlar la conducción de sus robots.\n" +
                        "Este motor funciona a 160-170 rpm, con una fuerza de torque que varía entre 20 y 40 Ncm lo que lo hace más lento pero más fuerte.",

                "El Bloque mover la dirección puede cambiar la dirección del recorrido de su robot y sus parámetros son los siguientes:\n" +
                        "Dirección: Define el valor como +100 para que gire en el sentido de las agujas del reloj. Si defines -100 girará en sentido contrario " +
                        "y si el valor es 0 se moverá hacia delante en línea recta.\n" +
                        "Potencia: Para establecer un giro suave define como potencia entre 10 y 20 y el valor por defecto es 50%.\n" +
                        "Rotaciones: Indica el número de vueltas que ejecutará el motor.\n" +
                        "Detener al final. Si está marcada esta entrada el motor se detendrá después de ejecutar el movimiento especificado.",

                "El Bloque mover tanque permite darle movimiento al robot, por medio de una tracción tipo tanque y sus parámetros son los siguientes:\n" +
                        "Encendido por rotaciones: Indica si los motores se detienen, avanzan de manera indefinida, avanzan una cantidad de segundos, " +
                        "una cantidad de grados de giro de rueda o una cantidad definida de giros de rueda.\n" +
                        "Potencia: Para establecer la potencia que se puede dar a los motores. El valor 0 corresponde a un motor detenido y el valor 1 es el mínimo de potencia " +
                        "que se puede entregar a un motor y 100 el máximo. En el caso de querer que el motor vaya en dirección opuesta (reversa), se le debe agregar un signo “-” (negativo) " +
                        "al número previo a la potencia, por lo tanto el margen sería entre -1 y -100.\n" +
                        "Rotaciones: Indica el número de vueltas que ejecutará el motor.\n" +
                        "Detener al final. Si está marcada esta entrada el motor se detendrá después de ejecutar el movimiento especificado.",

                "Description 5"};
        List<Robot> listAux = new ArrayList<>();

        for(int i = 0; i < models.length; i++){
            Robot c = new Robot( models[i % models.length], brands[ i % brands.length], photos[i % photos.length],description[i % description.length] );
            c.setCategory(categories[i % brands.length]);

            if(category != 0 && c.getCategory() != category){
                continue;
            }
            listAux.add(c);
        }
        return(listAux);
    }
    public List<Robot> getRobotsByCategory(int category){
        List<Robot> listAux = new ArrayList<>();
        for(int i = 0; i < listRobots.size(); i++){
            if(category != 0 && listRobots.get(i).getCategory() != category){
                continue;
            }
            listAux.add(listRobots.get(i));
        }
        return(listAux);
    }
    public List<Robot> getListRobots(){
        return (listRobots);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putInt("mItemDrawerSelected", mItemDrawerSelected);
        outState.putParcelableArrayList("ListRobots", (ArrayList<Robot>) listRobots);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        RobotFragment frag = null;

        if (id == R.id.nav_all) { //Todos los Bloques
            frag = new RobotFragment();
            frag.setGroup(0);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_accion) { //Bloques de Acción
            frag = new RobotFragment();
            frag.setGroup(1);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_flujo) { //Bloques de Flujo
            frag = new RobotFragment();
            frag.setGroup(2);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_operaciones) { //Bloques de Operaciones
            frag = new RobotFragment();
            frag.setGroup(3);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_sensores) { //Bloques de Sensores
            frag = new RobotFragment();
            frag.setGroup(4);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_avanzados) { //Bloques Avanzados
            frag = new RobotFragment();
            frag.setGroup(5);
            mToolbar.setTitle(item.getTitle());
        }

        listRobots = getSetRobotList();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
