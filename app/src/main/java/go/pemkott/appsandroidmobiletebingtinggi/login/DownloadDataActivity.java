package go.pemkott.appsandroidmobiletebingtinggi.login;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import go.pemkott.appsandroidmobiletebingtinggi.NewDashboard.DashboardVersiOne;
import go.pemkott.appsandroidmobiletebingtinggi.R;
import go.pemkott.appsandroidmobiletebingtinggi.api.HttpService;
import go.pemkott.appsandroidmobiletebingtinggi.api.RetroClient;
import go.pemkott.appsandroidmobiletebingtinggi.database.DatabaseHelper;
import go.pemkott.appsandroidmobiletebingtinggi.dialogview.DialogView;
import go.pemkott.appsandroidmobiletebingtinggi.model.DataEmployee;
import go.pemkott.appsandroidmobiletebingtinggi.model.KegiatanIzin;
import go.pemkott.appsandroidmobiletebingtinggi.model.Koordinat;
import go.pemkott.appsandroidmobiletebingtinggi.model.TimeTables;
import go.pemkott.appsandroidmobiletebingtinggi.model.WaktuSift;
import go.pemkott.appsandroidmobiletebingtinggi.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DownloadDataActivity extends AppCompatActivity {
    ProgressBar progressBarHorizontal;
    DatabaseHelper databaseHelper;

    String sId, sToken, sEmployId, eOPD;
    HttpService holderAPI;
    DialogView dialogView = new DialogView(DownloadDataActivity.this);
    TextView tvinfoDownload;

    int download = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);
        databaseHelper = new DatabaseHelper(this);

        getWindow().addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://absensi.tebingtinggikota.go.id/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        holderAPI = retrofit.create(HttpService.class);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        tvinfoDownload = findViewById(R.id.tvinfoDownload);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        progressBarHorizontal= findViewById(R.id.progressBarHorizontal);

        if(NetworkUtils.isConnectedFast(this)){
            dataUser();
        }else{
            pesanError();
        }
    }




    public void koordinat(){

        Call<List<Koordinat>> callKoordinat = holderAPI.getUrlKoordinat("https://absensi.tebingtinggikota.go.id/api/koordinat");
        callKoordinat.enqueue(new Callback<List<Koordinat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Koordinat>> call, @NonNull Response<List<Koordinat>> response) {
                if (!response.isSuccessful()) {
                    viewNotifKosong("Gagal mengunduh data, periksa koneksi internet anda dan coba kembali.", "", 2);
                }

                List<Koordinat> koordinats = response.body();
                assert koordinats != null;
                for (Koordinat koordinat : koordinats) {
                    databaseHelper.insertDataKoordinat(koordinat.getId(), koordinat.getOpd_id(), koordinat.getAlamat(), koordinat.getLet(), koordinat.getLng());

                }

                download = 3;
            }

            @Override
            public void onFailure(@NonNull Call<List<Koordinat>> call, @NonNull Throwable t) {
                dialogView.viewNotifKosong(DownloadDataActivity.this, "Gagal menerima data koordinas,", "mohon periksa jaringan internet anda.");

            }
        });
    }

    public void koordinat_e(){
        Call<List<Koordinat>> callKoordinates = holderAPI.getUrlKoordinat("https://absensi.tebingtinggikota.go.id/api/koordinatemployee?id="+sEmployId);
        callKoordinates.enqueue(new Callback<List<Koordinat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Koordinat>> call, @NonNull Response<List<Koordinat>> response) {
                List<Koordinat> koordinats = response.body();
                if (!response.isSuccessful()) {

                    viewNotifKosong("Gagal mengunduh data, periksa koneksi internet anda dan coba kembali.", "", 4);

                }

                assert koordinats != null;
                for (Koordinat koordinat : koordinats) {
                    if (koordinat.getStatus().equals("ada")) {
                        databaseHelper.insertDataKoordinatEmployee(koordinat.getId(), sEmployId, koordinat.getAlamat(), koordinat.getLet(), koordinat.getLng());
                    }
                }

                download = 6;
            }

            @Override
            public void onFailure(@NonNull Call<List<Koordinat>> call, @NonNull Throwable t) {
                dialogView.viewNotifKosong(DownloadDataActivity.this, "Gagal menerima data koordinas,", "mohon periksa jaringan internet anda.");

            }
        });
    }
    private void kegiatan() {

        Call<List<KegiatanIzin>> callKegiatanIzins = holderAPI.getUrlKegiatan("https://absensi.tebingtinggikota.go.id/api/kegiatannew?opd="+eOPD, "Bearer "+sToken);
        callKegiatanIzins.enqueue(new Callback<List<KegiatanIzin>>() {
            @Override
            public void onResponse(@NonNull Call<List<KegiatanIzin>> call, @NonNull Response<List<KegiatanIzin>> response) {
                List<KegiatanIzin> kegiatanIzins = response.body();
                if (!response.isSuccessful()) {

                    dialogView.viewNotifKosong(DownloadDataActivity.this, "Gagal memeriksa data absensi,", "mohon periksa internet anda.");

                }

                assert kegiatanIzins != null;
                for (KegiatanIzin kegiatanIzin : kegiatanIzins) {
                    databaseHelper.insertResourceKegiatan(String.valueOf(kegiatanIzin.getId()), kegiatanIzin.getTipe(), kegiatanIzin.getKet());
                }

                tvinfoDownload.setText("unduh data jadwal...");
                download = 5;
            }

            @Override
            public void onFailure(@NonNull Call<List<KegiatanIzin>> call, @NonNull Throwable t) {
                pesanError();

            }
        });

    }

    private void timetable(String idEmployee, String token){
        Call<List<TimeTables>> timeTable = holderAPI.getUrlTimeTable("https://absensi.tebingtinggikota.go.id/api/timetable?employee_id="+idEmployee, "Bearer "+token);
        timeTable.enqueue(new Callback<List<TimeTables>>() {
            @Override
            public void onResponse(@NonNull Call<List<TimeTables>> call, @NonNull Response<List<TimeTables>> response) {
                List<TimeTables> timeTables = response.body();
                if (!response.isSuccessful()) {
                    viewNotifKosong("Gagal mengunduh data, periksa koneksi internet anda dan coba kembali.", "", 3);

                }


                assert timeTables != null;
                for (TimeTables timeTable : timeTables) {
                    databaseHelper.insertDataTimeTable(String.valueOf(timeTable.getId()), timeTable.getEmployee_id(), timeTable.getTimetable_id(), timeTable.getInisial(), String.valueOf(timeTable.getHari()), timeTable.getMasuk(), timeTable.getPulang());

                }

                download = 4;
            }

            @Override
            public void onFailure(@NonNull Call<List<TimeTables>> call, @NonNull Throwable t) {
                pesanError();

            }
        });

    }

    public void getDataPegawai(String idE){
        Call<DataEmployee> calls = RetroClient.getInstance().getApi().dataEmployee(idE);
        calls.enqueue(new Callback<DataEmployee>() {
            @Override
            public void onResponse(@NonNull Call<DataEmployee> call, @NonNull Response<DataEmployee> response) {
                if (!response.isSuccessful()) {
                    viewNotifKosong("Gagal mengunduh data, periksa koneksi internet anda dan coba kembali.", "", 1);
                }

                assert response.body() != null;
                boolean insertDataPegawai = databaseHelper.insertDataEmployee(
                        response.body().getId(),
                        response.body().getAtasan_id1(),
                        response.body().getAtasan_id2(),
                        response.body().getPosition_id(),
                        response.body().getOpd_id(),
                        response.body().getNip(),
                        response.body().getNama(),
                        response.body().getEmail(),
                        response.body().getNo_hp(),
                        response.body().getKelompok(),
                        response.body().getS_jabatan(),
                        response.body().getEselon(),
                        response.body().getJabatan(),
                        response.body().getOpd(),
                        response.body().getAlamat(),
                        response.body().getLet(),
                        response.body().getLng(),
                        response.body().getFoto(),
                        response.body().getAwal_waktu(),
                        String.valueOf(response.body().getShift())
                );

                if (insertDataPegawai){
                    tvinfoDownload.setText("unduh data koordinat lokasi...");
                    Cursor dataUser = databaseHelper.getDataEmployee(sEmployId);
                    int dataPegawai = 0;

                    while (dataUser.moveToNext()){
                        eOPD = dataUser.getString(4);
                        dataPegawai +=1;
                    }
                    if (dataPegawai == dataUser.getCount()){
                        download = 2;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataEmployee> call, @NonNull Throwable t) {
                dialogView.viewNotifKosong(DownloadDataActivity.this, "Gagal memeriksa data absensi,", "mohon periksa internet anda.");

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void testsift(){

        Call<List<WaktuSift>> jadwalSiftPegawai = holderAPI.getTestSift("https://absensi.tebingtinggikota.go.id/api/testsift?eOPD="+eOPD);
        jadwalSiftPegawai.enqueue(new Callback<List<WaktuSift>>() {
            @Override
            public void onResponse(@NonNull Call<List<WaktuSift>> call, @NonNull Response<List<WaktuSift>> response) {

                if (!response.isSuccessful()){
                    viewNotifKosong("Gagal mengunduh data, periksa koneksi internet anda dan coba kembali.", "", 3);
                }

                List<WaktuSift> waktuSifts = response.body();
                assert waktuSifts != null;
                for(WaktuSift waktuSift : waktuSifts){
                    databaseHelper.insertJamSift(String.valueOf(waktuSift.getId()), String.valueOf(waktuSift.getOpd_id()), String.valueOf(waktuSift.getTipe()), String.valueOf(waktuSift.getInisial()), String.valueOf(waktuSift.getMasuk()), String.valueOf(waktuSift.getPulang()));
                }

                download = 7;
            }

            @Override
            public void onFailure(@NonNull Call<List<WaktuSift>> call, @NonNull Throwable t) {
                pesanError();
            }
        });

    }



    public void fakeProgress(final int progress){

        progressBarHorizontal.setProgress(progress);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (download == 2){
                fakeProgress(progress+17);
                koordinat();
            }else if(download == 3){
                fakeProgress(progress+17);
                timetable(sEmployId, sToken);
            }else if(download == 5){
                fakeProgress(progress+17);
                koordinat_e();

            }else if (download == 4){
                fakeProgress(progress+17);
                kegiatan();
            }else if(download == 1) {
                fakeProgress(progress+17);
                getDataPegawai(sEmployId);
            }else if(download == 6){
                fakeProgress(progress+17);
                testsift();
            }else if (download == 7){

                Intent dashboardActivity = new Intent(DownloadDataActivity.this, DashboardVersiOne.class);
                dashboardActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dashboardActivity);
                finish();

            }
        });
        thread.start();
    }


    public void dataUser(){
        Cursor tUser = databaseHelper.getAllData22();
        int dataUser = 0;

        while (tUser.moveToNext()){
            dataUser +=1;
            sId = tUser.getString(0);
            sToken = tUser.getString(5);
            sEmployId = tUser.getString(1);

        }

        if (dataUser == tUser.getCount()){
            download =1;
            fakeProgress(20);
            tvinfoDownload.setText("unduh data pegawai...");
        }

    }

    public void pesanError(){
        Dialog errorDialogs = new Dialog(this, R.style.DialogStyle);
        errorDialogs.setContentView(R.layout.view_error);
        ImageView tvTutupDialog = errorDialogs.findViewById(R.id.tvTutupDialog);

        tvTutupDialog.setOnClickListener(v -> errorDialogs.dismiss());

        errorDialogs.show();

        Handler handler = new Handler();
        //your code here
        handler.postDelayed(errorDialogs::dismiss, 2000);

    }

    public void viewNotifKosong( String w1, String w2, int kode){

        Dialog dataKosong = new Dialog(DownloadDataActivity.this, R.style.DialogStyle);
        dataKosong.setContentView(R.layout.view_warning_kosong);
        TextView tvWarning1 = dataKosong.findViewById(R.id.tvWarning1);
        ImageView tvTutupDialog = dataKosong.findViewById(R.id.tvTutupDialog);
        tvWarning1.setText(w1+" "+w2);
        dataKosong.setCancelable(true);

        tvTutupDialog.setOnClickListener(v -> {

            if (kode == 1){
                databaseHelper.deleteDataUseAll();
                dataKosong.dismiss();
                finish();

            }else if (kode == 2){
                databaseHelper.deleteDataUseAll();
                databaseHelper.deleteDataEmployeeAll();
                dataKosong.dismiss();
                finish();


            }else if (kode == 3){

                databaseHelper.deleteDataUseAll();
                databaseHelper.deleteDataEmployeeAll();
                databaseHelper.deleteDataKoordinatOPDAll();
                dataKosong.dismiss();
                finish();

            } else if (kode == 4) {

                databaseHelper.deleteDataUseAll();
                databaseHelper.deleteDataEmployeeAll();
                databaseHelper.deleteDataKoordinatOPDAll();
                databaseHelper.deleteTimeTableAll();
                dataKosong.dismiss();
                finish();

            }

        });
        dataKosong.show();
    }
}