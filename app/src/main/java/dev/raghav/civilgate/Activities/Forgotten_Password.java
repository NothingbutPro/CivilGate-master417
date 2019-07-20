package dev.raghav.civilgate.Activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Const_Files.Forgotten_Passowrd;
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Package_Const;
import dev.raghav.civilgate.Dapter.Pakages_Adapter;
import dev.raghav.civilgate.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Forgotten_Password extends AppCompatActivity {
  EditText Forgotemail;
  TextView Retrivemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten__password);
        Forgotemail =findViewById(R.id.txtemail);
        Retrivemail = findViewById(R.id.retrivmail);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Retrivemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("sdfsd is " , ""+Forgotemail.getText().toString().contains("."));
                Log.w("hgvhvhg is " , ""+Forgotemail.getText().toString().contains("com"));
                Log.w("sdfsd is " , ""+Forgotemail.getText().toString().contains("@"));
                Log.w("sdfsd is " , ""+Forgotemail.getText().toString().contains("COM"));
                if(Forgotemail.getText().toString().length() !=0 && Forgotemail.getText().toString().contains(".")
                        &&Forgotemail.getText().toString().contains("@")&&Forgotemail.getText().toString().contains("com")
               ){
                    Retrivethatsht(Forgotemail.getText().toString());
                  //  Toast.makeText(Forgotten_Password.this, "email is "+Forgotemail.getText().toString(), Toast.LENGTH_SHORT).show();

                }else {
                    Forgotemail.setError("Please check email");
                    Toast.makeText(Forgotten_Password.this, "Not a email which you have typed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Retrivethatsht(String Email) {
        Retrofit RetroLogin = new Retrofit.Builder()
                .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api RegApi = RetroLogin.create(Api.class);
        Call<Forgotten_Passowrd> login_responceCall = RegApi.Forgetpassword(Email);
        login_responceCall.enqueue(new Callback<Forgotten_Passowrd>() {
            @Override
            public void onResponse(Call<Forgotten_Passowrd> call, Response<Forgotten_Passowrd> response) {
                Log.d("string dfsd" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
            if(response.isSuccessful())
            {
                Toast.makeText(Forgotten_Password.this, ""+response.body().getMassage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Forgotten_Password.this , LoginActivity.class);
                startActivity(intent);
            }

            }

            @Override
            public void onFailure(Call<Forgotten_Passowrd> call, Throwable t) {
                Log.d("call.clone()" , ""+call.clone());
                Log.d("cause" , ""+t.getCause());
                Log.d("cause" , ""+t.getMessage());
                Log.d("cause" , ""+t.getLocalizedMessage());
                Log.d("call.isExecuted()" , ""+call.isExecuted());
                Log.d("call.isExecuted()" , ""+call.isCanceled());
                Toast.makeText(Forgotten_Password.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
