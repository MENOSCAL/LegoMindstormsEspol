package espol.fiec.edu.lego;

import android.app.Application;

/**
 * Created by DESARROLLO-1 on 20/04/2016.
 */
public class WebServicesConfiguration extends Application {
    //Configuracion
    private String NAMESPACE = "urn:HogarDeCristo";
    private String URL= "http://www.corporacionsmartest.com/hogar_de_cristo/wsHogarDeCristo.php";
    private String SOAP_ACTION = "urn:HogarDeCristo#";

    //Nombre de métodos
    private String METHOD_NAME_LOGIN = "login_user";
    private String METHOD_GET_TALLERES = "get_talleres";

    //Getters
    public String getNAMESPACE() {
        return NAMESPACE;
    }

    public String getURL() {
        return URL;
    }

    public String getSOAP_ACTION() {
        return SOAP_ACTION;
    }

    public String getMETHOD_NAME_LOGIN() {
        return METHOD_NAME_LOGIN;
    }

    public String getMETHOD_GET_TALLERES() {
        return METHOD_GET_TALLERES;
    }
}
