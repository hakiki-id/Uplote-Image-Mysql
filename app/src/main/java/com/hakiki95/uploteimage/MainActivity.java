package com.hakiki95.uploteimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn_gallery;
    Button btn_photo ;
    Button uplote;

    TextView tv_nama, tv_keterangan;

    ImageView img ;

    CameraPhoto mPhoto;
    GalleryPhoto mGalery;
    String encode_image = null;

    private final int TAG_PHOTO = 1111;
    private  final int TAG_GALLERY = 2222;

    String selected_photo ;

    RequestQueue mRequestQueue;

    String URL ="http://10.0.3.2/belajar/uplote/uplote_kegiatan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_gallery = (Button) findViewById(R.id.btn_gallery);
        btn_photo = (Button) findViewById(R.id.btn_camera);
        uplote = (Button) findViewById(R.id.uplote);
        img = (ImageView) findViewById(R.id.imageviewtemp);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mGalery = new GalleryPhoto(getApplicationContext());
        mPhoto = new CameraPhoto(getApplicationContext());


        tv_nama = (TextView) findViewById(R.id.edt_nama);
        tv_keterangan= (TextView) findViewById(R.id.edt_keterangan);

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(mPhoto.takePhotoIntent(),TAG_PHOTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(mGalery.openGalleryIntent(),TAG_GALLERY);
            }
        });

        uplote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = tv_nama.getText().toString();
                final String keterangan = tv_keterangan.getText().toString();

                try {
                    Bitmap bitmap = ImageLoader.init().from(selected_photo).requestSize(1024,1024).getBitmap();
                    encode_image = ImageBase64.encode(bitmap);

                    Log.d("ENCODER", encode_image);



                StringRequest request = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().equals("berhasil")) {
                                    Snackbar.make(findViewById(android.R.id.content), "Berhasil Uplote Data", Snackbar.LENGTH_SHORT).show();
                                }else{
                                    Snackbar.make(findViewById(android.R.id.content), "Gagal Uplote Data", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("judul",nama);
                        map.put("keterangan",keterangan);
                        map.put("image",encode_image);
                        return map;
                    }
                };

                mRequestQueue.add(request);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode == TAG_PHOTO){
                String photo_path = mPhoto.getPhotoPath();
                selected_photo  =photo_path;
                try {
                    Bitmap bitmap ;
                    bitmap = ImageLoader.init().from(photo_path).requestSize(512,512).getBitmap();
                    img.setImageBitmap(bitmap);
                    mPhoto.addToGallery();
                    Snackbar.make(findViewById(android.R.id.content),"Succes Loading Image",Snackbar.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Snackbar.make(findViewById(android.R.id.content),"Error Loading Image",Snackbar.LENGTH_SHORT).show();
                }
            }else if (requestCode == TAG_GALLERY){
                Uri uri_path = data.getData();
                mGalery.setPhotoUri(uri_path);
                String path = mGalery.getPath();

                selected_photo = path;
                try {
                    Bitmap bitmap;
                    bitmap = ImageLoader.init().from(path).requestSize(512,512).getBitmap();
                    img.setImageBitmap(bitmap);

                    Snackbar.make(findViewById(android.R.id.content),"Succes Loader Image",Snackbar.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Snackbar.make(findViewById(android.R.id.content),"Something Wrong",Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }
}
