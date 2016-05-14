package espol.fiec.edu.lego.domain;

/**
 * Created by Usuario on 14/05/2016.
 */
public class Pregunta {
    private int idPregunta;
    private int idTaller;
    private String name;

    public Pregunta(String idPregunta, String idTaller, String name) {
        this.idPregunta = Integer.parseInt(idPregunta);
        this.idTaller = Integer.parseInt(idTaller);
        this.name = name;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
