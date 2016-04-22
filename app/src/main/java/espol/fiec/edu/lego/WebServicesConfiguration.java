package espol.fiec.edu.lego;

import android.app.Application;

/**
 * Created by DESARROLLO-1 on 20/04/2016.
 */
public class WebServicesConfiguration extends Application {
    //Configuracion
    private String NAMESPACE = "urn:LegoMindstormsEspol";
    private String URL= "http://www.corporacionsmartest.com/lego_mindstorm/web_services_lego/wsLegoMindstrom.php";
    private String SOAP_ACTION = "urn:LegoMindstormsEspol#";

    //Nombre de métodos
    private String METHOD_NAME_LOGIN = "login";

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
}
