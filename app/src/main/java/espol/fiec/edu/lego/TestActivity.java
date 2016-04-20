package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

/**
 * Created by Usuario on 16/04/2016.
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private Button btnFinalizar;

    private RadioGroup rgPregunta1, rgPregunta2, rgPregunta3;
    private RadioButton respPregunta1, respPregunta2, respPregunta3;

    private float puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_taller);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Test");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        puntaje = 0;

        rgPregunta1 = (RadioGroup) findViewById(R.id.rgPregunta1);
        rgPregunta2 = (RadioGroup) findViewById(R.id.rgPregunta2);
        rgPregunta3 = (RadioGroup) findViewById(R.id.rgPregunta3);

        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnFinalizar: {
                Toast.makeText(getApplicationContext(), "Bienvenido presionado", Toast.LENGTH_SHORT).show();
                //Aqui debo chequear las respuestas del usuario para calcular su puntaje
                // get selected radio button from radioGroup
                int selectedPregunta1 =rgPregunta1.getCheckedRadioButtonId();
                respPregunta1 = (RadioButton) findViewById(selectedPregunta1);
                String resp1 = (String) respPregunta1.getText();
                //Se envia respuesta al servidor y verificar si es correcta, si es correcta entonces se suma +10 al puntaje de la leccion
                System.out.println("resp 1 selected: "+resp1);

                int selectedPregunta2 =rgPregunta2.getCheckedRadioButtonId();
                respPregunta2 = (RadioButton) findViewById(selectedPregunta2);
                String resp2 = (String) respPregunta2.getText();
                System.out.println("resp 1 selected: "+resp2);

                int selectedPregunta3 =rgPregunta3.getCheckedRadioButtonId();
                respPregunta3 = (RadioButton) findViewById(selectedPregunta3);
                String resp3 = (String) respPregunta3.getText();
                System.out.println("resp 1 selected: "+resp3);

                break;
            }
        }
    }
}
