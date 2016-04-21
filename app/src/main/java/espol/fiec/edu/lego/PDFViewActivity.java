package espol.fiec.edu.lego;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.joanzapata.pdfview.PDFView;

import espol.fiec.edu.lego.fragments.PDFRenderFragment;

/**
 * Created by Usuario on 21/04/2016.
 */
public class PDFViewActivity extends AppCompatActivity {

    private PDFView pdfView;
    private String pdfName;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Guia de Uso");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bolsaR = getIntent().getExtras();

        pdfView = (PDFView) findViewById(R.id.pdfview);

        pdfName = bolsaR.getString("pdfnameKey");

        pdfView.fromAsset(pdfName)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();

    }
}
