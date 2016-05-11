package espol.fiec.edu.lego;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.fragments.RobotFragment;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private int mItemDrawerSelected;
    private List<Robot> listRobots;

    //Web services
    private WebServicesConfiguration wsConf;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserBloqueTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);




        if(savedInstanceState != null){
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
            listRobots = savedInstanceState.getParcelableArrayList("listRobots");
        }
        else{
          //  listRobots = getSetRobotList();
            listRobots = new ArrayList<Robot>();
            wsConf = (WebServicesConfiguration) getApplicationContext();

            mAuthTask = new UserBloqueTask();
            mAuthTask.execute((Void) null);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("ALL Bloques");
        mToolbar.setLogo(R.drawable.ic_launcher);

        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

   /*public List<Robot> getSetRobotList(){
        int category=0;
        String[] title = new String[]{"Bloque Motor mediano","Bloque Motor grande","Bloque Mover la dirección","Bloque Mover tanque","Bloque Pantalla","Bloque Sonido","Bloque Luz de estado del Bloque EV3",
                "Bloque de inicio","Bloque Esperar","Bloque de bucle","Bloque Interruptor","Bloque Interrupción del bucle",
                "Bloque Sensor ultrasónico","Bloque Sensor infrarrojo","Bloque Girosensor","Bloque Sensor de color","Bloque Rotación del motor","Bloque Sensor táctil","Bloque Temporizador","Bloque Botones del Bloque EV3","Bloque Sensor de sonido NXT","Bloque Sensor de temperatura","Bloque Medidor de energía",
                "Bloque Constante","Bloque Variable","Bloque Operaciones secuenciales","Bloque Operaciones lógicas","Bloque Matemática","Bloque Redondear","Bloque Comparar","Bloque Rango","Bloque Texto","Bloque Aleatorio",
                "Bloque Acceso al archivo","Bloque Mandar mensajes","Bloque Conexión Bluetooth","Bloque Mantener activo","Bloque Comentario","Bloque Valor del sensor sin procesar","Bloque Detener","Bloque Invertir el motor","Bloque Motor sin regular"};
        String[] brands = new String[]{"Acción", "Acción","Acción","Acción","Acción","Acción","Acción","Flujo","Flujo","Flujo","Flujo","Flujo","Sensor","Sensor","Sensor","Sensor","Sensor","Sensor","Sensor","Sensor","Sensor","Sensor","Sensor","Datos","Datos","Datos","Datos","Datos","Datos","Datos","Datos","Datos","Datos","Avanzados","Avanzados","Avanzados","Avanzados","Avanzados","Avanzados","Avanzados","Avanzados","Avanzados"};
        int[] categories = new int[]{1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5};
        */
        /*int[] photos = new int[]{R.drawable.motor_mediano, R.drawable.motor_grande,R.drawable.mover_direccion,R.drawable.mover_tanque,R.drawable.pantalla,R.drawable.sonido,R.drawable.luz_estado,
        R.drawable.iniciar,R.drawable.esperar,R.drawable.bucle,R.drawable.interruptor,R.drawable.interrupcion_bucle,
        R.drawable.sensor_ultrasonico,R.drawable.sensor_infrarrojo,R.drawable.sensor_girosensor,R.drawable.sensor_color,R.drawable.rotacion_motor,R.drawable.sensor_tactil,R.drawable.temporizador,R.drawable.botones,R.drawable.sensor_sonido,R.drawable.sensor_temperatura,R.drawable.energia,
        R.drawable.constante,R.drawable.variable,R.drawable.operaciones_secuenciales,R.drawable.operaciones_logicas,R.drawable.matematica,R.drawable.redondear,R.drawable.comparar,R.drawable.rango,R.drawable.texto,R.drawable.aleatorio,
        R.drawable.acceso_archivo,R.drawable.mandar_mensaje,R.drawable.conexion_bluetooth,R.drawable.mantener_activo,R.drawable.comentario,R.drawable.sensor_sin_procesar,R.drawable.detener,R.drawable.invertir_motor,R.drawable.motor_sin_regular};
        */
        /*String[] url = new String[]{"motor_mediano","motor_grande","mover_direccion","mover_tanque","pantalla","sonido","luz_estado",
                "iniciar","esperar","bucle","interruptor","interrupcion_bucle",
                "sensor_ultrasonico","sensor_infrarrojo","sensor_girosensor","sensor_color","rotacion_motor","sensor_tactil","temporizador","botones","sensor_sonido","sensor_temperatura","energia",
                "constante","variable","operaciones_secuenciales","operaciones_logicas","matematica","redondear","comparar","rango","texto","aleatorio",
                "acceso_archivo","mandar_mensaje","conexion_bluetooth","mantener_activo","comentario","sensor_sin_procesar","detener","invertir_motor","motor_sin_regular"};


        String[] description = new String[]{"El bloque Motor mediano controla el motor mediano. Puede encender y apagar el motor, controlar el nivel de potencia, o bien, encender el motor por una cantidad específica de tiempo o rotaciones.",

                "El bloque Motor grande controla un motor grande. Puede encender y apagar un motor, controlar el nivel de potencia o encender el motor por una cantidad específica de tiempo o rotaciones.",

                "El bloque Mover la dirección puede impulsar el robot hacia adelante, hacia atrás, hacerlo girar o detenerse. Puede ajustar la dirección para hacer que el robot se mueva en dirección recta, gire en arcos o haga giros cerrados.\n" +
                        "Use el bloque Mover la dirección para vehículos robot que tengan dos motores grandes, con un motor que impulse el lado izquierdo del vehículo y otro que impulse el lado derecho. El bloque Mover la dirección controlará ambos motores al mismo tiempo, para impulsar el vehículo en la dirección que usted elija.",

                "El bloque Mover tanque puede impulsar un robot hacia adelante, hacia atrás, hacerlo girar o detenerse. Use el bloque Mover tanque para vehículos robot que tengan dos motores grandes, con un motor que impulse el lado izquierdo del vehículo y otro que impulse el lado derecho. Puede hacer que los dos motores vayan a distintas velocidades o en diferentes direcciones para que el robot gire.",

                "El bloque Pantalla puede mostrar textos o gráficos en la pantalla del Bloque EV3.",

                "El bloque Sonido reproduce un sonido con el parlante que está dentro del Bloque EV3. Usted puede reproducir archivos de sonido grabados previamente o puede especificar una nota o un tono musical.",

                "El bloque Luz de estado del Bloque EV3 controla la Luz de estado del Bloque EV3. La Luz de estado del Bloque EV3 rodea los Botones del Bloque EV3 en el frente del Bloque EV3. Puede encender la Luz de estado del Bloque EV3 en verde, naranja o rojo, apagarla, o hacer que se encienda y apague intermitentemente (pulso).",

                "El Bloque de inicio marca el inicio de una secuencia de bloques de programación en el programa. El programa puede tener más de una secuencia. Todas las secuencias con un Bloque de inicio iniciarán automáticamente cuando se ejecute el programa, y las secuencias se ejecutarán al mismo tiempo.",

                "El bloque Esperar hace que su programa espere a que suceda algo antes de continuar con el siguiente bloque de la secuencia. Puede esperar una cantidad de tiempo determinada, a que un sensor alcance un valor determinado o a que el valor de un sensor cambie.",

                "El bloque Bucle es un contenedor que puede mantener una secuencia de bloques de programación. Hará que la secuencia de bloques dentro de él se repita. Puede elegir repetir los bloques de forma ilimitada, una cantidad de veces específica o hasta que una comprobación de un sensor u otra condición sea Verdadera.\n" +
                        "Solo los bloques dentro del bucle se repetirán. Una vez que termine el bucle, el programa continuará con los bloques que estén a continuación del bucle.",

                "El bloque Interruptor es un contenedor que puede contener dos o más secuencias de bloques de programación. Cada secuencia se llama Caso. Una prueba al comienzo del Interruptor determina qué Caso se ejecutará. Solo un Caso se ejecutará cada vez que se utilice el Interruptor.\n" +
                        "La prueba del Interruptor que se muestra aquí puede decidir qué caso se ejecutará según los valores de los datos de un sensor o el valor de un Cable de datos. Después de que se selecciona y se ejecuta un caso, el programa sigue con los bloques que están después del Interruptor.",

                "El bloque Interrupción del bucle hace que un bloque Bucle termine. No se ejecutarán más bloques en la secuencia del bucle, y el programa continuará con los bloques que estén después del bucle. Puede especificar qué bloque Bucle se interrumpa utilizando el Nombre del bucle.\n" +
                        "Puede utilizar el bloque Interrupción del bucle para hacer que un bucle termine antes de lo que debería o en respuesta a una condición diferente. Puede interrumpir un bucle desde adentro del bucle mismo o desde otra secuencia de bloques que se esté ejecutando al mismo tiempo.",

                "El bloque Sensor ultrasónico obtiene datos del Sensor ultrasónico. Puede medir la distancia en pulgadas o en centímetros y obtener una salida Numérica. También puede comparar la distancia con un Valor del límite y obtener una salida Lógica (Verdadera o Falsa). También puede detectar otras señales ultrasónicas en el modo \"solo escuchar\".",

                "El bloque Sensor infrarrojo obtiene datos del Sensor infrarrojo. Puede medir los datos del sensor en los modos Proximidad, Baliza y Remoto y obtener una salida numérica. También puede comparar los datos del sensor con un valor de entrada y obtener una salida lógica (Verdadera o Falsa).",

                "El bloque Girosensor obtiene datos del Girosensor. Puede medir la razón de rotación o el ángulo de rotación y obtener una salida numérica. También puede comparar los datos del sensor con un Valor del límite y obtener una salida lógica (Verdadera o Falsa).",

                "El bloque Sensor de color obtiene datos del Sensor de color. Puede medir el color o la intensidad de la luz y obtener una salida numérica. También puede comparar los datos del sensor con un valor de entrada y obtener una salida lógica (Verdadera o Falsa).",

                "El bloque Rotación del motor obtiene datos del sensor de Rotación del motor que está incorporado en los motores medianos, grandes y NXT. Puede medir cuánto giró un motor en grados o rotaciones. También puede obtener el nivel de potencia actual en el cual está funcionando un motor.\n" +
                        "El bloque Rotación del motor también puede comparar la cantidad de rotación o el nivel de potencia con un Valor del límite y obtener una salida lógica (Verdadera o Falsa).",

                "El bloque Sensor táctil obtiene datos del Sensor táctil. Puede comprobar si el Sensor táctil está Presionado, No presionado o En contacto, y obtener una salida lógica (Verdadera o Falsa).",

                "El bloque Temporizador obtiene tiempos del temporizador interno del Bloque EV3. Puede medir el intervalo de tiempo en segundos y obtener una salida numérica. También puede comparar un tiempo con un Valor del límite y obtener una salida Lógica (Verdadera o Falsa).",

                "El bloque Botones del Bloque EV3 obtiene datos de los Botones del Bloque EV3, que son los cinco botones (Izquierda, Centro, Derecha, Arriba y Abajo) en el frente del Bloque EV3. Puede saber qué botón se presiona y obtener una salida numérica. También puede comprobar uno o más botones para verificar si uno está siendo Presionado, No presionado o En contacto, y obtener una salida lógica (Verdadera o Falsa).",

                "El bloque Sensor de sonido NXT obtiene datos del Sensor de sonido NXT. Puede medir el nivel del sonido como porcentaje (entre 0 y 100) y obtener una salida numérica. También puede comparar el nivel del sonido con un Valor del límite y obtener una salida lógica (Verdadera o Falsa).",

                "El bloque Sensor de temperatura obtiene datos del Sensor de temperatura. Puede medir la distancia en grados Celsius (°C) o grados Fahrenheit (°F) y obtener una salida Numérica. También puede comparar la temperatura con un valor del límite y obtener una salida Lógica (Verdadera o Falsa).",

                "El bloque Medidor de energía obtiene datos del Medidor de energía. El medidor de energía es parte del Conjunto agregado de energía renovable. El bloque Medidor de energía puede medir el almacenamiento, la entrada y el consumo de energía eléctrica de los componentes eléctricos que están conectados al Medidor de energía. También puede comparar los datos con un Valor del límite y obtener una salida Lógica (Verdadera o Falsa).",

                "El bloque Constante le permite ingresar un valor que puede utilizar en varias ubicaciones diferentes de su programa. Si cambia el valor de la constante, todas las ubicaciones donde utilice la constante obtendrán el valor actualizado.",

                "El bloque Variable le permite leer o escribir una Variable en su programa. También puede crear una nueva variable y ponerle un nombre.\n" +
                        "Una Variable es una ubicación en la memoria del Bloque EV3 que puede almacenar un valor. Puede escribir en una Variable para almacenar un valor de datos. Más adelante en el programa, puede leer la Variable para acceder al valor almacenado.\n" +
                        "Cada variable tiene un Tipo y un Nombre. Los diferentes Tipos son Numérico, Lógico, Texto, Secuencia numérica y Secuencia Lógica. Puede elegir el Nombre de la variable, el cual se utiliza para identificar la variable.\n" +
                        "El valor de una variable puede cambiar mientras se ejecuta el programa. Cada vez que escriba en una variable, cualquier valor anterior se borra y se reemplaza con el valor nuevo. Por ejemplo, puede utilizar una variable llamada \"Luz Máx\" para hacer un seguimiento de la intensidad de la luz más alta que su robot haya medido en el Sensor de color hasta el momento. Cada vez que el robot detecte un valor más alto, puede escribir el nuevo valor en la variable \"Luz Máx\".",

                "El bloque Operaciones secuenciales realiza operaciones en tipos de datos de Secuencia numérica y Secuencia lógica. Puede crear una secuencia, agregar elementos, leer y escribir elementos individuales y obtener la longitud de una secuencia.",

                "El bloque Operaciones lógicas realiza una operación lógica en sus entradas y muestra el resultado. Una operación lógica toma entradas que son Verdaderas o Falsas y produce una salida Verdadera/Falsa. Las operaciones lógicas disponibles son AND, OR, XOR y NOT.",

                "El bloque Matemática realiza un cálculo matemático en sus entradas y muestra el resultado. Puede hacer una operación matemática sencilla con una o dos entradas o ingresar una fórmula con hasta cuatro entradas.",

                "El bloque Redondear redondea un número decimal a un valor entero. Puede redondear un número hacia arriba, abajo o al entero más cercano. También puede truncar un número a una cierta cantidad de lugares decimales.",

                "El bloque Comparar compara dos números para averiguar si son iguales o para saber qué número es mayor. Puede elegir una de las seis comparaciones diferentes. El resultado es Verdadero o Falso.",

                "El bloque Rango comprueba si un número está dentro o fuera de un conjunto numérico específico. El resultado es Verdadero o Falso.",

                "El bloque Texto puede combinar hasta tres cadenas de texto en una sola cadena.",

                "El bloque Aleatorio puede mostrar un valor Numérico o Lógico aleatorio. Puede utilizar el resultado del bloque Aleatorio para hacer que el robot elija de manera aleatoria diferentes acciones.",

                "El bloque Acceso al archivo le permite leer y escribir datos en archivos y desde archivos en su Bloque EV3.",

                "El bloque Mandar mensajes se utiliza para enviar Mensajes Bluetooth entre Bloques EV3. Para enviar o recibir un mensaje, los Bloques EV3 deben estar conectados a través del menú Bluetooth en el bloque o a través del bloque Conexión Bluetooth.",

                "Utilice el bloque Conexión Bluetooth para encender o apagar el Bluetooth, conectarse a otro dispositivo Bluetooth, o para cerrar la conexión con otro dispositivo Bluetooth. Los dispositivos Bluetooth incluyen otros Bloques EV3, teléfonos celulares y equipos, aunque no todos los dispositivos Bluetooth serán compatibles para conectarse al Bloque EV3. Si ya estableció una conexión Bluetooth utilizando el menú en el Bloque EV3, no necesita utilizar el bloque Conexión Bluetooth.\n" +
                        "El protocolo Bluetooth en el sistema EV3 funciona eligiendo un Bloque EV3 maestro y utilizándolo para conectarse a un Bloque EV3 esclavo. Un Bloque EV3 maestro puede conectarse con siete Bloques EV3 esclavos inclusive. El Bloque EV3 maestro puede enviar mensajes a cada Bloque EV3 esclavo. Los Bloques EV3 esclavos, sin embargo, solo pueden enviar mensajes al Bloque EV3 maestro. Los Bloques EV3 esclavos no pueden enviar mensajes directamente a otros Bloques EV3 esclavos.\n" +
                        "Después de establecer la conexión Bluetooth, el programa puede enviar y recibir mensajes.",

                "El bloque Mantener activo reinicia el temporizador de suspensión del Bloque EV3. Utilice este bloque si el programa necesita esperar más tiempo que el del Ajuste de suspensión del Bloque EV3. El Ajuste de suspensión se configura con la Interfaz del Bloque EV3.",

                "El Bloque Comentario le permite escribir comentarios dentro de un programa. Este bloque no es un bloque de programación, lo que significa que no hay ninguna acción programable asociada a él. Con frecuencia, se usa para proporcionar una explicación de los bloques siguientes y de las acciones esperadas.",

                "El bloque Valor del sensor sin procesar muestra la lectura del sensor sin procesar, que es un valor entre 0 y 1023. El bloque Valor del sensor sin procesar tiene solo un modo.",

                "El bloque Detener programa finaliza de inmediato cualquier secuencia de bloques de programación y termina el programa.\n" +
                        "Puede colocar un bloque Detener programa al final de cualquier secuencia en su programa, incluyendo las secuencias que aparecen dentro de un Interruptor, Bucle o Mi bloque. Si el bloque Detener programa se alcanza y se ejecuta, finalizará todo el programa.",

                "El bloque Invertir el motor cambia la dirección de rotación de un motor. Cuando invierte la dirección de un motor, un bloque de programación que normalmente hace que el motor gire en sentido horario hará que este gire en sentido antihorario.",

                "El bloque Motor sin regular controla tanto los motores medianos como los grandes. Puede encender un motor y controlar su nivel de potencia."
        };

        List<Robot> listAux = new ArrayList<>();

        for(int i = 0; i < title.length; i++){
            Robot c = new Robot( title[i % title.length], brands[ i % brands.length], description[i % description.length] );

            //c.setPhoto(photos[i % photos.length]);
            c.setCategory(categories[i % categories.length]);

            c.setUrl(url[i % url.length]);


            if(category != 0 && c.getCategory() != category){
                continue;
            }
            listAux.add(c);
        }
        return(listAux);
    }
    */
    public List<Robot> getRobotsByCategory(int category){
        List<Robot> listAux = new ArrayList<>();
        for(int i = 0; i < listRobots.size(); i++){
            if(category != 0 && listRobots.get(i).getCategory() != category){
                continue;
            }
            listAux.add(listRobots.get(i));
        }
        return(listAux);
    }
    public List<Robot> getListRobots(){
        return (listRobots);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putInt("mItemDrawerSelected", mItemDrawerSelected);
        outState.putParcelableArrayList("ListRobots", (ArrayList<Robot>) listRobots);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        RobotFragment frag = null;

        if (id == R.id.nav_all) { //Todos los Bloques
            frag = new RobotFragment();
            frag.setGroup(0);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_accion) { //Bloques de Acción
            frag = new RobotFragment();
            frag.setGroup(1);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_flujo) { //Bloques de Flujo
            frag = new RobotFragment();
            frag.setGroup(2);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_operaciones) { //Bloques de Operaciones
            frag = new RobotFragment();
            frag.setGroup(3);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_sensores) { //Bloques de Sensores
            frag = new RobotFragment();
            frag.setGroup(4);
            mToolbar.setTitle(item.getTitle());
        } else if (id == R.id.nav_avanzados) { //Bloques Avanzados
            frag = new RobotFragment();
            frag.setGroup(5);
            mToolbar.setTitle(item.getTitle());
        }

        //listRobots = getSetRobotList();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserBloqueTask extends AsyncTask<Void, Void, Boolean> {
        
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            ArrayList<String> name_categories = new ArrayList<String>();
            ArrayList<String> brands1 = new ArrayList<String>();

            ArrayList<String> idCategory = new ArrayList<String>();

            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_BLOQUES());
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_BLOQUES(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Vector<?> responseVector = (Vector<?>) response.getProperty(0);
                String categoryB = "";
                String titleB = "";
                String descriptionB = "";
                int[] categories = new int[]{1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5};
                int category=0;
                String[] url = new String[]{"motor_mediano","motor_grande","mover_direccion","mover_tanque","pantalla","sonido","luz_estado",
                        "iniciar","esperar","bucle","interruptor","interrupcion_bucle",
                        "sensor_ultrasonico","sensor_infrarrojo","sensor_girosensor","sensor_color","rotacion_motor","sensor_tactil","temporizador","botones","sensor_sonido","sensor_temperatura","energia",
                        "constante","variable","operaciones_secuenciales","operaciones_logicas","matematica","redondear","comparar","rango","texto","aleatorio",
                        "acceso_archivo","mandar_mensaje","conexion_bluetooth","mantener_activo","comentario","sensor_sin_procesar","detener","invertir_motor","motor_sin_regular"};



                for (int i = 0; i <responseVector.size(); ++i) {
                    SoapObject datos =(SoapObject)responseVector.get(i);
                    categoryB          = datos.getProperty("Category_idCategory").toString();
                    titleB             = datos.getProperty("Title").toString();
                    descriptionB       = datos.getProperty("Description").toString();

                    Robot c = new Robot( titleB, categoryB, descriptionB );

                    //c.setPhoto(photos[i % photos.length]);
                    c.setCategory(categories[i % categories.length]);

                    c.setUrl(url[i % url.length]);


                    if(category != 0 && c.getCategory() != category){
                        continue;
                    }
                    listRobots.add(c);

                }

            } catch (Exception e) {
                Log.i("Respuesta", "excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            //FRAGMENT
            RobotFragment frag = (RobotFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
            if(frag == null){
                frag = new RobotFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.rl_fragment_container,frag, "mainFrag");
                ft.commit();
            }
        }
    }
}
