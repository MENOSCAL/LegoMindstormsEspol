package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import espol.fiec.edu.lego.asynctasks.ImageLoadTask;

/**
 * Created by Usuario on 26/03/2016.
 */
public class TallerActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ViewFlipper viewFlipperInstrucciones;
    private Button btnAnterior, btnSiguiente;
    private TextView txtIniciarTaller;
    private String tallerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taller);

        Bundle bolsaR = getIntent().getExtras();
        tallerName = bolsaR.getString("tallerKey");

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(tallerName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtIniciarTaller = (TextView) findViewById(R.id.txtIniciarTest);

        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnAnterior.setClickable(false);

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        viewFlipperInstrucciones = (ViewFlipper) findViewById(R.id.vfInstrucciones);

        for(int i=1;i<4;i++)
        {
            //This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(i, tallerName);
        }

        btnAnterior.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);

        txtIniciarTaller.setOnClickListener(this);
    }

    private void setFlipperImage(int res, String taller) {
        Log.i("Set Flipper Called", res + "");
        ImageView image = new ImageView(getApplicationContext());

        new ImageLoadTask("http://www.corporacionsmartest.com/lego_mindstorm/Talleres/"+taller+"/"+res+".jpg", image).execute();

        viewFlipperInstrucciones.addView(image);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSiguiente:{
                viewFlipperInstrucciones.showNext();

                if(viewFlipperInstrucciones.getDisplayedChild() != 0){
                    btnAnterior.setClickable(true);
                }

                //Cuando se llega a la ultima imagen
                if(viewFlipperInstrucciones.getDisplayedChild() == 2){
                    txtIniciarTaller.setVisibility(View.VISIBLE);
                }

                break;
            }
            case R.id.btnAnterior: {
                viewFlipperInstrucciones.showPrevious();

                if (viewFlipperInstrucciones.getDisplayedChild() == 0) {
                    btnAnterior.setClickable(false);
                } else {
                    btnSiguiente.setClickable(true);
                }
                break;
            }
            case R.id.txtIniciarTest:{
                //Abrir Test
                startActivity(new Intent(this, TestActivity.class));
                break;
            }
        }
    }
}
