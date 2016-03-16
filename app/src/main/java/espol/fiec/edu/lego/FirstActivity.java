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

import java.util.ArrayList;
import java.util.List;

import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.fragments.RobotFragment;

public class FirstActivity extends AppCompatActivity {

    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;

    private Drawer navigationDrawerLeft, navigationDrawerRight;
    private AccountHeader headerNavigationLeft;
    private int mPositionClicked;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(FirstActivity.this,"onCheckedChanged: "+(isChecked ? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(" APP LEGO");
        mToolbar.setSubtitle(" Robots");
        mToolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);

        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;
                switch(menuItem.getItemId()){
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
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

        mToolbarBottom.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FirstActivity.this,"Settings pressed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MenuActivity.class ));
            }
        });

        //FRAGMENT
        RobotFragment frag = (RobotFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){
            frag = new RobotFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container,frag, "mainFrag");
            ft.commit();
        }



        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem Pitem1 = new PrimaryDrawerItem().withName("Robots Deportivos").withIcon(getResources().getDrawable(R.drawable.robot_selected_1));
        PrimaryDrawerItem Pitem2 = new PrimaryDrawerItem().withName("Robots de Lujo").withIcon(getResources().getDrawable(R.drawable.robot_2));
        PrimaryDrawerItem Pitem3 = new PrimaryDrawerItem().withName("Robots Coleccionables").withIcon(getResources().getDrawable(R.drawable.robot_3));
        PrimaryDrawerItem Pitem4 = new PrimaryDrawerItem().withName("Robots Populares").withIcon(getResources().getDrawable(R.drawable.robot_4));

        SecondaryDrawerItem Sitem1 = new SecondaryDrawerItem().withName("Robots Deportivos").withIcon(getResources().getDrawable(R.drawable.robot_selected_1));
        SecondaryDrawerItem Sitem2 = new SecondaryDrawerItem().withName("Robots de Lujo").withIcon(getResources().getDrawable(R.drawable.robot_selected_2));
        SecondaryDrawerItem Sitem3 = new SecondaryDrawerItem().withName("Robots Coleccionables").withIcon(getResources().getDrawable(R.drawable.robot_selected_3));
        SecondaryDrawerItem Sitem4 = new SecondaryDrawerItem().withName("Robots Populares").withIcon(getResources().getDrawable(R.drawable.robot_selected_4));

        //NAVIGATION DRAWER
        // END - RIGHT
        navigationDrawerRight = new DrawerBuilder()
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
                .build();

        // Create the AccountHeader
        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                .withHeaderBackground(R.drawable.portada)
                .withTextColorRes(R.color.black)
                .addProfiles(
                        new ProfileDrawerItem().withName("Person One").withEmail("person1@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_1)),
                        new ProfileDrawerItem().withName("Person Two").withEmail("person2@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_2)),
                        new ProfileDrawerItem().withName("Person Three").withEmail("person3@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_3)),
                        new ProfileDrawerItem().withName("Person Four").withEmail("person4@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_4))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        Toast.makeText(FirstActivity.this,"onProfileChanged: "+profile.getName(),Toast.LENGTH_SHORT).show();
                        // headerNavigationLeft.setBackgroundRes(R.drawable.vyron);
                        return false;
                    }
                })
                .build();

        //create the drawer and remember the `Drawer` result object
        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
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
                        //Toast.makeText(FirstActivity.this, "onItemClick: " + position, Toast.LENGTH_SHORT).show();
                        for (int count = 0, tam = navigationDrawerLeft.getDrawerItems().size(); count < tam; count++) {
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
                        Pitem1, Pitem2,
                        // new DividerDrawerItem(),
                        Pitem3, Pitem4,
                        new SectionDrawerItem().withName("Configuraciones"),
                        new SwitchDrawerItem().withName("Notificaciones").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener),
                        new ToggleDrawerItem().withName("Noticias").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener)
                )
                .build();
    }

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

    public List<Robot> getSetCarList(int qtd){
        String[] models = new String[]{"Robot1", "Robot2"};
        String[] brands = new String[]{"Robot con luces", " Robot Ultrasonido"};
        //      int[] categories = new int[]{2, 1, 2, 1, 1, 4, 3, 2, 4, 1};
        int[] photos = new int[]{R.drawable.robot1, R.drawable.robot2};
        //    String[] urlPhotos = new String[]{"gallardo.jpg", "vyron.jpg", "corvette.jpg", "paganni_zonda.jpg", "porsche_911.jpg", "bmw_720.jpg", "db77.jpg", "mustang.jpg", "camaro.jpg", "ct6.jpg"};
        //  String description = "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.";
        List<Robot> listAux = new ArrayList<>();

        for(int i = 0; i < qtd; i++){
            Robot c = new Robot( models[i % models.length], brands[ i % brands.length], photos[i % models.length] );
//            c.setDescription(description);
//            c.setCategory( categories[ i % brands.length ] );
//            c.setTel("33221155");

//            if(category != 0 && c.getCategory() != category){
//                continue;
//            }
            listAux.add(c);
        }
        return(listAux);
    }
}
