package dev.raghav.civilgate.Activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Parsingfiles.LoginReg.RegisPars_responce;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.Api.Api;

import dev.raghav.civilgate.Api.RetrofitClientApi;
import dev.raghav.civilgate.Utils.Utilities;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RegisterActivity extends AppCompatActivity {
    private static final int PERMISSION_GATE_READ = 141;
    private static final int READ_REQUEST_CODE = 101;
    CollapsingToolbarLayout toolbar_post;
    // private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = ;
    EditText email , password , passing_year , ful_name , mobile,address,collage_name;
     ImageView  gate_photo , gate_sign;
     File gate_photo_file , gate_sign_file;
     Button reg_btn;
     View gv;
     Api  apiInterface;
     int a = 0;
    private static final int MY_PERMISSIONS_REQUESTS = 101;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        gv = RegisterActivity.this.getApplicationContext();
        init();
//         a=10;
//        Toast.makeText(this, "a value at oncreate is"+a, Toast.LENGTH_SHORT).show();
        getSupportActionBar().hide();
        apiInterface = RetrofitClientApi.getClient().create(Api.class);
        checkforpermission();

        gate_photo.setOnClickListener(v -> {
            if(checkforpermission())
            {
                Toast.makeText(RegisterActivity.this, "all set", Toast.LENGTH_SHORT).show();
                a=1;
                opengoddamngallery();

            }else {
             requestitback();
            }
        });
        gate_sign.setOnClickListener(v -> {
            if(checkforpermission())
            {
                Toast.makeText(RegisterActivity.this, "all set", Toast.LENGTH_SHORT).show();
                a=2;
                opengoddamngallery();

            }else {
                requestitback();
            }
        });
        //calling for registration
        reg_btn.setOnClickListener(v -> {
            if(seeifallvaliD())
            {
                if(gate_photo_file !=null && gate_sign_file !=null)
                {
                    registerthestupiduser(gate_photo_file , gate_sign_file , gate_photo_file.getAbsolutePath() , gate_sign_file.getAbsolutePath());
                    Toast.makeText(RegisterActivity.this, "ok now you can upload", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(RegisterActivity.this, "something is wrong", Toast.LENGTH_SHORT).show();
                }
            }

        });
        //

    }

    private void registerthestupiduser(File gate_photo_file, File gate_sign_file, String pabsolutePath, String spath) {
//        RequestBody gate_fulname  = RequestBody.create(MediaType.get("text/plain") , ful_name.getText().toString());
//        RequestBody gate_email  = RequestBody.create(MediaType.get("text/plain") , email.getText().toString());
//        RequestBody gate_password  = RequestBody.create(MediaType.get("text/plain") , password.getText().toString());
//        RequestBody gate_mobile  = RequestBody.create(MediaType.get("text/plain") , mobile.getText().toString());
//        RequestBody gate_passout  = RequestBody.create(MediaType.get("text/plain") , passing_year.getText().toString());
//        RequestBody gate_collage  = RequestBody.create(MediaType.get("text/plain") , collage_name.getText().toString());
//        RequestBody gate_address  = RequestBody.create(MediaType.get("text/plain") , address.getText().toString());
//
//        RequestBody gateRequestBodyphoto = RequestBody.create(MediaType.parse("image/*"), gate_photo_file );
//        RequestBody gateRequestBodysign = RequestBody.create(MediaType.parse("image/*"), gate_sign_file);
////        RequestBody gateRequestBodyphoto = RequestBody.create(MediaType.parse("image/*"), gate_photo_file );
////        RequestBody gateRequestBodysign = RequestBody.create(MediaType.parse("image/*"), gate_sign_file);
//        MultipartBody.Part gateToUploadphoto = MultipartBody.Part.createFormData("file", gate_photo_file.getName(), gateRequestBodyphoto);
//        MultipartBody.Part gateToUploadsign = MultipartBody.Part.createFormData("file1", gate_sign_file.getName(), gateRequestBodysign);
//        // create RequestBody instance from file
////        RequestBody gate_photo_file2 =
////                RequestBody.create(
////                        MediaType.parse(getContentResolver().getType(gate_photo_file)),
////                        file
////                );
//        // create RequestBody instance from file
////        RequestBody gate_sign_file2 =
////                RequestBody.create(Med);
//        Retrofit REgretrofit = new Retrofit.Builder()
//                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api RegApi = REgretrofit.create(Api.class);
//        if(gate_photo_file !=null && gate_sign_file !=null)
//        {
//            if(gate_photo_file.exists() && gate_sign_file.exists())
//            {
//                Toast.makeText(this, "Something is there", Toast.LENGTH_SHORT).show();
//                Call<RegisPars_responce> regisPars_responceCall = RegApi.Register_to_app_with_profile( ful_name.getText().toString(), mobile.getText().toString() ,email.getText().toString() ,password.getText().toString() , passing_year.getText().toString() , collage_name.getText().toString() , address.getText().toString() , gate_photo_file , gate_sign_file);
//                regisPars_responceCall.enqueue(new Callback<RegisPars_responce>() {
//                    @Override
//                    public void onResponse(Call<RegisPars_responce> call, Response<RegisPars_responce> response) {
//                        Toast.makeText(RegisterActivity.this, ""+response.body().getResponce(), Toast.LENGTH_SHORT).show();
//                        Log.e("responce is" , ""+response.body().getResponce());
//                    }
//
//                    @Override
//                    public void onFailure(Call<RegisPars_responce> call, Throwable t) {
//
//                    }
//                });
//            }else{
//                Toast.makeText(this, "None found", Toast.LENGTH_SHORT).show();
//            }
//
//        }
        new Final_Image_upload(gate_photo_file , gate_sign_file).execute();

    }

    private void init() {
//        this.a = a+10;
//        Log.d("init" , "works a"+a);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passing_year = findViewById(R.id.passout_year);
        ful_name = findViewById(R.id.ful_nam);
        mobile = findViewById(R.id.mobile);
        gate_sign = findViewById(R.id.gate_sign);
        gate_photo = findViewById(R.id.gate_photo);
        reg_btn = findViewById(R.id.reg_btn);
        toolbar_post = findViewById(R.id.toolbar_post);
        address = findViewById(R.id.address);
        collage_name = findViewById(R.id.collage_name);

    }

    private Boolean seeifallvaliD() {
        Boolean valBoolean = true;
        if(email.getText().toString().length() ==0)
        {
            email.setError("Can not be empty");
            valBoolean = false;
        }
        if( password.getText().toString().length() <=6 )
        {
            password.setError("Password is weak");
           valBoolean = false;
        } if(ful_name.getText().toString().length() <=2)
        {
            ful_name.setError("Name is too short");
            ful_name.requestFocus();
//            toolbar_post.requestFocus();
            valBoolean = false;
        }
        if(mobile.getText().toString().length() <=9)
        {
            mobile.setError("Number is inValid");
            valBoolean = false;
        }
//        valBoolean = false;

        return valBoolean;

    }

    private void opengoddamngallery() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    private void requestitback() {

        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_GATE_READ);
//        if (ContextCompat.(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission is not granted
//            checkforpermission();
//        }if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission is not granted
//            checkforpermission();
//        }
    }

    private boolean checkforpermission() {

        int result = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void getitdone() {
        Call<RegisPars_responce> regisPars_responceCall = apiInterface.Register_to_app("pro1" ,"82240984561" , "pjk@gmail1.com","1234561","20001","nojkt1","near everything1",null,null );
        regisPars_responceCall.enqueue(new Callback<RegisPars_responce>() {
            @Override
            public void onResponse(Call<RegisPars_responce> call, Response<RegisPars_responce> response) {
                Toast.makeText(RegisterActivity.this, ""+response.body().getResponce(), Toast.LENGTH_SHORT).show();
                Log.e("hey yory", "responce is"+response.body().getResponce());
            }

            @Override
            public void onFailure(Call<RegisPars_responce> call, Throwable t) {

            }
        });

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_GATE_READ:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted){
                        Snackbar.make(getWindow().getDecorView().getRootView(), "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();

                    }

                    else {

                        Snackbar.make(getWindow().getDecorView().getRootView(), "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                                showMessageOKCancel("For Validation, We need to access photos (from sd card) of your phone please allow",
                                        (dialog, which) -> {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                        MY_PERMISSIONS_REQUESTS);
                                                Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;


        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("We go somethihbygf", "Uri: " + uri.toString());
                Toast.makeText(this, "is "+uri, Toast.LENGTH_SHORT).show();
                showImage(uri,resultData);
            }
        }
    }

    private File showImage(Uri uri, Intent resultData) {
        ParcelFileDescriptor parcelFileDescriptor =
                null;
        try {
            parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
//        OutputStream = new
        if (a == 1) {
            File filesDir = getApplicationContext().getFilesDir();
           gate_photo_file = new File(filesDir, "photo" + ".jpg");

            OutputStream os;
            try {
                os = new FileOutputStream(gate_photo_file);
                Log.e("file is", ""+gate_photo_file.getName());
                image.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }

//            }
            gate_photo.setImageBitmap(image);

                return null;
            } else
                {

            File filesDir = getApplicationContext().getFilesDir();
            gate_sign_file = new File(filesDir, "sign" + ".jpg");

            OutputStream os;
            try {
                os = new FileOutputStream(gate_sign_file);
                Log.e("file is", ""+gate_sign_file.getName());
                image.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }
            gate_sign.setImageBitmap(image);

            }

            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
                Toast.makeText(this, "cant" + e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "" + gate_sign_file.getName(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }

    private void showMessageOKCancel(String s, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(RegisterActivity.this)
                .setMessage(s)
                .setPositiveButton("OK", onClickListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private class Final_Image_upload extends AsyncTask<Void , Void , String> {
        File gate_photo , gate_sign;
        String result = "";

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(RegisterActivity.this);
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        public  Final_Image_upload(File gate_photo_file, File gate_sign_file) {
            this.gate_photo = gate_photo_file;
            this.gate_sign = gate_sign_file;
        }

        @Override
        protected String doInBackground(Void... Void) {
            try {


                MultipartEntity entity = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);

                entity.addPart("name", new StringBody(ful_name.getText().toString()));
                entity.addPart("email", new StringBody("" + email.getText().toString()));
                entity.addPart("mobile", new StringBody("" + mobile.getText().toString()));
                entity.addPart("password", new StringBody("" + password.getText().toString()));
                entity.addPart("passout_year", new StringBody("" + passing_year.getText().toString()));
                entity.addPart("collage_name", new StringBody("" + collage_name.getText().toString()));
                entity.addPart("address", new StringBody("" + address.getText().toString()));
                entity.addPart("profile_image", new FileBody(gate_photo));
                entity.addPart("sign_image", new FileBody(gate_sign));
//                    result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
//                 //   result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
                result = Utilities.postEntityAndFindJson("http://ihisaab.in/lms/api/Ragistration", entity);
            } catch (Exception e) {
                       e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {

            String result1 = result;
            if (result1 != null) {

                dialog.dismiss();
                Log.e("result1", result1);

                Toast.makeText(RegisterActivity.this, " Successfully Registered", Toast.LENGTH_LONG).show();

                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);

            } else {
                dialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Some Problem", Toast.LENGTH_LONG).show();
            }

        }
        }


//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUESTS: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "Permission is"+grantResults[0], Toast.LENGTH_SHORT).show();
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                }
//                else
//                    {
//                    Toast.makeText(this, "Permission is"+grantResults[0], Toast.LENGTH_SHORT).show();
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request.
//        }
//    }
}
