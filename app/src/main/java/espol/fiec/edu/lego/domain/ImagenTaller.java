package espol.fiec.edu.lego.domain;

/**
 * Created by Usuario on 15/05/2016.
 */
public class ImagenTaller {
    private int idImagenTaller;
    private String urlImagen;
    private int idTaller;

    public ImagenTaller(String idImagenTaller, String urlImagen, String idTaller) {
        this.idImagenTaller =Integer.parseInt(idImagenTaller);
        this.urlImagen = urlImagen;
        this.idTaller = Integer.parseInt(idTaller);
    }

    public int getIdImagenTaller() {
        return idImagenTaller;
    }

    public void setIdImagenTaller(int idImagenTaller) {
        this.idImagenTaller = idImagenTaller;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }
}
