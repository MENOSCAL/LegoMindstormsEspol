package espol.fiec.edu.lego.domain;

/**
 * Created by Usuario on 10/06/2016.
 */
public class PreguntaRespuesta {

    private String pregunta;
    private String respuesta;

    public PreguntaRespuesta() {
    }

    public PreguntaRespuesta(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
