package espol.fiec.edu.lego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Usuario on 20/03/2016.
 */
public class GuiaUsoActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;

    private TextView tvBienvenido;
    private TextView tvAspectosGenerales, tvBloqueEV3, tvMotoresEV3, tvSensoresEV3, tvConexionEV3, tvInterfazEV3;
    private TextView tvInstalacionSoftware, tvPropiedadesEstructuraProyecto, tvMisionesRobot, tvProgramacion, tvPaginaHardware, tvEditorContenidos, tvHerramientas;
    private TextView tvAyudaSoftware, tvActualizacionFirmware, tvReinicioBloque;
    private TextView tvListaArchivoSonidos, tvListaArchivoImagenes, tvListaRecursos, tvListaElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_uso);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Manuales");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvBienvenido = (TextView) findViewById(R.id.tvBienvenido);

        tvAspectosGenerales = (TextView) findViewById(R.id.tvAspectosGenerales);
        tvBloqueEV3 = (TextView) findViewById(R.id.tvBloqueEV3);
        tvMotoresEV3 = (TextView) findViewById(R.id.tvMotoresEV3);
        tvSensoresEV3 = (TextView) findViewById(R.id.tvSensoresEV3);
        tvConexionEV3 = (TextView) findViewById(R.id.tvConexionTecnologiaEV3);
        tvInterfazEV3 = (TextView) findViewById(R.id.tvInterfazEV3);

        tvInstalacionSoftware = (TextView) findViewById(R.id.tvInstalacionSoftware);
        tvPropiedadesEstructuraProyecto = (TextView) findViewById(R.id.tvPropiedadesEstructura);
        tvMisionesRobot = (TextView) findViewById(R.id.tvMisionesRobot);
        tvProgramacion = (TextView) findViewById(R.id.tvProgramacion);
        tvPaginaHardware = (TextView) findViewById(R.id.tvPaginaHardware);
        tvEditorContenidos = (TextView) findViewById(R.id.tvEditorContenidos);
        tvHerramientas = (TextView) findViewById(R.id.tvHerramientas);

        tvAyudaSoftware = (TextView) findViewById(R.id.tvAyudaSoftware);
        tvActualizacionFirmware = (TextView) findViewById(R.id.tvActualizacionFirmware);
        tvReinicioBloque = (TextView) findViewById(R.id.tvReinicioBloque);

        tvListaArchivoSonidos = (TextView) findViewById(R.id.tvListaArchivosSonido);
        tvListaArchivoImagenes = (TextView) findViewById(R.id.tvListaArchivosImagen);
        tvListaRecursos = (TextView) findViewById(R.id.tvListaRecursos);
        tvListaElementos = (TextView) findViewById(R.id.tvListaElementos);

        //Introduccion
        tvBienvenido.setOnClickListener(this);

        //Tecnologia EV3
        tvAspectosGenerales.setOnClickListener(this);
        tvBloqueEV3.setOnClickListener(this);
        tvMotoresEV3.setOnClickListener(this);
        tvSensoresEV3.setOnClickListener(this);
        tvConexionEV3.setOnClickListener(this);
        tvInterfazEV3.setOnClickListener(this);

        //Software EV3
        tvInstalacionSoftware.setOnClickListener(this);
        tvPropiedadesEstructuraProyecto.setOnClickListener(this);
        tvMisionesRobot.setOnClickListener(this);
        tvProgramacion.setOnClickListener(this);
        tvPaginaHardware.setOnClickListener(this);
        tvEditorContenidos.setOnClickListener(this);
        tvHerramientas.setOnClickListener(this);

        //Solución de problemas
        tvAyudaSoftware.setOnClickListener(this);
        tvActualizacionFirmware.setOnClickListener(this);
        tvReinicioBloque.setOnClickListener(this);

        //Información Útil
        tvListaArchivoSonidos.setOnClickListener(this);
        tvListaArchivoImagenes.setOnClickListener(this);
        tvListaRecursos.setOnClickListener(this);
        tvListaElementos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvBienvenido: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "introduction.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvAspectosGenerales: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa = new Bundle();
                bolsa.putString("pdfnameKey", "aspectosGenerales.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);

                break;
            }

            case R.id.tvBloqueEV3: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "bloqueEV3.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvMotoresEV3: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "motores.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvSensoresEV3: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "sensoresEV3.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvConexionTecnologiaEV3: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "conexionTecnologiaEV3.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvInterfazEV3: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "interfazEV3.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvInstalacionSoftware: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "instalacionSoftware.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvPropiedadesEstructura: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "propiedadesEstructuraProyecto.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvMisionesRobot: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "misionRobot.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvProgramacion: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "programacion.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvPaginaHardware: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "paginaHardware.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvEditorContenidos: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "editorContenidos.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvHerramientas: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "herramientas.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvAyudaSoftware: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "ayudaSoftware.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvActualizacionFirmware: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "actualizacionFirmware.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvReinicioBloque: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "reinicioBloqueEV3.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvListaArchivosSonido: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "listaArchivosSonido.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvListaArchivosImagen: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "listaArchivosImagen.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvListaRecursos: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "listaRecursos.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
            case R.id.tvListaElementos: {
                Intent pdfViewActivity = new Intent(this, PDFViewActivity.class);

                Bundle bolsa=new Bundle();
                bolsa.putString("pdfnameKey", "listaElementos.pdf");

                pdfViewActivity.putExtras(bolsa);

                startActivity(pdfViewActivity);
                break;
            }
        }
    }
}
