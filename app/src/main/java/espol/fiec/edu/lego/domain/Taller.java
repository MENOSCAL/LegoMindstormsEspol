package espol.fiec.edu.lego.domain;

/**
 * Created by Usuario on 07/05/2016.
 */
public class Taller {

    private int idTaller;
    private String title;
    private String urlImagen;
    private String puntaje;

    public Taller(String title, String idTaller, String urlImagen, String puntaje) {
        this.title = title;
        this.idTaller = Integer.parseInt(idTaller);
        this.urlImagen = urlImagen;
        this.puntaje = puntaje;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }
}
