package espol.fiec.edu.lego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Usuario on 26/03/2016.
 */
public class ListaTalleresActivity  extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_talleres);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Talleres");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
