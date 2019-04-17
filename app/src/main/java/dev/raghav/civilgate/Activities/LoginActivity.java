package dev.raghav.civilgate.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.raghav.civilgate.SessionManage.SessionManager;
import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Const_Files.Retro_Urls;
import dev.raghav.civilgate.Parsingfiles.LoginReg.Login_Responce;
import dev.raghav.civilgate.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity  extends AppCompatActivity {
    private ProgressDialog dialog;
    TextView NewRegister;
    Button Btn_Signin;
    EditText emailfx, passwordtxt;
    AlertDialog.Builder builder;
    final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private int MY_PERMISSIONS_REQUESTS = 141;
    SessionManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        builder = new AlertDialog.Builder(this);
        manager = new SessionManager(this);
        // getSupportActionBar().hide();
        emailfx = findViewById(R.id.emailfx);
        passwordtxt = findViewById(R.id.passwordtxt);
        NewRegister = findViewById(R.id.new_reg);
        Btn_Signin = findViewById(R.id.button_signin);

        checkforpermission();

        NewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        Btn_Signin.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            if (checkvalidem()) {
               // new Do_Login(emailfx.getText().toString(), passwordtxt.getText().toString()).execute();
//               Api loginService =
//                       ServiceGenerator.createService(Api.class, "email", "password");
//               Call<Login_Responce> call = loginService.basicLogin();
//               call.enqueue(new Callback<Login_Responce >() {
//                                @Override
//                                public void onResponse(Call<Login_Responce> call, Response<Login_Responce> response) {
//                                    Toast.makeText(LoginActivity.this, "jskfsdfn", Toast.LENGTH_SHORT).show();
//
////                                    if (response.isSuccessful()) {
////                                        Toa
////                                        // user object available
////                                    } else {
////                                        // error response, no access to resource?
////                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<Login_Responce> call, Throwable t) {
//                                    // something went completely south (like no internet connection)
//                                    Log.d("Error", t.getMessage());
//                                }
//                                });

                    Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
                    Api RegApi = RetroLogin.create(Api.class);
                    Call<Login_Responce> login_responceCall = RegApi.Login_that_dk(emailfx.getText().toString() , passwordtxt.getText().toString());
                    login_responceCall.enqueue(new Callback<Login_Responce>() {
                        @Override
                        public void onResponse(Call<Login_Responce> call, Response<Login_Responce> response) {
                            Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                            if(!response.body().getResponce().equals(false))
                            {
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
                                intent.putExtra("respoce", ""+response);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Login_Responce> call, Throwable t) {

                            Log.d("cause" , ""+t.getCause());
                            Toast.makeText(LoginActivity.this, "Network problem", Toast.LENGTH_SHORT).show();

                        }
                    });

            } else {
                Toast.makeText(LoginActivity.this, "Check Credential", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private boolean checkvalidem() {
        Boolean wth = false;
        if (emailfx.getText().toString().length() == 0 && passwordtxt.getText().toString().length() == 0) {

            return wth;
        }
        if (!emailfx.getText().toString().contains("@") && !emailfx.getText().toString().contains(".") && !emailfx.getText().toString().contains("com") && passwordtxt.getText().toString().length() == 0) {
            emailfx.setError("Email is invalid");
            passwordtxt.setError("Password is too short");
            return wth;
        }
        if (passwordtxt.getText().toString().length() == 0) {
            passwordtxt.setError("Password is too short");
        }
        wth = true;
        return wth;
    }

    private void checkforpermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUESTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Toast.makeText(this, "Permission all done", Toast.LENGTH_SHORT).show();
        }// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "permission has been denied ", Toast.LENGTH_SHORT).show();

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUESTS);
                Toast.makeText(this, "permission has been denied ", Toast.LENGTH_SHORT).show();
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Toast.makeText(this, "Permission all done", Toast.LENGTH_SHORT).show();
            // Permission has already been granted
        }
//                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, permissions, ALL_PERMISSIONS);
//            // Permission is not granted
//        }      if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, permissions, ALL_PERMISSIONS);
//            // Permission is not granted
//        }
    }


//    private class Do_Login extends AsyncTask<String, Void, String> {
//        ProgressDialog dialog;
//        String email,password;
//
//        public Do_Login(String email, String passowrd) {
//            this.email = email;
//            this.password = passowrd;
//        }
//
//        protected void onPreExecute() {
//            dialog = new ProgressDialog(LoginActivity.this);
//            dialog.show();
//
//        }
//
//        protected String doInBackground(String... arg0) {
//
//            try {
//
//                URL url = new URL("http://ihisaab.in/lms/api/login");
//
//                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("email", email);
//                postDataParams.put("password", password);
//
//
//
//                Log.e("postDataParams", postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000  /*milliseconds*/);
//                conn.setConnectTimeout(15000  /*milliseconds*/);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode = conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in = new BufferedReader(new
//                            InputStreamReader(
//                            conn.getInputStream()));
//
//                    StringBuffer sb = new StringBuffer("");
//                    String line = "";
//
//                    while ((line = in.readLine()) != null) {
//
//                        StringBuffer Ss = sb.append(line);
//                        Log.e("Ss", Ss.toString());
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                } else {
//                    return new String("false : " + responseCode);
//                }
//            } catch (Exception e) {
//                return new String("Exception: " + e.getMessage());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                dialog.dismiss();
//
//                JSONObject jsonObject = null;
//                Log.e("PostRegistration", result.toString());
//                try {
//
//                    jsonObject = new JSONObject(result);
//                    String response = jsonObject.getString("responce");
//                    Log.e("Response is", response);
//                    String id = jsonObject.getJSONObject("data").getString("id");
//                    String name = jsonObject.getJSONObject("data").getString("id");
//                    String mobile  = jsonObject.getJSONObject("data").getString("id");
//                    String email = jsonObject.getJSONObject("data").getString("id");
//                    String password = jsonObject.getJSONObject("data").getString("id");
//                    String passout_year = jsonObject.getJSONObject("data").getString("id");
//                    String collage_name = jsonObject.getJSONObject("data").getString("id");
//                    String address = jsonObject.getJSONObject("data").getString("id");
////                    File passout_year = jsonObject.getJSONObject("data").getString("id");
////                    String passout_year = jsonObject.getJSONObject("data").getString("id");
//
//                    if (response.equalsIgnoreCase("True")) {
//                       Intent loginIntent = new Intent(LoginActivity.this , MainActivity.class);
//                       startActivity(loginIntent);
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//        public String getPostDataString(JSONObject params) throws Exception {
//
//            StringBuilder result = new StringBuilder();
//            boolean first = true;
//
//            Iterator<String> itr = params.keys();
//
//            while (itr.hasNext()) {
//
//                String key = itr.next();
//                Object value = params.get(key);
//
//                if (first)
//                    first = false;
//                else
//                    result.append("&");
//
//                result.append(URLEncoder.encode(key, "UTF-8"));
//                result.append("=");
//                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//            }
//            return result.toString();
//        }
//    }
}
