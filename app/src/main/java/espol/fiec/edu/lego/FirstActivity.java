package espol.fiec.edu.lego;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

import espol.fiec.edu.lego.domain.Person;
import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.fragments.RobotFragment;

public class FirstActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "TSbcJHnOJWFuah2HTFxmUwn9b";
    private static final String TWITTER_SECRET = "pJZeESURODEhqfNQEUJ9hAi88025Ih5QHgMyoMqqO01iQQLcAO";


    //private static String TAG = "LOG";
    private Toolbar mToolbar;
    //private Toolbar mToolbarBottom;

    private Drawer navigationDrawerLeft, navigationDrawerRight;
    private AccountHeader headerNavigationLeft;
    //private int mPositionClicked;
    private FloatingActionMenu fab;

    private int mItemDrawerSelected;
    private List<PrimaryDrawerItem> listCategorias;
    private List<Person> listProfile;
    private List<Robot> listRobots;

    /*
    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(FirstActivity.this,"onCheckedChanged: "+(isChecked ? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };*/

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


        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(" APP LEGO");
        //mToolbar.setSubtitle(" Robots");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);*/
/*
        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;

                switch (menuItem.getItemId()) {
                    case R.id.action_facebook:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.action_youtube:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.youtube.com"));
                        break;
                    case R.id.action_google_plus:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.plus.google.com"));
                        break;
                    case R.id.action_linkedin:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.linkedin.com"));
                        break;
                    case R.id.action_whatsapp:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.whatsapp.com"));
                        break;
                }

                startActivity(it);
                return true;
            }
        });
*/
/*
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

        mToolbarBottom.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "Settings pressed", Toast.LENGTH_SHORT).show();
            }
        });
*/
        //FRAGMENT
        RobotFragment frag = (RobotFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){
            frag = new RobotFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container,frag, "mainFrag");
            ft.commit();
        }


        //if you want to update the items at a later time it is recommended to keep it in a variable
        /*PrimaryDrawerItem Pitem1 = new PrimaryDrawerItem().withName("Robots Deportivos").withIcon(getResources().getDrawable(R.drawable.robot_selected_1));
        PrimaryDrawerItem Pitem2 = new PrimaryDrawerItem().withName("Robots de Lujo").withIcon(getResources().getDrawable(R.drawable.robot_2));
        PrimaryDrawerItem Pitem3 = new PrimaryDrawerItem().withName("Robots Coleccionables").withIcon(getResources().getDrawable(R.drawable.robot_3));
        PrimaryDrawerItem Pitem4 = new PrimaryDrawerItem().withName("Robots Populares").withIcon(getResources().getDrawable(R.drawable.robot_4));

        SecondaryDrawerItem Sitem1 = new SecondaryDrawerItem().withName("Robots Deportivos").withIcon(getResources().getDrawable(R.drawable.robot_selected_1));
        SecondaryDrawerItem Sitem2 = new SecondaryDrawerItem().withName("Robots de Lujo").withIcon(getResources().getDrawable(R.drawable.robot_selected_2));
        SecondaryDrawerItem Sitem3 = new SecondaryDrawerItem().withName("Robots Coleccionables").withIcon(getResources().getDrawable(R.drawable.robot_selected_3));
        SecondaryDrawerItem Sitem4 = new SecondaryDrawerItem().withName("Robots Populares").withIcon(getResources().getDrawable(R.drawable.robot_selected_4));
*/
        //NAVIGATION DRAWER
        // END - RIGHT
        /*navigationDrawerRight = new DrawerBuilder()
                .withActivity(this)
                        //.withToolbar(mToolbar)
                .withDisplayBelowToolbar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.END)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        Toast.makeText(FirstActivity.this, "onItemClick: " + position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        Toast.makeText(FirstActivity.this, "onItemLongClick: " + position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .addDrawerItems(
                        Sitem1, Sitem2,
                        new DividerDrawerItem(),
                        Sitem3, Sitem4
                )
                .build();*/

        // Create the AccountHeader
        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                //.withHeaderBackground(R.drawable.portada)
                .withTextColorRes(R.color.black)
                /*.addProfiles(
                        new ProfileDrawerItem().withName("Person One").withEmail("person1@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_1)),
                        new ProfileDrawerItem().withName("Person Two").withEmail("person2@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_2)),
                        new ProfileDrawerItem().withName("Person Three").withEmail("person3@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_3)),
                        new ProfileDrawerItem().withName("Person Four").withEmail("person4@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_4))
                )*/
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        Person aux = getPersonByEmail(listProfile,(ProfileDrawerItem) profile);
                        headerNavigationLeft.setBackgroundRes(aux.getBackground());
                        //Toast.makeText(FirstActivity.this,"onProfileChanged: "+profile.getName(),Toast.LENGTH_SHORT).show();
                        // headerNavigationLeft.setBackgroundRes(R.drawable.vyron);
                        return true;
                    }
                })
                .build();

        listProfile = getSetProfileList();
        if (listProfile != null && listProfile.size() > 0){
            for (int i=0; i < listProfile.size(); i++){
                headerNavigationLeft.addProfile(listProfile.get(i).getProfile(), i);
            }
            headerNavigationLeft.setBackgroundRes(listProfile.get(0).getBackground());
        }

        //create the drawer and remember the `Drawer` result object
        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerNavigationLeft)
                /*.withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        return false;
                    }
                })*/
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        RobotFragment frag = null;
                        mItemDrawerSelected = position;

                        if (position == 0) { //Todos los Bloques
                            frag = new RobotFragment();
                            frag.setGroup(0);
                            mToolbar.setTitle(listCategorias.get(0).getName());
                        } else if (position == 1) { //Bloques de Acción
                            frag = new RobotFragment();
                            frag.setGroup(1);
                            mToolbar.setTitle(listCategorias.get(1).getName());
                        } else if (position == 2) { //Bloques de Flujo
                            frag = new RobotFragment();
                            frag.setGroup(2);
                            mToolbar.setTitle(listCategorias.get(2).getName());
                        } else if (position == 3) { //Bloques de Operaciones
                            frag = new RobotFragment();
                            frag.setGroup(3);
                            mToolbar.setTitle(listCategorias.get(3).getName());
                        } else if (position == 4) { //Bloques de Sensores
                            frag = new RobotFragment();
                            frag.setGroup(4);
                            mToolbar.setTitle(listCategorias.get(4).getName());
                        } else if (position == 5) { //Bloques Avanzados
                            frag = new RobotFragment();
                            mToolbar.setTitle(listCategorias.get(5).getName());
                        }

                        listRobots = getSetRobotList();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                        ft.commit();


                        //Toast.makeText(FirstActivity.this, "onItemClick: " + position, Toast.LENGTH_SHORT).show();
                        /*for (int count = 0, tam = navigationDrawerLeft.getDrawerItems().size(); count < tam; count++) {
                            if (count == mPositionClicked && mPositionClicked <= 3) {
                                PrimaryDrawerItem aux = (PrimaryDrawerItem) navigationDrawerLeft.getDrawerItems().get(count);
                                aux.setIcon(getResources().getDrawable(getCorrectcDrawerIcon(count, false)));
                                break;
                            }
                        }

                        if (position <= 3) {
                            ((PrimaryDrawerItem) drawerItem).setIcon(getResources().getDrawable(getCorrectcDrawerIcon(position, true)));
                        }

                        mPositionClicked = position;
                        navigationDrawerLeft.getAdapter().notifyDataSetChanged();
                        */
                        return false;
                    }
                })

                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        Toast.makeText(FirstActivity.this, "onItemLongClick: " + position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                /*.addDrawerItems(
                        Pitem1, Pitem2,
                        // new DividerDrawerItem(),
                        Pitem3, Pitem4,
                        new SectionDrawerItem().withName("Configuraciones"),
                        new SwitchDrawerItem().withName("Notificaciones").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener),
                        new ToggleDrawerItem().withName("Noticias").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener)
                )*/
                .build();

        listCategorias = getSetCategoryList();
        mToolbar.setTitle(listCategorias.get(0).getName());

        if(listCategorias != null && listCategorias.size() > 0){
            for (int i = 0; i < listCategorias.size(); i++){
                navigationDrawerLeft.addItem(listCategorias.get(i));
            }
            //navigationDrawerLeft.setSelection(mItemDrawerSelected);
        }

        fab = (FloatingActionMenu) findViewById(R.id.fab);

    }

/*
        private int getCorrectcDrawerIcon(int position, boolean isSelecetd){
            switch(position){
                case 0:
                    return( isSelecetd ? R.drawable.robot_selected_1 : R.drawable.robot_1);
                case 1:
                    return( isSelecetd ? R.drawable.robot_selected_2 : R.drawable.robot_2);
                case 2:
                    return( isSelecetd ? R.drawable.robot_selected_3 : R.drawable.robot_3);
                case 3:
                    return( isSelecetd ? R.drawable.robot_selected_4 : R.drawable.robot_4);
            }
            return(0);
        }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_second_activity){
            startActivity(new Intent(this,SecondActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    //CATEGORIA
    public List<PrimaryDrawerItem> getSetCategoryList(){
        String[] names = new String[]{"ALL Bloques", "Bloques de Acción","Bloques de Flujo", "Bloques de Operaciones",
                "Bloques de Sensores","Bloques Avanzados"};
        int[] icons = new int[]{R.drawable.robot_1,R.drawable.robot_1,R.drawable.robot_2,R.drawable.robot_3,
                R.drawable.robot_4,R.drawable.robot_4};
        int[] iconsSelected = new int[]{R.drawable.robot_selected_1,R.drawable.robot_selected_1,R.drawable.robot_selected_2,
                R.drawable.robot_selected_3,R.drawable.robot_selected_4,R.drawable.robot_selected_4};
        List<PrimaryDrawerItem> list = new ArrayList<>();

        for(int i = 0; i < names.length; i++){
            PrimaryDrawerItem aux = new PrimaryDrawerItem();
            aux.setName(names[i]);
            aux.setIcon(getResources().getDrawable(icons[i]));
            aux.setTextColor(getResources().getColor(R.color.colorPrimarytext));
            aux.setSelectedIcon(getResources().getDrawable(iconsSelected[i]));
            aux.setSelectedTextColor(getResources().getColor(R.color.colorPrimary));

            list.add(aux);
        }
        return(list);
    }

    //PERSON
    private Person getPersonByEmail(List<Person> list, ProfileDrawerItem p){
        Person aux = null;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getProfile().getEmail().equalsIgnoreCase(p.getEmail())){
                aux = list.get(i);
                break;
            }
        }
        return (aux);
    }

    private List<Person> getSetProfileList(){
        String[] names = new String[]{"User 1"};
        String[] emails = new String[]{"emailUser_1@gmail.com"};
        int[] photos = new int[]{R.drawable.person_1};
        int[] background = new int[]{R.drawable.portada};
        List<Person> list = new ArrayList<>();

        for(int i=0; i < names.length; i++){
            ProfileDrawerItem aux = new ProfileDrawerItem();
            aux.setName(names[i]);
            aux.setEmail(emails[i]);
            aux.setIcon(getResources().getDrawable(photos[i]));

            Person p = new Person();
            p.setProfile(aux);
            p.setBackground(background[i]);

            list.add(p);
        }
        return (list);
    }

    //Robot
    public List<Robot> getSetRobotList(int qtd){
        return(getSetRobotList(qtd, 0));
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
            //c.selTel("33221155");

            if(category != 0 && c.getCategory() != category){
                continue;
            }
            listAux.add(c);
        }
        return(listAux);
    }

    public List<Robot> getSetRobotList(int qtd, int category){
        String[] models = new String[]{"Accion de Bloques", "Control de Flujo","Operaciones con Datos","Sensores","Avanzados"};
        String[] brands = new String[]{"Accion", "Flujo","Operación","Sensor","Avanzado"};
        int[] categories = new int[]{1,2,3,4,5};
        int[] photos = new int[]{R.drawable.accion, R.drawable.flujo,R.drawable.operacion,R.drawable.sensor,R.drawable.avanzado};
        String[] description = new String[]{"Description 1","Description 2","Description 3","Description 4","Description 5"};
        List<Robot> listAux = new ArrayList<>();

        for(int i = 0; i < qtd; i++){
            Robot c = new Robot( models[i % models.length], brands[ i % brands.length], photos[i % photos.length],description[i % description.length] );
            c.setCategory(categories[i % brands.length]);
            //c.selTel("33221155");

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

        //outState = navigationDrawerRight.saveInstanceState(outState);
        outState = navigationDrawerLeft.saveInstanceState(outState);
        outState = headerNavigationLeft.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed(){
        if(navigationDrawerLeft.isDrawerOpen()){
            navigationDrawerLeft.closeDrawer();
        }
        else if(fab.isOpened()){
            fab.close(true);
        }
        else{
            super.onBackPressed();
        }
    }
}
