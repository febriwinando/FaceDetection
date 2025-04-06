package go.pemkott.appsandroidmobiletebingtinggi.database;

import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.EMPLOYEE;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_ALAMAT;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_BATASWAKTU;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_EMAIL;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_ESELON;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_FOTO;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_ID;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_ID1;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_ID2;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_JABATAN;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_KELOMPOK;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_LAT;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_LNG;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_NAMA;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_NAMA_OPD;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_NIP;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_NO_HP;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_OPD_ID;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_PEGAWAI_STATUS_SIFT;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_POSISI_ID;
import static go.pemkott.appsandroidmobiletebingtinggi.database.ModelDataPagawai.E_S_JABATAN;
import static go.pemkott.appsandroidmobiletebingtinggi.konstanta.TimeFormat.BULAN;
import static go.pemkott.appsandroidmobiletebingtinggi.konstanta.TimeFormat.SIMPLE_FORMAT_TANGGAL;
import static go.pemkott.appsandroidmobiletebingtinggi.konstanta.TimeFormat.TAHUN;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAMA_DATABASE = "absensitt.db";

    public DatabaseHelper(Context context) {
        super(context, NAMA_DATABASE, null, 8);
    }
    public static final String TABLE_USER = "data_pengguna";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "EMPLOYEID";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "MARKS";
    public static final String COL_5 = "ACTIVE";
    public static final String COL_6 = "TOKEN";
    public static final String COL_7 = "VERIFIKATOR";

//Table temporary
    public static final String TEMPORARY_PD = "temporary_pd";
    public static final String T_ID = "ID";
    public static final String T_KEGIATAN = "KEGIATAN";
    public static final String T_TGLMULAI = "TGLMULAI";
    public static final String T_TGLSAMPAI = "TGLSAMPAI";
    public static final String T_LAT = "LAT";
    public static final String T_LNG = "LNG";

//    Table Resoirce
    public static final String RESOURCE_KEGIATAN ="kegiatan_izin";
    public static final String R_ID = "ID";
    public static final String R_TIPE = "TIPE";
    public static final String R_KET = "KET";

//    Time Table
    public static final String TIMETABLE = "timetables";
    public static final String TT_ID = "ID";
    public static final String TT_EMPLOYEE_ID = "EMPLOYEEID";
    public static final String TT_TIMETABLE_ID = "TIMETABELID";
    public static final String TT_INISIAL = "INISIAL";
    public static final String TT_HARI = "HARI";
    public static final String TT_MASUK = "MASUK";
    public static final String TT_PULANG = "PULANG";


    public static final String JAMSIFT = "jamsift";
    public static String JS_ID = "ID",
            JS_OPD_ID = "OPD_ID",
            JS_TIPE = "TIPE",
            JS_INISIAL ="INISIAL",
            JS_MASUK = "MASUK",
            JS_PULANG = "PULANG";

    public static final String JADWALSIFT = "jadwalsift";
    public static String JW_ID = "ID",
            JW_EMPLOYEE_ID ="EMPLOYEEID",
            JW_SIFT = "SIFT_ID",
            JW_TANGGAL = "TANGGAL";

    public static final String PRESENCES = "presences";
    public static final String P_ID = "ID";
    public static final String P_EMPLOYEE_ID = "EMPLOYEEID";
    public static final String P_TANGGAL = "TANGGAL";
    public static final String P_JAM_MASUK = "JAM_MASUK";
    public static final String P_JAM_PULANG = "JAM_PULANG";
    public static final String P_POSISI_MASUK = "POSISI_MASUK";
    public static final String P_POSISI_PULANG = "POSISI_PULANG";
    public static final String P_STATUS_MASUK = "STATUS_MASUK";
    public static final String P_STATUS_PULANG = "STATUS_PULANG";
    public static final String P_PHOTO_TAGING_MASUK = "PHOTOTAGING_MASUK";
    public static final String P_PHOTO_TAGING_PULANG = "PHOTOTAGING_PULANG";
    public static final String P_LAT_MASUK = "LAT_MASUK";
    public static final String P_LAT_PULANG = "LAT_PULANG";
    public static final String P_LNG_MASUK = "LNG_MASUK";
    public static final String P_LNG_PULANG = "LNG_PULANG";
    public static final String P_KET_MASUK = "KET_MASUK";
    public static final String P_KET_PULANG = "KET_PULANG";
    public static final String P_LAMPIRAN_MASUK = "LAMPIRAN_MASUK";
    public static final String P_LAMPIRAN_PULANG = "LAMPIRAN_PULANG";
    public static final String P_VALID_MASUK = "VALID_MASUK";
    public static final String P_VALID_PULANG = "VALID_PULANG";
    public static final String P_LAMPIRAN_KOSONG_MASUK = "KOSONG_MASUK";
    public static final String P_LAMPIRAN_KOSONG_PULANG = "KOSONG_PULANG";

    public static final String KOORDINAT = "koordinat";
    public static final String K_ID = "ID";
    public static final String K_OPD_ID = "OPDID";
    public static final String K_ALAMAT = "ALAMAT";
    public static final String K_LAT = "LAT";
    public static final String K_LNG = "LNG";

    public static final String KOORDINAT_E = "koordinatemployee";
    public static final String KE_ID = "ID";
    public static final String KE_EMP_ID = "EMPLOYEID";
    public static final String KE_ALAMAT = "ALAMAT";
    public static final String KE_LAT = "LAT";
    public static final String KE_LNG = "LNG";

    public static final String INFO_MP = "masukpulang";
    public static final String INFO_EMPLOYEE_ID = "ID";
    public static final String INFO_TANGGAL = "TANGGAL";
    public static final String INFO_JAM = "JAM";
    public static final String INFO_STATUS = "STATUS";

    public static final String HAPUS_DATA_PENGGUNA = "infologinuser";
    public static final String HAPUS_ID = "ID";
    public static final String HAPUS_INFO = "KODE";


    @Override
    public void onCreate(SQLiteDatabase absensi) {

        try {
            absensi.execSQL("create table " + TABLE_USER + " (ID TEXT, EMPLOYEID TEXT, " +
                    "USERNAME TEXT, MARKS TEXT, ACTIVE TEXT, TOKEN TEXT, VERIFIKATOR TEXT ) ");

            absensi.execSQL("create table " + KOORDINAT_E + " (ID TEXT, EMPLOYEID TEXT, " +
                    "ALAMAT TEXT, LAT TEXT, LNG TEXT ) ");

            absensi.execSQL("create table " + KOORDINAT + " (ID TEXT, OPDID TEXT, " +
                    "ALAMAT TEXT, LAT TEXT, LNG TEXT ) ");

            absensi.execSQL("create table " + HAPUS_DATA_PENGGUNA + " (ID TEXT, KODE TEXT ) ");

            absensi.execSQL("create table " + INFO_MP + " (ID TEXT, TANGGAL TEXT, " +
                    "JAM TEXT, STATUS TEXT ) ");

            absensi.execSQL("create table " + TEMPORARY_PD + " (ID TEXT, KEGIATAN TEXT, " +
                    "TGLMULAI TEXT, TGLSAMPAI TEXT, LAT TEXT, LNG TEXT ) ");

            absensi.execSQL("create table " + RESOURCE_KEGIATAN + " (ID TEXT, TIPE TEXT, KET TEXT)");

            absensi.execSQL("create table " + TIMETABLE + " (ID TEXT, EMPLOYEEID TEXT, " +
                    "TIMETABELID TEXT, INISIAL TEXT, HARI TEXT, MASUK TEXT, PULANG TEXT ) ");

            absensi.execSQL("create table " + JAMSIFT + " (ID TEXT, OPD_ID TEXT, " +
                    "TIPE TEXT, INISIAL TEXT, MASUK TEXT, PULANG TEXT ) ");

            absensi.execSQL("create table " + JADWALSIFT + " (ID TEXT, EMPLOYEEID TEXT, " +
                    "SIFT_ID TEXT, TANGGAL TEXT ) ");

            absensi.execSQL("create table " + PRESENCES + " (ID TEXT, EMPLOYEEID TEXT, TANGGAL TEXT, " +
                    "JAM_MASUK TEXT,  JAM_PULANG TEXT, " +
                    "POSISI_MASUK TEXT, POSISI_PULANG TEXT, " +
                    "STATUS_MASUK TEXT, STATUS_PULANG TEXT, " +
                    "PHOTOTAGING_MASUK BLOB, PHOTOTAGING_PULANG BLOB," +
                    "LAT_MASUK TEXT, LAT_PULANG TEXT, " +
                    "LNG_MASUK TEXT, LNG_PULANG TEXT, " +
                    "KET_MASUK TEXT, KET_PULANG TEXT, " +
                    "LAMPIRAN_MASUK BLOB, LAMPIRAN_PULANG BLOB, " +
                    "VALID_MASUK TEXT, VALID_PULANG TEXT, " +
                    "KOSONG_MASUK TEXT, KOSONG_PULANG TEXT ) ");

            absensi.execSQL("create table " + EMPLOYEE +" (ID TEXT, IDI TEXT, IDII TEXT, " +
                    "POSISIID TEXT, OPDID TEXT, " +
                    "NIP TEXT, NAMA TEXT, " +
                    "EMAIL TEXT, NOHP TEXT, " +
                    "KELOMPOK TEXT, SJABATAN TEXT, " +
                    "ESELON TEXT, JABATAN TEXT, " +
                    "NAMA_OPD TEXT, ALAMAT TEXT, " +
                    "LAT TEXT, LNG TEXT, FOTO TEXT, " +
                    "BATAS_WAKTU TEXT, PEGAWAI_SIFT TEXT ) ");
        }catch (Exception e){
            absensi.beginTransaction();
        }



    }


    @Override
    public void onUpgrade(SQLiteDatabase absensi, int oldVersion, int newVersion) {
            if(newVersion > oldVersion){

                absensi.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
                absensi.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE);
                absensi.execSQL("DROP TABLE IF EXISTS " + TEMPORARY_PD);
                absensi.execSQL("DROP TABLE IF EXISTS " + RESOURCE_KEGIATAN);
                absensi.execSQL("DROP TABLE IF EXISTS " + TIMETABLE);
                absensi.execSQL("DROP TABLE IF EXISTS " + PRESENCES);
                absensi.execSQL("DROP TABLE IF EXISTS " + KOORDINAT);
                absensi.execSQL("DROP TABLE IF EXISTS " + INFO_MP);
                absensi.execSQL("DROP TABLE IF EXISTS " + KOORDINAT_E);
                absensi.execSQL("DROP TABLE IF EXISTS " + HAPUS_DATA_PENGGUNA);
                absensi.execSQL("DROP TABLE IF EXISTS " + JAMSIFT);
                absensi.execSQL("DROP TABLE IF EXISTS " + JADWALSIFT);

            }

            onCreate(absensi);
    }

    public boolean insertDataPresences(String id, String employee_id, String tanggal, String jam, String posisi,String status,
                                       byte[] phototaging, String lat, String lng, String ket, byte[] lampiran, String valid, String lampirankosong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(P_ID, id);
        contentValues.put(P_EMPLOYEE_ID, employee_id);
        contentValues.put(P_TANGGAL, tanggal);
        contentValues.put(P_JAM_MASUK, jam);
        contentValues.put(P_POSISI_MASUK, posisi);
        contentValues.put(P_STATUS_MASUK, status);
        contentValues.put(P_PHOTO_TAGING_MASUK, phototaging);
        contentValues.put(P_LAT_MASUK, lat);
        contentValues.put(P_LNG_MASUK, lng);
        contentValues.put(P_KET_MASUK, ket);
        contentValues.put(P_LAMPIRAN_MASUK, lampiran);
        contentValues.put(P_VALID_MASUK, valid);
        contentValues.put(P_LAMPIRAN_KOSONG_MASUK, lampirankosong);

        long result = db.insert(PRESENCES, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean updatePresensi(String id, String employee_id, String jam, String posisi, String status,
                                  byte[] phototaging, String lat, String lng, String ket, byte[] lampiran, String valid, String lampirankosong){
        SQLiteDatabase infoMP = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_JAM_PULANG, jam);
        contentValues.put(P_POSISI_PULANG, posisi);
        contentValues.put(P_STATUS_PULANG, status);
        contentValues.put(P_PHOTO_TAGING_PULANG, phototaging);
        contentValues.put(P_LAT_PULANG, lat);
        contentValues.put(P_LNG_PULANG, lng);
        contentValues.put(P_KET_PULANG, ket);
        contentValues.put(P_LAMPIRAN_PULANG, lampiran);
        contentValues.put(P_VALID_PULANG, valid);
        contentValues.put(P_LAMPIRAN_KOSONG_PULANG, lampirankosong);

        infoMP.update(PRESENCES, contentValues, P_ID+" = ? AND "+P_EMPLOYEE_ID+" = ? ", new String[]{id, employee_id});
        return true;
    }
    public static void deleteDatabase(Context mContext) {
        mContext.deleteDatabase(NAMA_DATABASE);
        return;
    }

    public Cursor getSingkronPresensi(String id, String idE){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+EMPLOYEE+" where ID = '"+id+"'", null);
        return res;
    }

    public Cursor getInfoDataTabelUser(String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+HAPUS_DATA_PENGGUNA+" where KODE = '"+kode+"'", null);
        return res;
    }

    public boolean insertDataEmployee(String id, String atasan_id1, String atasan_id2, String position_id, String opd_id,
                                      String nip, String nama, String email, String no_hp, String kelompok, String s_jabatan,
                                      String eselon, String jabatan, String nama_opd, String alamat, String lat, String lng, String foto, String bataswaktu, String sift){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(E_ID, id);
        contentValues.put(E_ID1, atasan_id1);
        contentValues.put(E_ID2, atasan_id2);
        contentValues.put(E_POSISI_ID, position_id);
        contentValues.put(E_OPD_ID, opd_id);
        contentValues.put(E_NIP, nip);
        contentValues.put(E_NAMA, nama);
        contentValues.put(E_EMAIL, email);
        contentValues.put(E_NO_HP, no_hp);
        contentValues.put(E_KELOMPOK, kelompok);
        contentValues.put(E_S_JABATAN, s_jabatan);
        contentValues.put(E_ESELON, eselon);
        contentValues.put(E_JABATAN, jabatan);
        contentValues.put(E_NAMA_OPD, nama_opd);
        contentValues.put(E_ALAMAT, alamat);
        contentValues.put(E_LAT, lat);
        contentValues.put(E_LNG, lng);
        contentValues.put(E_FOTO, foto);
        contentValues.put(E_BATASWAKTU, bataswaktu);
        contentValues.put(E_PEGAWAI_STATUS_SIFT, sift);

        long result = db.insert(EMPLOYEE, null, contentValues);
        if (result == -1 ){
            return false;
        }
        else{
            return true;
        }
    }





    public Cursor getDataEmployee(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+EMPLOYEE+" where ID = '"+id+"'", null);
        return res;
    }


    public boolean insertDataUserLogin(String id, String name, String username, String marks, String active, String token, String verifikator){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, username);
        contentValues.put(COL_4, marks);
        contentValues.put(COL_5, active);
        contentValues.put(COL_6, token);
        contentValues.put(COL_7, verifikator);
        long result = db.insert(TABLE_USER, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean insertDataKoordinat(String id, String id_opd, String alamat, String lat, String lng){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(K_ID, id);
        contentValues.put(K_OPD_ID, id_opd);
        contentValues.put(K_ALAMAT, alamat);
        contentValues.put(K_LAT, lat);
        contentValues.put(K_LNG, lng);

        long result = db.insert(KOORDINAT, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean insertDataKoordinatEmployee(String id, String ide, String alamat, String lat, String lng){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KE_ID, id);
        contentValues.put(KE_EMP_ID, ide);
        contentValues.put(KE_ALAMAT, alamat);
        contentValues.put(KE_LAT, lat);
        contentValues.put(KE_LNG, lng);

        long result = db.insert(KOORDINAT_E, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean insertDataKodeInfoTableUser(String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HAPUS_INFO, kode);
        contentValues.put(HAPUS_ID, kode);

        long result = db.insert(HAPUS_DATA_PENGGUNA, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean insertInfoMP(String id, String tanggal, String jam, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INFO_EMPLOYEE_ID, id);
        contentValues.put(INFO_TANGGAL, tanggal);
        contentValues.put(INFO_JAM, jam);
        contentValues.put(INFO_STATUS, status);

        long result = db.insert(INFO_MP, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }


    public Cursor getInfoMP(String idE, String status, String tanggal ){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+INFO_MP+" where "+INFO_EMPLOYEE_ID+" = '"+idE+"' AND "+INFO_TANGGAL+" = '"+ tanggal +"' AND "+INFO_STATUS+" = '"+ status +"'", null);
        return res;
    }


    public boolean updateDataInfoMP(String id, String tanggal, String jam, String status){
        SQLiteDatabase infoMP = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INFO_JAM, jam);
        contentValues.put(INFO_STATUS, status);

        infoMP.update(INFO_MP, contentValues, INFO_EMPLOYEE_ID+" = ? AND "+INFO_TANGGAL+" = ? ", new String[]{id, tanggal, status});
        return true;
    }



    public boolean insertDataTimeTable(String id, String employee_id, String timetable_id, String inisial, String hari, String masuk, String pulang){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TT_ID, id);
        contentValues.put(TT_EMPLOYEE_ID, employee_id);
        contentValues.put(TT_TIMETABLE_ID, timetable_id);
        contentValues.put(TT_INISIAL, inisial);
        contentValues.put(TT_HARI, hari);
        contentValues.put(TT_MASUK, masuk);
        contentValues.put(TT_PULANG, pulang);

        long result = db.insert(TIMETABLE, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean insertJamSift(String id, String opd_id, String tipe, String inisial, String masuk, String pulang){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JS_ID, id);
        contentValues.put(JS_OPD_ID, opd_id);
        contentValues.put(JS_TIPE, tipe);
        contentValues.put(JS_INISIAL, inisial);
        contentValues.put(JS_MASUK, masuk);
        contentValues.put(JS_PULANG, pulang);

        long result = db.insert(JAMSIFT, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean insertJadwalSift(String id, String employee_id, String sift_id, String tanggal){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JW_ID, id);
        contentValues.put(JW_EMPLOYEE_ID, employee_id);
        contentValues.put(JW_SIFT, sift_id);
        contentValues.put(JW_TANGGAL, tanggal);

        long result = db.insert(JADWALSIFT, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;

    }



    public boolean insertResourceKegiatan(String id, String tipe, String ket){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(R_ID, id);
        contentValues.put(R_TIPE, tipe);
        contentValues.put(R_KET, ket);

        long result = db.insert(RESOURCE_KEGIATAN, null, contentValues);
        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataTemporaryPD(String kegiatan, String tglMulai, String tglSampai, String lat, String lng){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(T_ID, "1");
        contentValues.put(T_KEGIATAN, kegiatan);
        contentValues.put(T_TGLMULAI, tglMulai);
        contentValues.put(T_TGLSAMPAI, tglSampai);
        contentValues.put(T_LAT, lat);
        contentValues.put(T_LNG, lng);
        long result = db.insert(TABLE_USER, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public boolean insertImageTemporary(String id_kegiatan, byte[] img){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_KEGIATAN", id_kegiatan);
        contentValues.put("IMAGE", img);
        long ins = MyDB.insert("temporary_image", null, contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData22(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_USER, null);
        return res;
    }

    public Cursor getDataKoordinat(String idOPD){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+KOORDINAT+" where "+K_OPD_ID+" = '"+idOPD+"'", null);
        return res;
    }

    public Cursor getDataKoordinatEmp(String idE){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+KOORDINAT_E+" where EMPLOYEID = '"+idE+"'", null);

        return res;
    }

    public Cursor getKegiatanIzin(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+RESOURCE_KEGIATAN, null);
        return res;
    }

    public Cursor getKegiatanTimeTable(String idE, String hari){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TIMETABLE+" where EMPLOYEEID = '"+idE+"' AND HARI = '"+ hari +"'" , null);
        return res;
    }

    public Cursor getJamSift(String opd_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+JAMSIFT+" where OPD_ID = '"+opd_id+"'" , null);
        return res;
    }

    public Cursor getDataSift(String opd_id, String idSift){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+JAMSIFT+" where OPD_ID = '"+opd_id+"' AND ID = '"+idSift+"' " , null);
        return res;
    }

//            absensi.execSQL("create table "+JADWALSIFT+" (ID TEXT, EMPLOYEEID TEXT, " +
//            "SIFT_ID TEXT, TANGGAL TEXT)");

    public Cursor getJadwalSift(String ide){
        String today = SIMPLE_FORMAT_TANGGAL.format(new Date());
        Date tglToday =null;
        try {

            tglToday = SIMPLE_FORMAT_TANGGAL.parse(today);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID, EMPLOYEEID, SIFT_ID, date(TANGGAL) from "+JADWALSIFT+" where EMPLOYEEID = '"+ide+"'" , null);
        return res;
    }

    public Cursor getJadwalSifts2(String ide, String bulansebelum, String bulan, String tahunsebelum, String tahun){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID, EMPLOYEEID, SIFT_ID, date(TANGGAL), strftime('%m', TANGGAL) AS BULAN, strftime('%Y', TANGGAL) AS TAHUN from "+JADWALSIFT+" where EMPLOYEEID = '"+ide+"' and (BULAN = '"+bulan+"' or BULAN = '"+bulansebelum+"') and (TAHUN = '"+tahun+"' or TAHUN = '"+tahunsebelum+"')", null);

        return res;
    }

    public Cursor getJadwalSiftsCalendar(String ide, String bulan, String tahun){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID, EMPLOYEEID, SIFT_ID, date(TANGGAL), strftime('%m', TANGGAL) AS BULAN, strftime('%Y', TANGGAL) AS TAHUN from "+JADWALSIFT+" where EMPLOYEEID = '"+ide+"' and BULAN = '"+bulan+"' and TAHUN = '"+tahun+"'", null);
        return res;
    }

    public Cursor getInfoJadwalSift(String ide){
        String bulan = BULAN.format(new Date());
        String tahun = TAHUN.format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID, EMPLOYEEID, SIFT_ID, date(TANGGAL), strftime('%m', TANGGAL) AS BULAN, strftime('%Y', TANGGAL) AS TAHUN from "+JADWALSIFT+" where EMPLOYEEID = '"+ide+"' and BULAN = '"+bulan+"' and TAHUN = '"+tahun+"'" , null);
        return res;
    }

    public Cursor getInfoJadwalSiftToday(String ide, String tglCheck){
        String hariini = SIMPLE_FORMAT_TANGGAL.format(new Date());


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID, EMPLOYEEID, SIFT_ID, date(TANGGAL) from "+JADWALSIFT+" where EMPLOYEEID = '"+ide+"' and TANGGAL = '"+tglCheck+"'" , null);
        return res;
    }

    public Cursor getKegiatanTimeTableCheck(String idE){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TIMETABLE+" where EMPLOYEEID = '"+idE+"'", null);
        return res;
    }

    public boolean updateTimeTabale(String tt_timetable_id, String inisial, String masuk, String pulang, String employee_id, String hari){
        SQLiteDatabase dbUmkm = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TT_TIMETABLE_ID, tt_timetable_id);
        contentValues.put(TT_INISIAL, inisial);
        contentValues.put(TT_MASUK, masuk);
        contentValues.put(TT_PULANG, pulang);

        dbUmkm.update(TIMETABLE, contentValues, TT_EMPLOYEE_ID+" = ? AND "+TT_HARI+" = ? ", new String[]{employee_id, hari});
        return true;
    }


    public Cursor getListPresensi(String idE){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PRESENCES+" where " +P_EMPLOYEE_ID+" = '"+idE+"' order by "+P_TANGGAL+" desc, "+P_JAM_MASUK+" desc", null);
//        Cursor res = db.rawQuery("select * from "+PRESENCES+" order by "+P_TANGGAL+" desc, "+P_JAM+" desc", null);
        return res;

    }

    public Cursor getListPresensiPosisi(String tanggalPresensi, String idE){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PRESENCES+" where TANGGAL = '"+tanggalPresensi+"' AND "+P_EMPLOYEE_ID+" = '"+idE+"'", null);
        return res;

    }

    public Cursor getListPresensiPosisi2(String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PRESENCES+" where "+P_EMPLOYEE_ID+" = '15' AND TANGGAL = '"+tanggal+"'", null);

        return res;
    }


    public Cursor getAllDataTemporaryPD(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TEMPORARY_PD, null);
        return res;
    }

    public Cursor getToken(){
        SQLiteDatabase db = DatabaseHelper.this.getWritableDatabase();
        Cursor res = db.rawQuery("select "+COL_6+" from "+TABLE_USER, null);
        return res;
    }

    public boolean updateData(String id, String name, String username, String marks){
        SQLiteDatabase dbUmkm = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, username);
        contentValues.put(COL_4, marks);

        dbUmkm.update(TABLE_USER, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public boolean updateDataTemporaryPDTime(String id, String tglmulai, String tglsampai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T_TGLMULAI, tglmulai);
        contentValues.put(T_TGLSAMPAI, tglsampai);

        db.update(TEMPORARY_PD, contentValues, "ID = ?", new String[]{id});
        return true;

    }

    public boolean updateDataEmployee(String id, String imgProfil){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(E_FOTO, imgProfil);

        db.update(EMPLOYEE, contentValues, "ID = ?", new String[]{id});
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( TABLE_USER, "EMPLOYEID = ?", new String[] {id} );
    }

    public Integer deleteDataUseAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( TABLE_USER, null, null );
    }

    public Integer deleteTimeTable(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( TIMETABLE, null, null);
    }

    public Integer deleteJamSift(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( JAMSIFT, null, null);
    }
    public Integer deleteJadwalSift(String ide, String bulan, String tahun){

//        String bulan = BULAN.format(new Date());
//        String tahun = TAHUN.format(new Date());
//        Cursor res = db.rawQuery("select ID, EMPLOYEEID, SIFT_ID, date(TANGGAL), strftime('%m', TANGGAL) AS BULAN, strftime('%Y', TANGGAL) AS TAHUN from "+JADWALSIFT+" where EMPLOYEEID = '"+ide+"' and BULAN = '"+bulan+"' and TAHUN = '"+tahun+"'" , null);
//        return res; JW_TANGGAL
//        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID, date(TANGGAL), strftime('%m', TANGGAL) AS BULAN, strftime('%Y', TANGGAL) AS TAHUN from "+JADWALSIFT+" where EMPLOYEEID = '"+ide+"' and BULAN = '"+bulan+"' and TAHUN = '"+tahun+"'", null);
        while (res.moveToNext()){
            db.delete( JADWALSIFT, JW_TANGGAL+" = ?", new String[] {res.getString(1)});
        }
        return res.getCount();
    }

    public Integer deleteJadwalSift2(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( JADWALSIFT, null, null);
    }

    public Integer deleteTimeTableAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( TIMETABLE, null, null);
    }

    public Integer deleteAllDataUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( TABLE_USER, null, null );
    }

    public Integer deleteDataTableInfoClearUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( HAPUS_DATA_PENGGUNA, HAPUS_INFO+" = ?", new String[] {id} );
    }

    public Integer deleteDataEmployee(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( EMPLOYEE, E_ID+" = ?", new String[] {id} );
    }

    public Integer deleteDataEmployeeAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( EMPLOYEE, null, null );
    }

    public Integer deleteDataTemporaryPD(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( TEMPORARY_PD, "ID = ?", new String[] {id} );

    }

    public Integer deleteDataKoordinatEmployee(String idE){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( KOORDINAT_E, "EMPLOYEID = ?", new String[] {idE} );

    }

    public Integer deleteDataKoordinatEmployeeAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( KOORDINAT_E, null, null);

    }

    public Integer deleteDataKoordinatOPD(String idOPD){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( KOORDINAT, "OPDID = ?", new String[] {idOPD} );
    }

    public Integer deleteDataKoordinatOPDAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( KOORDINAT, null, null );
    }

    public Integer deleteKegiatanIzin(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( RESOURCE_KEGIATAN, null, null );
    }

}
