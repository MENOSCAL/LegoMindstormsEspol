package espol.fiec.edu.lego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by Usuario on 26/03/2016.
 */
public class TallerActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ViewFlipper viewFlipperInstrucciones;
    private Button btnAnterior, btnSiguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taller);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Taller");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAnterior= (Button) findViewById(R.id.btnAnterior);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        //int gallery_grid_Images[]={R.drawable.robodoz3r_1};
        int gallery_grid_Images[]={R.drawable.robodoz3r_1, R.drawable.robodoz3r_2, R.drawable.robodoz3r_3};

        viewFlipperInstrucciones = (ViewFlipper) findViewById(R.id.vfInstrucciones);

        for(int i=0;i<gallery_grid_Images.length;i++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(gallery_grid_Images[i]);
        }

        btnAnterior.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);

    }

    private void setFlipperImage(int res) {
        Log.i("Set Flipper Called", res + "");
        ImageView image = new ImageView(getApplicationContext());
        System.out.println("res: "+ res);
        image.setBackgroundResource(res);
        viewFlipperInstrucciones.addView(image);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSiguiente:
                viewFlipperInstrucciones.showNext();
                System.out.println("page: " + viewFlipperInstrucciones.getDisplayedChild());
                break;
            case R.id.btnAnterior:
                viewFlipperInstrucciones.showPrevious();
                System.out.println("page: " + viewFlipperInstrucciones.getDisplayedChild());
                break;
        }
    }
}
