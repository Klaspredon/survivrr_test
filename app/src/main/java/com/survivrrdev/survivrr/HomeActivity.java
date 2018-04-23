package com.survivrrdev.survivrr;

/*
import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
*/
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.Toast;
/*
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnSuccessListener;
*/
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private final AppCompatActivity activity = HomeActivity.this;
    private String username;
    private DBHelper dbHelper;
    private UserInfo userInfo;

    //    EditText sms_num, sms_mes;
    Button sms_send_A, sms_send_B, sms_send_C;

    // dummy data
//    String[] info_num = { "09357471170", "09178402099", "09357471170" };
//    String[] info_name = { "Julius Robert Oppenheimer", "Adolf Hitler", "Juan Luna" };
//    String[] info_data = { "I have become death, the destroyer of worlds...",
//            "How long we'll be together? Forevermore...",
//            "I am a man with sad stories" };

    // non-persistent data
    String info_USERname;
    String info_USERnum;
    String info_USERaddr;
    String info_USERgender;
    String info_USERbday;
    String info_PTCICOEname;
    String info_PTCICOEnum;
    String info_PTCICOEaddr;

    String[] request_type = { "FIRE DEPARTMENT", "HOSPITAL", "POLICE" };


    /*
        // location
        private GeoDataClient mGeoDataClient;
        private PlaceDetectionClient mPlaceDetectionClient;
        private FusedLocationProviderClient mFusedLocationProviderClient;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // get info from database
        dbHelper = new DBHelper(activity);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            username = extras.getString("USERNAME");
        }

        userInfo = dbHelper.getUserInfo(username);

        info_USERname = userInfo.getUserName();
        info_USERnum = userInfo.getUserPhone();
        info_USERaddr = userInfo.getUserAddress();
//        info_USERgender = userInfo.getUserGender();
//        info_USERbday = userInfo.getUserBirthday();
        info_PTCICOEname = userInfo.getUserContactName();
        info_PTCICOEnum = userInfo.getUserContactPhone();
        info_PTCICOEaddr = userInfo.getUserContactAddress();
/*
        // location
        // Construct a GeoDataClient
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetection Client
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
*/

        // sms
        sms_send_A = findViewById(R.id.sms_send_A);
        sms_send_B = findViewById(R.id.sms_send_B);
        sms_send_C = findViewById(R.id.sms_send_C);

        sms_send_A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                send_sms(0);
            }
        });
        sms_send_B.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                send_sms(1);
            }
        });
        sms_send_C.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                send_sms(2);
            }
        });
    }
/*
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }
*/
/*
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });
*/

    protected void send_sms(int x) {
//        String s_sms_num = sms_num.getText().toString();
//        String s_sms_mes = sms_mes.getText().toString();

        // get current timestamp
        final SimpleDateFormat sdf_date = new SimpleDateFormat("E yyyy-MM-dd");
        final SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss a");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String s_timestamp = sdf_date.format(timestamp) + " at " + sdf_time.format(timestamp);

        // setup sms message to PTCICOE
        String PTCICOE_sms_mes = "Sent on " + s_timestamp + "\n" + info_USERname.toUpperCase() + " has requested help from the " + request_type[x] + ". ";

//        if(info_USERgender == "Male"){
            PTCICOE_sms_mes = PTCICOE_sms_mes + "He ";
//        }

        PTCICOE_sms_mes = PTCICOE_sms_mes + "currently lives on " + info_USERaddr.toUpperCase() + ".\n";
        PTCICOE_sms_mes = PTCICOE_sms_mes + "Please verify the emergency by calling this number but take note to proceed with CAUTION.\n\n";
        PTCICOE_sms_mes = PTCICOE_sms_mes + "[You are receiving this because you are listed as the Person to Contact in Case of Emergency.]";


        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(s_sms_num, null, s_sms_mes, null, null);

        ArrayList<String> s_sms_mes_divided = sms.divideMessage(PTCICOE_sms_mes);
        sms.sendMultipartTextMessage(info_PTCICOEnum, null, s_sms_mes_divided, null, null);

        Toast.makeText(getApplicationContext(), "Message Sent to " + info_PTCICOEname.toUpperCase() + "!",
                Toast.LENGTH_LONG).show();
    }
}
