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
        String[] models = new String[]{"Accion de Bloques", "Control de Flujo","Operaciones con Datos","Sensores","Avanzados"};
        String[] brands = new String[]{"Accion", "Flujo","Operación","Sensor","Avanzado"};
        int[] categories = new int[]{1,2,3,4,5};
        int[] photos = new int[]{R.drawable.accion, R.drawable.flujo,R.drawable.operacion,R.drawable.sensor,R.drawable.avanzado};
        String[] description = new String[]{"Description 1","Description 2","Description 3","Description 4","Description 5"};
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
