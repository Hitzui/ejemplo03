package innovasoft.com.ejemplo01.converter;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by josemiguel on 26/02/2018.
 */

public class ImageConverterFromUrl {

    private ImageView imageView;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView imageConverter(String url) {
        try {
            URL myFileUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
            return imageView;
        } catch (Exception e) {
            return  null;
        }
    }
}
