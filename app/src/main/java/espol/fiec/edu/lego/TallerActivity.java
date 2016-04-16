package espol.fiec.edu.lego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import espol.fiec.edu.lego.asynctasks.ImageLoadTask;

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

        //for(int i=0;i<gallery_grid_Images.length;i++)
        for(int i=1;i<4;i++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            //setFlipperImage(gallery_grid_Images[i]);
            setFlipperImage(i);
        }

        btnAnterior.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);

    }

    private void setFlipperImage(int res) {
        Log.i("Set Flipper Called", res + "");
        ImageView image = new ImageView(getApplicationContext());
        //System.out.println("res: "+ res);
        //image.setBackgroundResource(res);
        //image.setImageBitmap(getBitmapFromURL("http://icons.iconarchive.com/icons/crountch/one-piece-jolly-roger/72/Luffys-flag-icon.png"));
        new ImageLoadTask("http://www.corporacionsmartest.com/lego_images/0"+res+".jpg", image).execute();
        viewFlipperInstrucciones.addView(image);
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");

            System.out.print("Bitmap: " +  myBitmap.toString());
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
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
