package espol.fiec.edu.lego.domain;

/**
 * Created by Usuario on 14/05/2016.
 */
public class Respuesta {

    private int idRespuesta;
    private int idPregunta;
    private String name;
    private  boolean valido;

    public Respuesta() {
    }

    public Respuesta(String idRespuesta, String idPregunta, String name, String valido) {
        this.idRespuesta = Integer.parseInt(idRespuesta);
        this.idPregunta = Integer.parseInt(idPregunta);
        this.name = name;

        if(valido.equals("1")){
            this.valido = true;
        }
        else{
            this.valido = false;
        }

    }

    public Respuesta(String idRespuesta, String idPregunta, String name) {
        this.idRespuesta = Integer.parseInt(idRespuesta);
        this.idPregunta = Integer.parseInt(idPregunta);
        this.name = name;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }
}
