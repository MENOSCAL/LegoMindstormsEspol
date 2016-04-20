package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Usuario on 26/03/2016.
 */
public class ListaTalleresActivity  extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;

    private TextView tvIniciarTallerRobozod3r;

    private LinearLayout lly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_talleres);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Talleres");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvIniciarTallerRobozod3r = (TextView) findViewById(R.id.tvTallerRobodoz3r);

        tvIniciarTallerRobozod3r.setOnClickListener(this);

        lly = (LinearLayout) findViewById(R.id.LayoutRobodoz3r);
        lly.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvTallerRobodoz3r: {
                //Toast.makeText(getApplicationContext(), "Bienvenido presionado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, TallerActivity.class));
                break;
            }
            case R.id.LayoutRobodoz3r:{
                System.out.println("Enter to layout");
                lly.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            }
        }
    }
}
