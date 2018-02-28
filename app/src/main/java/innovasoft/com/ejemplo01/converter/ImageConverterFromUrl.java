package innovasoft.com.ejemplo01.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by josemiguel on 26/02/2018.
 */

public class ImageConverterFromUrl {

    private ImageView imageView;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Bitmap loadImageFromURL(String fileUrl) {
        try {
            URL myFileUrl = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            return  BitmapFactory.decodeStream(is);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
