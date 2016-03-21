package espol.fiec.edu.lego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import espol.fiec.edu.lego.domain.Robot;
import me.drakeet.materialdialog.MaterialDialog;

public class RobotActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Robot robot;
    private Drawer navigationDrawerLeft;
    private MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);

        if(savedInstanceState != null){
            robot = savedInstanceState.getParcelable("robot");
        }
        else{
            if(getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("robot") != null){
                robot = getIntent().getExtras().getParcelable("robot");
            }
            else{
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(robot.getModel());
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);


        ImageView ivRobot = (ImageView) findViewById(R.id.iv_robot);
        TextView tvModel = (TextView) findViewById(R.id.tv_model);
        TextView tvBrand = (TextView) findViewById(R.id.tv_brand);
        TextView tvDescription = (TextView) findViewById(R.id.tv_description);
        //Button btPhone = (Button) findViewById(R.id.bt_phone);

        /*btPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog = new MaterialDialog(new ContextThemeWrapper(RobotActivity.this,  R.style.MyAlertDialog))
                        .setTitle("Telefono de la Empresa")
                        .setMessage(robot.getTel())
                        .setPositiveButton("Ligar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(Intent.ACTION_CALL);
                                it.setData(Uri.parse("tel:"+ robot.getTel().trim()));
                                startActivity(it);
                            }
                        })
                        .setNegativeButton("Voltar", new View.OnClickListener(){

                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                    }
                                });
                mMaterialDialog.show();
            }
        });*/

        ivRobot.setImageResource(robot.getPhoto());
        tvModel.setText(robot.getModel());
        tvBrand.setText(robot.getBrand());
        tvDescription.setText(robot.getDescription());

        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelable("robot", robot);
    }
}
