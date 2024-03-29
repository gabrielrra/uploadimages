package com.example.uploadimages;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * It is necessary to have all the following permissions
 * and a FileProvider in Manifest.
 * The file provider must be an XML named "file_paths" at "res>xml"
 *
 * Permissões necessárias:
 *
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.CAMERA" />
 * <uses-permission android:name="android.permission.IMAGE_CAPTURE" />
 *
 * Author: gabrielrra <gabrielrra.dev@gmail.com>
 **/

public class MainActivity extends AppCompatActivity {
    //Diferentes códigos para o onActivityResult
    private static final int IMG_1 = 11;
    private static final int IMG_2 = 12;
    private static final int IMG_3 = 13;
    private static final int IMG_4 = 14;
    private static final int IMG_5 = 15;
    private static final int IMG_6 = 16;

    //Transfom the buttons' id into constants, to ease the code
    private static final int ID_1 = R.id.buttonImage1;
    private static final int ID_2 = R.id.buttonImage2;
    private static final int ID_3 = R.id.buttonImage3;
    private static final int ID_4 = R.id.buttonImage4;
    private static final int ID_5 = R.id.buttonImage5;
    private static final int ID_6 = R.id.buttonImage6;

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;

    private String mCurrentPhotoPath;

    private File file1, file2, file3, file4, file5, file6; //Arquivo das imagens

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);

    }

    public void getPermissions(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
         || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
         || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
         || ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, 1);

        } else {
            openCameraIntent(view.getId());
        }
    }

    public void openCameraIntent(int id) {

        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);

        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d("TAG", ex.getMessage());
            }
            if (photoFile != null) {

                Log.d("PHOTO FILE PATH - ", photoFile.getAbsolutePath());

                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.uploadimages.provider", photoFile);

                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                switch (id){
                    case ID_1:
                        file1 = photoFile;
                        startActivityForResult(pictureIntent, IMG_1);
                        break;
                    case ID_2:
                        file2 = photoFile;
                        startActivityForResult(pictureIntent, IMG_2);
                        break;
                    case ID_3:
                        file3 = photoFile;
                        startActivityForResult(pictureIntent, IMG_3);
                        break;
                    case ID_4:
                        file4 = photoFile;
                        startActivityForResult(pictureIntent, IMG_4);
                        break;
                    case ID_5:
                        file5 = photoFile;
                        startActivityForResult(pictureIntent, IMG_5);
                        break;
                    case ID_6:
                        file6 = photoFile;
                        startActivityForResult(pictureIntent, IMG_6);
                        break;

                }

            }
        }
    }

    String imageFilePath;
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getFilesDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* sufix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (resultCode == RESULT_OK) {
            Bitmap image, compressed;
            switch (requestCode) {
                case IMG_1:
                    image = BitmapFactory.decodeFile(file1.getAbsolutePath());
                    compressed = Bitmap.createScaledBitmap(image, 100, 100, true);
                    imageView1.setImageBitmap(compressed);
                    break;
                case IMG_2:
                    image = BitmapFactory.decodeFile(file2.getAbsolutePath());
                    compressed = Bitmap.createScaledBitmap(image, 100, 100, true);
                    imageView2.setImageBitmap(compressed);
                    break;
                case IMG_3:
                    image = BitmapFactory.decodeFile(file3.getAbsolutePath());
                    compressed = Bitmap.createScaledBitmap(image, 100, 100, true);
                    imageView3.setImageBitmap(compressed);
                    break;
                case IMG_4:
                    image = BitmapFactory.decodeFile(file4.getAbsolutePath());
                    compressed = Bitmap.createScaledBitmap(image, 100, 100, true);
                    imageView4.setImageBitmap(compressed);
                    break;
                case IMG_5:
                    image = BitmapFactory.decodeFile(file5.getAbsolutePath());
                    compressed = Bitmap.createScaledBitmap(image, 100, 100, true);
                    imageView5.setImageBitmap(compressed);
                    break;
                case IMG_6:
                    image = BitmapFactory.decodeFile(file6.getAbsolutePath());
                    compressed = Bitmap.createScaledBitmap(image, 100, 100, true);
                    imageView6.setImageBitmap(compressed);
                    break;

            }

        }

    }

    //TODO método de fazer upload - ALTERAR

    public void upload(String url, File file) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        RequestBody formBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("fileToUpload", file.getName(),
//                        RequestBody.create(MediaType.parse("image/jpeg"), file))
//                .build();
//        Request request = new Request.Builder()
//                        .url(url).post(formBody).build();
//
//        client.newCall(request).enqueue(new Callback() {
//
//            public void onFailure(Call call, IOException e) {
//
//                Log.d("Tag","error"+e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    Log.d("SUCESSO", "Enviado com sucesso.");
//                    Toast.makeText(MainActivity.this, "Deu certo",
//                            Toast.LENGTH_SHORT).show();
//                    // Upload successful
//                } else
//                    Log.d("ERROUPLOAD", "Erro" + response.message());
//
//            }
//        });
    }

    public void uploadAll(View v) {

        //Handle sending multiple files to any server

//        if(file1 != null && file2 != null && file3 != null
//                && file4 != null && file5 != null && file6 != null){
//
//            try {
//                upload("https://www.ufsj.xyz/upload.php", file1);
//            } catch (Exception e) {
//                Log.d("UPLOAD1", e.getMessage());
//            }
//            try {
//                upload("https://www.ufsj.xyz/upload.php", file2);
//            } catch (Exception e) {
//                Log.d("UPLOAD2", e.getMessage());
//            }
//            try {
//                upload("https://www.ufsj.xyz/upload.php", file3);
//            } catch (Exception e) {
//                Log.d("UPLOAD3", e.getMessage());
//            }
//            try {
//                upload("https://www.ufsj.xyz/upload.php", file4);
//            } catch (Exception e) {
//                Log.d("UPLOAD4", e.getMessage());
//            }
//            try {
//                upload("https://www.ufsj.xyz/upload.php", file5);
//            } catch (Exception e) {
//                Log.d("UPLOAD5", e.getMessage());
//            }
//            try {
//                upload("https://www.ufsj.xyz/upload.php", file6);
//            } catch (Exception e) {
//                Log.d("UPLOAD6", e.getMessage());
//            }
//        }else{
//            Toast.makeText(this, "Todas as imagens são necessárias",
//                    Toast.LENGTH_LONG).show();
//        }

    }

}
