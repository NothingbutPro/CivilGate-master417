package dev.raghav.civilgate;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.raghav.civilgate.Activities.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 20;
    // SessionManager manager;
    AlertDialog.Builder builder;
    AlertDialog finialpermissionDialog;
    private static int SPLASH_TIME_OUT = 2000;
     Context context = SplashActivity.this;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        builder = new AlertDialog.Builder(SplashActivity.this);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //manager =new SessionManager(SplashActivity.this);
         i = new Intent(SplashActivity.this, LoginActivity.class);
        setContentView(R.layout.activity_splash);

            new Handler().postDelayed(() -> {
                startActivity(i);
                finish();
//                try{
//
//                    if (manager.isLoggedIn()) {
//
//                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        SplashActivity.this.finish();
//                    } else {
//                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        SplashActivity.this.finish();
//                    }
//                }catch (Exception e) {
//                }



            }, SPLASH_TIME_OUT);









    }

//    private Boolean check_permisssion() {
//        int permissionGetPic = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE);
//        int permissionPutpic = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        if (permissionPutpic != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (permissionGetPic != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }
//        if (!listPermissionsNeeded.isEmpty()) {
////            startActivity(i);
////            finish();
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
//            return false;
//        }
//        return true;
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        Log.d("TAG", "Permission callback called-------");
//        switch (requestCode) {
//            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
//
//                Map<String, Integer> perms = new HashMap<>();
//                // Initialize the map with both permissions
//                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
//                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
//                // Fill with actual results from user
//                if (grantResults.length > 0) {
//                    for (int i = 0; i < permissions.length; i++)
//                        perms.put(permissions[i], grantResults[i]);
//                    // Check for both permissions
//                    if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//                            && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                        Log.d(SplashActivity.this.getLocalClassName(), "sms & location services permission granted");
//                        // process the normal flow
//                        //else any one or both the permissions are not granted
//                    } else {
//                        Log.d(SplashActivity.this.getLocalClassName(), "Some permissions are not granted ask again ");
//                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
////                        // shouldShowRequestPermissionRationale will return true
//                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
//                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                            showDialogOK("SMS and Location Services Permission required for this app",
//                                    new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            switch (which) {
//                                                case DialogInterface.BUTTON_POSITIVE:
//                                                   // check_permisssion();
//                                                    break;
//                                                case DialogInterface.BUTTON_NEGATIVE:
//                                                    // proceed with logic by disabling the related features or quit the app.
//                                                    break;
//                                            }
//                                        }
//                                    });
//                        }
//                        //permission is denied (and never ask again is  checked)
//                        //shouldShowRequestPermissionRationale will return false
//                        else {
//                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
//                                    .show();
//                            //                            //proceed with logic by disabling the related features or quit the app.
//                        }
//                    }
//                }
//            }
//        }
//
//    }

//    private void showDialogOK(String s, DialogInterface.OnClickListener onClickListener) {
//
//        new AlertDialog.Builder(this)
//                .setMessage("THnaks")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "thats positivelu", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "sdgdgsdg", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create()
//                .show();
//    }
}
