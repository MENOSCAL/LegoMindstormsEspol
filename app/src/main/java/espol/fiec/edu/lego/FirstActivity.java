package espol.fiec.edu.lego;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.fragments.RobotFragment;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private int mItemDrawerSelected;
    //private List<Robot> listRobots;

    //Web services
    private WebServicesConfiguration wsConf;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserBloqueTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);




        if(savedInstanceState != null){
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
            MenuActivity.listRobots = savedInstanceState.getParcelableArrayList("listRobots");
        }
        //else{
          //  listRobots = getSetRobotList();
          //  MenuActivity.listRobots = new ArrayList<Robot>();
            //wsConf = (WebServicesConfiguration) getApplicationContext();

            //mAuthTask = new UserBloqueTask();
            //mAuthTask.execute((Void) null);
        //}

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Todos los Bloques");

        //mToolbar.setLogo(R.drawable.ic_launcher);

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
            //frag.setGroup(1);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container,frag, "mainFrag");
            ft.commit();
        }
    }
    public List<Robot> getRobotsByCategory(int category){
        List<Robot> listAux = new ArrayList<>();
        for(int i = 0; i < MenuActivity.listRobots.size(); i++){
            if(category != 0 && MenuActivity.listRobots.get(i).getCategory() != category){
                continue;
            }
            listAux.add(MenuActivity.listRobots.get(i));
        }
        return(listAux);
    }
    public List<Robot> getListRobots(){
        return (MenuActivity.listRobots);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putInt("mItemDrawerSelected", mItemDrawerSelected);
        outState.putParcelableArrayList("ListRobots", (ArrayList<Robot>) MenuActivity.listRobots);

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
        } else

        if (id == R.id.nav_accion) { //Bloques de Acción
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

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
