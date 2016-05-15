package espol.fiec.edu.lego.domain;

/**
 * Created by Usuario on 07/05/2016.
 */
public class Taller {

    private int idTaller;
    private String title;
    private String urlImagen;

    public Taller(String title, String idTaller, String urlImagen) {
        this.title = title;
        this.idTaller = Integer.parseInt(idTaller);
        this.urlImagen = urlImagen;
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
}
