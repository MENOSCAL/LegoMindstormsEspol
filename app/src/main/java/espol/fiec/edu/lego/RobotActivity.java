package espol.fiec.edu.lego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import espol.fiec.edu.lego.asynctasks.ImageLoadRobot;
import espol.fiec.edu.lego.domain.Robot;

public class RobotActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Robot robot;
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

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(robot.getTitle());
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);


        ImageView ivRobot = (ImageView) findViewById(R.id.iv_robot);
        TextView tvModel = (TextView) findViewById(R.id.tv_model);
        //TextView tvBrand = (TextView) findViewById(R.id.tv_brand);
        TextView tvDescription = (TextView) findViewById(R.id.tv_description);

        new ImageLoadRobot("http://www.corporacionsmartest.com/lego_mindstorm/bloques/"+robot.getUrl()+".jpg" , ivRobot).execute();

        //ivRobot.setImageResource(robot.getPhoto());
        tvModel.setText(robot.getTitle());
        //tvBrand.setText(robot.getBrand());
        tvDescription.setText(robot.getDescription());
/*
        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .build();*/
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
