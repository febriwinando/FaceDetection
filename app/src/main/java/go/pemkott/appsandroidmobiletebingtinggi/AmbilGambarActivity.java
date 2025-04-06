package go.pemkott.appsandroidmobiletebingtinggi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AmbilGambarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_gambar);

        String myDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+ "/eabsensi";
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("fileName");
        File file = new File(myDir, fileName);
        Bitmap gambardeteksi = BitmapFactory.decodeFile(file.getAbsolutePath());

        ImageView testing = findViewById(R.id.testing);
        testing.setImageBitmap(gambardeteksi);

//// Menerima Intent
//
//// Mengekstrak gambar dalam format Base64 dari Intent
//
//// Mengubah kembali gambar dari Base64 menjadi byte array
//        byte[] imageInByte = Base64.decode(encodedImage, Base64.DEFAULT);
//
//// Mengubah byte array menjadi Bitmap
//        Bitmap receivedBitmap = BitmapFactory.decodeByteArray(imageInByte, 0, imageInByte.length);
//
//// Gunakan receivedBitmap sesuai kebutuhan

    }

//    public void saveBitmapToFile(Bitmap bitmap, File file) {
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos); // Format dan kualitas kompresi dapat disesuaikan
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}