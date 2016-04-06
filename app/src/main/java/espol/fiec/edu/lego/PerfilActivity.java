package espol.fiec.edu.lego;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class PerfilActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RelativeLayout mainLayout;

    private PieChartView chart;
    private PieChartData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mToolbar = (Toolbar) findViewById(R.id.tb_perfil);
        mToolbar.setTitle("Usuario1");
        mToolbar.setLogo(R.drawable.foto);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        chart = new PieChartView(this);
        chart.setRotation(0);
        generateData();
        mainLayout.addView(chart);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout1);
        chart = new PieChartView(this);
        chart.setRotation(0);
        generateData();
        mainLayout.addView(chart);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout2);
        chart = new PieChartView(this);
        chart.setRotation(0);
        generateData();
        mainLayout.addView(chart);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout3);
        chart = new PieChartView(this);
        chart.setRotation(0);
        generateData();
        mainLayout.addView(chart);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout4);
        chart = new PieChartView(this);
        chart.setRotation(0);
        generateData();
        mainLayout.addView(chart);

    }

    private void generateData() {

        List<SliceValue> values = new ArrayList<SliceValue>();

        SliceValue sliceValue = new SliceValue((int) 100, ChartUtils.COLOR_RED);
        sliceValue.setLabel(((int) sliceValue.getValue() + "%").toCharArray());
        values.add(sliceValue);

        SliceValue sliceValue1 = new SliceValue((int) 0  , ChartUtils.COLOR_BLUE);
        sliceValue1.setLabel(((int) sliceValue1.getValue() + "%").toCharArray());
        values.add(sliceValue1);

        data = new PieChartData(values);
        data.setHasLabels(true);
        //data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(true);
        //data.setHasCenterCircle(false);

        chart.setPieChartData(data);
        chart.setCircleFillRatio(0.5f);
    }

}


