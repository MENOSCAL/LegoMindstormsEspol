package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Usuario on 20/03/2016.
 */
public class GuiaUsoActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;

    private TextView tvBienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_uso);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Manuales");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvBienvenido = (TextView) findViewById(R.id.tvBienvenido);

        tvBienvenido.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvBienvenido:
                Toast.makeText(getApplicationContext(), "Bienvenido presionado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,PDFViewerActivity.class));
                break;


        }
    }
}
