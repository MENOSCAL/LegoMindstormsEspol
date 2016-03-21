package espol.fiec.edu.lego;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import espol.fiec.edu.lego.fragments.PDFRenderFragment;
import espol.fiec.edu.lego.fragments.RobotFragment;


/**
 * Created by Usuario on 20/03/2016.
 */
public class PDFViewerActivity extends Activity {

    public static final String FRAGMENT_PDF_RENDERER_BASIC = "pdf_renderer_basic";

    private LinearLayout container;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);



        //find view by id
        //btnRender = (View)findViewById(R.id.btn_render);
        //container = (LinearLayout)findViewById(R.id.fragment_layout);

        //FRAGMENT
        /*
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_layout, new PDFRenderFragment());
        ft.commit();
        */

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_layout, new PDFRenderFragment(),
                            FRAGMENT_PDF_RENDERER_BASIC)
                    .commit();
        }



        //container.setVisibility(View.VISIBLE);

        //set event handling for button
        //btnRender.setOnClickListener(onClickListener());
    }

/*
    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //replace fragment when clicked
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_layout, new PDFRenderFragment());
                ft.commit();

                //gone button after all
                //btnRender.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
            }
        };
    }
*/
}
