package espol.fiec.edu.lego.asynctasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import espol.fiec.edu.lego.extras.ImageHelper;

/**
 * Created by mm on 28/04/2016.
 */
public class ImageLoad extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;
    private Context context;
    private int width;
    private int height;

    public ImageLoad(String url, ImageView imageView, Context context, int width, int height) {
        this.url = url;
        this.imageView = imageView;
        this.context = context;
        this.width = width;
        this.height = height;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();

            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            myBitmap = Bitmap.createScaledBitmap(myBitmap, width, height, false);
            myBitmap = ImageHelper.getRoundedCornerBitmap(context, myBitmap, 10, width, height, false, false, true, true);


            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        imageView.setImageBitmap(result);
    }

}
