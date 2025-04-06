package go.pemkott.appsandroidmobiletebingtinggi.kameralampiran;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import go.pemkott.appsandroidmobiletebingtinggi.DeteksiWajah.CameraActivity;
import go.pemkott.appsandroidmobiletebingtinggi.R;
import go.pemkott.appsandroidmobiletebingtinggi.konstanta.AmbilFoto;

public class CameraLampiranActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private Camera mCamera;
    private AutoFitTextureView mTextureView;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mTextureView.isAvailable()) {
            openCamera(mTextureView.getSurfaceTexture());
        } else {
            mTextureView.setSurfaceTextureListener(this);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_lampiran);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));
        window.setNavigationBarColor(getResources().getColor(R.color.black));

        mTextureView = findViewById(R.id.texture);
        ImageView btnCapture = findViewById(R.id.ivTakeFoto);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        mTextureView.setSurfaceTextureListener(this);
    }


    private void takePicture() {
        if (mCamera == null) {
            return;
        }

        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                // Proses gambar di sini, misalnya menyimpan ke file atau menampilkan di ImageView
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                // Contoh menyimpan gambar ke file
                saveImage(bitmap);
                // Restart preview setelah mengambil gambar
                mCamera.startPreview();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("KEY_HASIL", timeStamp);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
    AmbilFoto ambilFoto = new AmbilFoto(CameraLampiranActivity.this);

    private void saveImage(Bitmap bitmap) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("Error", "Error creating media file, check storage permissions");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();

            Log.d("Success", "Image saved: " + pictureFile.getAbsolutePath());
        } catch (IOException e) {
            Log.d("Error", "Error accessing file: " + e.getMessage());
        }


        String currentPhotoPath = pictureFile.getAbsolutePath();

        Bitmap selectedBitmap = ambilFoto.fileBitmapCompress(pictureFile);
        Bitmap rotationBitmapSurat = Bitmap.createBitmap(selectedBitmap, 0,0, selectedBitmap.getWidth(), selectedBitmap.getHeight(), AmbilFoto.exifInterface123(currentPhotoPath), true);
//
        File filebaru = new File(mediaStorageDir, timeStamp);
        if (filebaru.exists()) filebaru.delete();

        try {
            FileOutputStream out = new FileOutputStream(filebaru);
            rotationBitmapSurat.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    String timeStamp;
    File mediaFile;
    File mediaStorageDir;
    private File getOutputMediaFile() {
        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "eabsensi");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("CameraAppFolder", "failed to create directory");
                return null;
            }
        }

        timeStamp = "lampiran-"+new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date())+ ".jpg";

        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                timeStamp );



        return mediaFile;
    }

    private void openCamera(SurfaceTexture surface) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
            return;
        }

        mCamera = Camera.open(0);
        try {
            mCamera.setPreviewTexture(surface);
            int rotation = getWindowManager().getDefaultDisplay()
                    .getRotation();
            mCamera.setDisplayOrientation(ORIENTATIONS.get(rotation));
            Camera.Parameters params =  mCamera.getParameters();
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            Camera.Size size = params.getPreviewSize();

            int orientation = getResources().getConfiguration().orientation;
            int previewWidth = size.width;
            int previewHeight = size.height;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mTextureView.setAspectRatio(
                        previewWidth, previewHeight);
            } else {
                mTextureView.setAspectRatio(
                        previewHeight, previewWidth);
            }

            Log.d("PanjangLebar", previewHeight+" "+previewWidth);

            mCamera.setParameters(params);
            mCamera.startPreview();
        } catch (IOException ioe) {
        }
    }
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        openCamera(surface);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    private void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}