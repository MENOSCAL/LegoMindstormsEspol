package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private ViewGroup layoutParent;



    private Button btnFinalizar;

    private TextView pregunta1, pregunta2, pregunta3, pregunta4, pregunta5;

    private RadioGroup rgPregunta1, rgPregunta2, rgPregunta3, rgPregunta4, rgPregunta5;
    private RadioButton respPregunta1, respPregunta2, respPregunta3, respPregunta4, respPregunta5;

    private float puntaje;

    private String tallerName;
    private int tallerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_taller);

        Bundle bolsaR = getIntent().getExtras();
        tallerName = bolsaR.getString("tallerNameKey");
        tallerId = bolsaR.getInt("tallerIdKey");

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Test "+ tallerName + "  --  "+tallerId);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        puntaje = 0;

        pregunta1 = (TextView) findViewById(R.id.txtPregunta1);
        pregunta2 = (TextView) findViewById(R.id.txtPregunta2);
        pregunta3 = (TextView) findViewById(R.id.txtPregunta3);
        pregunta4 = (TextView) findViewById(R.id.txtPregunta4);
        pregunta5 = (TextView) findViewById(R.id.txtPregunta5);

        rgPregunta1 = (RadioGroup) findViewById(R.id.rgPregunta1);
        rgPregunta2 = (RadioGroup) findViewById(R.id.rgPregunta2);
        rgPregunta3 = (RadioGroup) findViewById(R.id.rgPregunta3);
        rgPregunta4 = (RadioGroup) findViewById(R.id.rgPregunta4);
        rgPregunta5 = (RadioGroup) findViewById(R.id.rgPregunta5);

        //Aqui debo buscar las preguntas de un taller, busqueda se realiza por id del taller

        addPreguntaFromServer("Pregunta1", 1);
        addPreguntaFromServer("Pregunta2", 2);
        addPreguntaFromServer("Pregunta3", 3);
        addPreguntaFromServer("Pregunta4", 4);
        addPreguntaFromServer("Pregunta5", 5);

        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(this);
    }

    public void addPreguntaFromServer(String textPregunta, int numeroPregunta){
        String txtPreguntaId = "txtPregunta"+numeroPregunta;
        TextView tvPregunta = (TextView) findViewById(getResources().getIdentifier(txtPreguntaId, "id", getPackageName()));
        tvPregunta.setText(textPregunta);

        //Añadir texto a las opciones de la pregunta;
        for(int i=1; i<5; i++){
            String rbId = "rbPregunta"+numeroPregunta+"Opcion"+i;
            RadioButton resppregunta1 = (RadioButton) findViewById(getResources().getIdentifier(rbId, "id", getPackageName()));
            resppregunta1.setText(rbId);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFinalizar: {
                Toast.makeText(getApplicationContext(), "Bienvenido presionado", Toast.LENGTH_SHORT).show();
                //Aqui debo chequear las respuestas del usuario para calcular su puntaje
                // get selected radio button from radioGroup
                int selectedPregunta1 = rgPregunta1.getCheckedRadioButtonId();
                respPregunta1 = (RadioButton) findViewById(selectedPregunta1);
                String resp1 = (String) respPregunta1.getText();
                //Se envia respuesta al servidor y verificar si es correcta, si es correcta entonces se suma +10 al puntaje de la leccion
                System.out.println("resp 1 selected: " + resp1);

                int selectedPregunta2 = rgPregunta2.getCheckedRadioButtonId();
                respPregunta2 = (RadioButton) findViewById(selectedPregunta2);
                String resp2 = (String) respPregunta2.getText();
                System.out.println("resp 2 selected: " + resp2);

                int selectedPregunta3 = rgPregunta3.getCheckedRadioButtonId();
                respPregunta3 = (RadioButton) findViewById(selectedPregunta3);
                String resp3 = (String) respPregunta3.getText();
                System.out.println("resp 3 selected: " + resp3);

                int selectedPregunta4 = rgPregunta4.getCheckedRadioButtonId();
                respPregunta4 = (RadioButton) findViewById(selectedPregunta4);
                String resp4 = (String) respPregunta4.getText();
                System.out.println("resp 4 selected: " + resp4);

                int selectedPregunta5 = rgPregunta5.getCheckedRadioButtonId();
                respPregunta5 = (RadioButton) findViewById(selectedPregunta5);
                String resp5 = (String) respPregunta5.getText();
                System.out.println("resp 5 selected: " + resp5);

                break;
            }
        }
    }
}
