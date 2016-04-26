package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.easing.linear.Linear;

/**
 * Created by Usuario on 26/03/2016.
 */
public class ListaTalleresActivity  extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewGroup layoutParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_talleres);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Talleres");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutParent = (ViewGroup) findViewById(R.id.layoutTalleres);

        addTaller(layoutParent, "Taller01");
        addTaller(layoutParent, "Taller02");
        addTaller(layoutParent, "Taller03");
    }

    public void addTaller(ViewGroup layoutParent, final String title){
        final float scale = getResources().getDisplayMetrics().density;
        int padding_10dp = (int) (10 * scale + 0.5f);
        int padding_15dp = (int) (15 * scale + 0.5f);

        final LinearLayout layoutTaller = new LinearLayout(this);
        //layoutTaller.setId('5');
        layoutTaller.setOrientation(LinearLayout.VERTICAL);
        layoutTaller.setClickable(true);
        layoutTaller.setBackgroundDrawable( getResources().getDrawable(R.drawable.rounded_layout));
        layoutTaller.setPadding(padding_15dp,padding_15dp,padding_15dp,padding_15dp);

        LinearLayout.LayoutParams layoutTallerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutTallerParams.setMargins(0, 0, 0, padding_10dp);

        //TITULO DEL ROBOT
        TextView valueTV = new TextView(this);
        valueTV.setText(title);
        valueTV.setTextSize((int) (10 * scale + 0.5f));
        valueTV.setTextColor(getResources().getColor(R.color.colorPrimary));
        LinearLayout.LayoutParams tvLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvLayoutParams.gravity = Gravity.CENTER;

        valueTV.setLayoutParams(tvLayoutParams);
        layoutTaller.addView(valueTV);

        //Imagen del robot
        ImageView ivRobot = new ImageView(this);
        ivRobot.setBackgroundDrawable(getResources().getDrawable(R.drawable.tracker));

        LinearLayout.LayoutParams ivRobotLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ivRobot.setLayoutParams(ivRobotLayoutParams);
        layoutTaller.addView(ivRobot);

        //ClickListener al layoutTaller
        layoutTaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui debo saltar a la pantalla del taller
                Intent tallerActivity = new Intent(ListaTalleresActivity.this, TallerActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("tallerKey", title);

                tallerActivity.putExtras(bolsa);

                startActivity(tallerActivity);
            }
        });

        //Añado titulo e imagen al layout del taller
        layoutParent.addView(layoutTaller, layoutTallerParams);
    }


}
