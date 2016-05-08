package espol.fiec.edu.lego.domain;

/**
 * Created by Usuario on 07/05/2016.
 */
public class Taller {

    private int idTaller;
    private String title;

    public Taller(String title, String idTaller) {
        this.title = title;
        this.idTaller = Integer.parseInt(idTaller);
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
}
