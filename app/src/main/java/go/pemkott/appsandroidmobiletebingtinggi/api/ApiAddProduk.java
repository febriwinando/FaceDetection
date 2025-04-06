package go.pemkott.appsandroidmobiletebingtinggi.api;

import go.pemkott.appsandroidmobiletebingtinggi.model.CheckAbsensi;
import go.pemkott.appsandroidmobiletebingtinggi.model.DataEmployee;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiAddProduk {

    @FormUrlEncoded
    @POST("baseee")
    Call<ResponsePOJO> uploadImage(
            @Field("fileup") String encodedImage
    );

    @FormUrlEncoded
    @POST("kehadrianpegawainew")
    Call<ResponsePOJO> uploadAbsenKehadiran(
            @Field("fototaging") String encodedImage,
            @Field("absensi") String absensi,
            @Field("eselon") String eselon,
            @Field("employee_id") String id,
            @Field("timetable_id") String time,
            @Field("tanggal") String tanggal,
            @Field("jam_masuk") String jammasuk,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("terlambat") int terlambat,
            @Field("opd") String opd,
            @Field("jam_kantor") String jamkantor,
            @Field("valid_masuk") String validasi,
            @Field("fakegps") String fakegps,
            @Field("batas_waktu") String batas_waktu,
            @Field("berakhlak") String berakhlak
    );
    @FormUrlEncoded
    @POST("kehadrianpegawaisift")
    Call<ResponsePOJO> uploadAbsenKehadiranSift(
            @Field("fototaging") String encodedImage,
            @Field("absensi") String absensi,
            @Field("eselon") String eselon,
            @Field("employee_id") String id,
            @Field("timetable_id") String time,
            @Field("tanggal") String tanggal,
            @Field("jam_masuk") String jammasuk,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("opd") String opd,
            @Field("jam_kantor") String jamkantor,
            @Field("valid_masuk") String validasi,
            @Field("fakegps") String fakegps,
            @Field("batas_waktu") String batas_waktu,
            @Field("masuksift") String masuksift,
            @Field("pulangsift") String pulangsift,
            @Field("inisialsift") String inisialsift,
            @Field("tipesift") String tipesift,
            @Field("idsift") String idsift,
            @Field("keterangan") String keterangan,
            @Field("terlambat") int terlambat
    );

    @FormUrlEncoded
    @POST("infoabsensitest")
    Call<CheckAbsensi> checkabsensi(
            @Field("tanggal") String absen,
            @Field("id_employee") String ide
    );

    @FormUrlEncoded
    @POST("infoabsensitestsiftmalam")
    Call<CheckAbsensi> checkabsensisiftmalam(
            @Field("tanggal") String absen,
            @Field("id_employee") String ide

    );

    @FormUrlEncoded
    @POST("dataEmployee")
    Call<DataEmployee> dataEmployee(
            @Field("id") String id

    );

    @FormUrlEncoded
    @POST("dinasluarkantornew")
    Call<ResponsePOJO> uploadAbsenKehadiranDinasLuar(
            @Field("fototaging") String encodedImage,
            @Field("absensi") String absensi,
            @Field("eselon") String eselon,
            @Field("employee_id") String id,
            @Field("timetable_id") String time,
            @Field("tanggal") String tanggal,
            @Field("jam_masuk") String jammasuk,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("terlambat") int terlambat,
            @Field("opd") String opd,
            @Field("jam_kantor") String jamkantor,
            @Field("valid_masuk") String validasi,
            @Field("lampiran") String lampiran,
            @Field("ekslampiran") String ekslampiran,
            @Field("fakegps") String fakegps,
            @Field("batas_waktu") String bataswaktu
    );


    @FormUrlEncoded
    @POST("dinasluarkantornewsift")
    Call<ResponsePOJO> uploadAbsenKpSift(
            @Field("fototaging") String encodedImage,
            @Field("absensi") String absensi,
            @Field("eselon") String eselon,
            @Field("employee_id") String id,
            @Field("timetable_id") String time,
            @Field("tanggal") String tanggal,
            @Field("jam_masuk") String jammasuk,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("terlambat") int terlambat,
            @Field("opd") String opd,
            @Field("jam_kantor") String jamkantor,
            @Field("valid_masuk") String validasi,
            @Field("lampiran") String lampiran,
            @Field("ekslampiran") String ekslampiran,
            @Field("fakegps") String fakegps,
            @Field("batas_waktu") String bataswaktu
    );

    @FormUrlEncoded
    @POST("izinsakitinsert")
    Call<ResponsePOJO> uploadizinsakit(
            @Field("fototaging") String encodedImage,
            @Field("absensi") String absensi,
            @Field("eselon") String eselon,
            @Field("employee_id") String id,
            @Field("timetable_id") String time,
            @Field("tanggal") String tanggal,
            @Field("jam_masuk") String jammasuk,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("terlambat") int terlambat,
            @Field("opd") String opd,
            @Field("jam_kantor") String jamkantor,
            @Field("valid_masuk") String validasi,
            @Field("lampiran") String lampiran,
            @Field("ekslampiran") String ekslampiran,
            @Field("fakegps") String fakegps
    );

    @FormUrlEncoded
    @POST("izinsakitsiftinsert")
    Call<ResponsePOJO> uploadizinsakitsift(
            @Field("fototaging") String encodedImage,
            @Field("absensi") String absensi,
            @Field("eselon") String eselon,
            @Field("employee_id") String id,
            @Field("timetable_id") String time,
            @Field("tanggal") String tanggal,
            @Field("jam_masuk") String jammasuk,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("terlambat") int terlambat,
            @Field("opd") String opd,
            @Field("jam_kantor") String jamkantor,
            @Field("valid_masuk") String validasi,
            @Field("lampiran") String lampiran,
            @Field("ekslampiran") String ekslampiran,
            @Field("fakegps") String fakegps,
            @Field("idsift") String idsift,
            @Field("tipesift") String tipesift,
            @Field("inisialsift") String inisialsift,
            @Field("masuksift") String masuksift,
            @Field("pulangsift") String pulangsift
    );


    @FormUrlEncoded
    @POST("absensiperjalanandinasnew")
    Call<ResponsePOJO> uploadAbsenPerjalananDinas(
            @Field("fototaging") String encodedImage,
            @Field("eselon") String eselon,
            @Field("employee_id") String id,
            @Field("timetable_id") String time,
            @Field("tanggal") String tanggal,
            @Field("jam_masuk") String jammasuk,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("terlambat") int terlambat,
            @Field("opd") String opd,
            @Field("valid_masuk") String validasi,
            @Field("lampiran") String lampiran,
            @Field("ekslampiran") String ekslampiran,
            @Field("mulai") String dari,
            @Field("sampai") String sampai,
            @Field("fakegps") String fakegps
    );

    @FormUrlEncoded
    @POST("absensiizincuti")
    Call<ResponsePOJO> uploadAbsenIzinCuti(

            @Field("fototaging") String encodedImage,
            @Field("employee_id") String id,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("valid_masuk") String validasi,
            @Field("lampiran") String lampiran,
            @Field("ekslampiran") String ekslampiran,
            @Field("mulai") String dari,
            @Field("sampai") String sampai,
            @Field("fakegps") String fakegps

    );

    @FormUrlEncoded
    @POST("absensiizincutisift")
    Call<ResponsePOJO> uploadAbsenIzinCutiSift(

            @Field("fototaging") String encodedImage,
            @Field("employee_id") String id,
            @Field("posisi_masuk") String posisimasuk,
            @Field("status_masuk") String statusmasuk,
            @Field("lat_masuk") String latmasuk,
            @Field("lng_masuk") String lngmasuk,
            @Field("ket_masuk") String ketmasuk,
            @Field("valid_masuk") String validasi,
            @Field("lampiran") String lampiran,
            @Field("ekslampiran") String ekslampiran,
            @Field("mulai") String dari,
            @Field("sampai") String sampai,
            @Field("fakegps") String fakegps

    );

}
