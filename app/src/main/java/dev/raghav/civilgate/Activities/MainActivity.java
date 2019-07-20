package dev.raghav.civilgate.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import dev.raghav.civilgate.Api.Api;
import dev.raghav.civilgate.Api.Retro_Urls;
import dev.raghav.civilgate.Frag_granades.Home;
import dev.raghav.civilgate.Other_Parsing_Files.Credit;
import dev.raghav.civilgate.Parsingfiles.LoginReg.Login_Responce;
import dev.raghav.civilgate.R;
import dev.raghav.civilgate.SessionManage.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Response<Login_Responce> response;
    Fragment mainFragment = null;
     SessionManager manager;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//putt home frags
        manager=new SessionManager(MainActivity.this);

        mainFragment = new Home();
        LoadGodamnFrags(mainFragment);
        //
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_viewxc);


        Load_Prfolie_Data();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu_image);
        navigationView.getMenu().findItem(R.id.nav_news).setVisible(false);
        navigationView.getMenu().getItem(5).setActionView(R.layout.menu_image);
        navigationView.getMenu().findItem(R.id.nav_group).setVisible(false);
        navigationView.getMenu().getItem(6).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(7).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(8).setActionView(R.layout.menu_image);
        navigationView.getMenu().findItem(R.id.nav_ask_expert).setVisible(false);
        navigationView.getMenu().getItem(9).setActionView(R.layout.menu_image);
       // navigationView.getMenu().getItem(10).setActionView(R.layout.menu_image);
//        navigationView.getMenu().getItem(11).setActionView(R.layout.menu_image);
        navigationView.setItemIconTintList(null);
//        response = getIntent().getStringArrayExtra("respoce");

//        Toast.makeText(this, ""+response.body().getResponce() , Toast.LENGTH_SHORT).show();

    }

    private void Load_Prfolie_Data() {
            Toast.makeText(this, "Credentials called", Toast.LENGTH_SHORT).show();
            Retrofit RetroLogin = new Retrofit.Builder()
                    .baseUrl(Retro_Urls.The_Base).addConverterFactory(GsonConverterFactory.create())
                    .build();
        TextView userName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.pronames);

        TextView userEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.proemails);
        ImageView userProfile =  navigationView.getHeaderView(0).findViewById(R.id.proimgs);
        userName.setText("gfgfgh");
            Api RegApi = RetroLogin.create(Api.class);
            Call<Credit> login_responceCall = RegApi.Get_Credits(manager.getCoustId());
            login_responceCall.enqueue(new Callback<Credit>() {
                @Override
                public void onResponse(Call<Credit> call, Response<Credit> response) {
                    Log.d("string" , ""+response.body().getResponce());
//                            Log.d("string" , ""+response.body().getData().getEmail());
                    if(response.body().getResponce())
                    {
//                    Toast.makeText(ShowAllPakages.this, "Login successful", Toast.LENGTH_SHORT).show();
                        for(int k=0;k< response.body().getData().size() ;k++) {
                            Log.e("name " , ""+response.body().getData().get(0).getName());
                            Log.e("email " , ""+response.body().getData().get(0).getEmail());

                            userName.setText(response.body().getData().get(0).getName());
                            //userName.setText(response.body().getData().get(0).getName());
                            userEmail.setText(response.body().getData().get(0).getEmail());
                          //  userEmail.setText(response.body().getData().get(0).getEmail());

//                            Glide.with(MainActivity.this)
//                                    .load(Retro_Urls.The_Base+"uploads/"+response.body().getData().get(0).getProfileImage())
//                                    .override(300, 200)
//                                    .into(userProfile);

                        }

//                    Intent intent=new Intent(ShowAllPakages.this,MainActivity.class);
//                    //manager.serverLogin(response.body().getData().getId() , response.body().getData().getName(),response.body().getData().getStatus());
//                    intent.putExtra("respoce", ""+response);
//                    startActivity(intent);
//                    finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Either Email is wrong or Password", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Credit> call, Throwable t) {

                    Log.d("cause" , ""+t.getCause());
                    Toast.makeText(MainActivity.this, "Network problem", Toast.LENGTH_SHORT).show();

                }
            });


    }

    private Fragment LoadGodamnFrags(Fragment mainFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dasboard_frame, mainFragment);
        fragmentTransaction.commit();
        return mainFragment;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_credits) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_homepage) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this , Profile_Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_buy_package) {
            Intent intent = new Intent(MainActivity.this ,  ShowAllPakages.class);
            startActivity(intent);

        } else if (id == R.id.nav_my_test) {
            Intent intent = new Intent(MainActivity.this , HisHistoryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_group) {

        } else if (id == R.id.nav_bookmark) {
            Intent intent = new Intent(MainActivity.this , MaBookmarks.class);
            startActivity(intent);
//            Intent intent = new Intent(MainActivity.this , BookmarkActivity.class);
//            startActivity(intent);

        } else if (id == R.id.nav_news) {

        }

//        else if (id == R.id.nav_offers) {
//
//        }
//        else if (id == R.id.nav_ex_offer) {
//
//        }
        else if (id == R.id.nav_ask_expert) {
//            Intent intent =new Intent(this , KYCACTIVTy.class);
//            startActivity(intent);

        } else if (id == R.id.nav_rate_us) {

        } else if (id == R.id.logout) {

            final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this).setTitle("Go Gate Exam")
                    .setMessage("Are you sure, you want to logout this app");

            dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    exitLauncher();
                }

                private void exitLauncher() {
                    manager.logoutUser();
                   // manager.setAfterName(null);
                   // AppPreference.setAfterId(getApplicationContext(), "null");
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();

            return true;


        } else if (id == R.id.aboutus) {
            Intent aboutIntent = new Intent(MainActivity.this, About_All.class);
            startActivity(aboutIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
