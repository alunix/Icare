package com.warriors.group.icare;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.BloodSugarListAdapter;
import database.DataStorage;
import model.ProfileModel;
import util.CircleTransform;
import util.Utility;

public class DashBoardActivity extends AppCompatActivity {

    String personID;
    SharedPreferences preferences;
    ImageView dashBoardProfilePicIV;
    TextView dashBoardNameTV;
    TextView dashBoardGenderTV;
    TextView dashBoardBirthDateTV;
    TextView dashBoardAgeTV;
    TextView dashBoardEmailTV;
    TextView dashBoardMobileTV;
    TextView dashBoardHeightTV;
    TextView dashBoardWeightTV;
    TextView dashBoardBloodGroupTV;
    TextView dashBoardDiseaseTV;
    String email;
    String mobile;
    DataStorage dataStorage;
    String name;
    Uri uri;
    Bitmap thumb;
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Diet Chart").withIcon(R.drawable.diet);
    PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Vaccination").withIcon(R.drawable.vaccine);
    PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("Medicine Dose").withIcon(R.drawable.medicine);
    PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("Prescriptions & Reports").withIcon(R.drawable.prescription);
    PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Doctor List").withIcon(R.drawable.doctor);
    PrimaryDrawerItem item6 = new PrimaryDrawerItem().withName("Doctor Appointment").withIcon(R.drawable.appointment);
    PrimaryDrawerItem item7 = new PrimaryDrawerItem().withName("Hospital Address").withIcon(R.drawable.hospital);
    PrimaryDrawerItem item8 = new PrimaryDrawerItem().withName("General Information").withIcon(R.drawable.information);
    PrimaryDrawerItem item10 = new PrimaryDrawerItem().withName("Blood Pressure and Heart rate").withIcon(R.drawable.heart);
    PrimaryDrawerItem item11 = new PrimaryDrawerItem().withName("Blood Sugar Level").withIcon(R.drawable.blood_sugar);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        preferences=getBaseContext().getSharedPreferences("person_id", MODE_PRIVATE);
        personID =preferences.getString("person_id", "");
        dataStorage = new DataStorage(getApplicationContext());
        initializer();
        showData();
        navigationDrawer();

    }

    private void navigationDrawer()
    {

        if(uri != null) {
            ContentResolver cr = getContentResolver();
            InputStream in = null;
            try {
                in = cr.openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            thumb = BitmapFactory.decodeStream(in, null, options);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.cover)
                .addProfiles(
                        new ProfileDrawerItem().withName(name).withEmail(email)
                                .withIcon(thumb)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,item2,
                        item3,item10,
                        item11,item4,
                        item5,item6,
                        item7,item8
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        //Toast.makeText(DashBoardActivity.this,String.valueOf(position), Toast.LENGTH_SHORT).show();
                        if(position==1)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,DietListActivity.class);
                            startActivity(intent);
                        }
                        if(position==2)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,VaccineListActivity.class);
                            startActivity(intent);
                        }
                        if(position==3)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,MedicineListActivity.class);
                            startActivity(intent);
                        }
                        if(position==4)
                        {
                           // Toast.makeText(DashBoardActivity.this,"This feature will be available very soon", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(DashBoardActivity.this,BloodPressureListActivity.class);
                            startActivity(intent);
                        }
                        if(position==5) {
                            //Toast.makeText(DashBoardActivity.this,"This feature will be available very soon", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(DashBoardActivity.this, BloodSugarListActivity.class);
                            startActivity(intent);
                        }
                        if(position==6)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,MedicalHistoryListActivity.class);
                            startActivity(intent);
                        }
                        if(position==7)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,DoctorsListActivity.class);
                            startActivity(intent);
                        }
                        if(position==8)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,AppointmentListActivity.class);
                            startActivity(intent);
                        }
                        if(position==9)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,HospitalListActivity.class);
                            startActivity(intent);
                        }
                        if(position==10)
                        {
                            Intent intent = new Intent(DashBoardActivity.this,GeneralInformationActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();
        result.setSelection(1, false);
    }

    private void showData() {
        ArrayList<ProfileModel> profileModels = new ArrayList<>();
        profileModels = dataStorage.getProfileModelByPersonID(personID);
        name = (profileModels.get(0)).getProfileName();
        String bloodGroup = (profileModels.get(0)).getBloodGroup();
        String gender = (profileModels.get(0)).getGender();
        String birthDt = (profileModels.get(0)).getDateOfBirth();
        email = (profileModels.get(0)).getEmail();
        mobile = (profileModels.get(0)).getContactNo();
        String height = (profileModels.get(0)).getHeight();
        String weight = (profileModels.get(0)).getWeight();
        String profileImagePath = (profileModels.get(0)).getImagePath();
        String majorDisease = (profileModels.get(0)).getMajorDisease();

        ///////////////////////////////////////////
        //Bitmap bmImg= ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(profileImagePath), 96, 90);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = null;
        try {
            birthDate=sdf.parse(birthDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String ageOfPerson= Utility.calculateAge(birthDate);
        String bDate = sdf.format(birthDate);


        /////////////////////////////////////////
        if(profileImagePath!= null)
        {
            uri = Uri.fromFile(new File(profileImagePath));
            Picasso.with(DashBoardActivity.this).load(uri)
                    .resize(225,225).centerCrop().transform(new CircleTransform()).into(dashBoardProfilePicIV);
        }

        dashBoardNameTV.setText(name);

        dashBoardGenderTV.setText(gender);

        dashBoardAgeTV.setText(ageOfPerson+" Old");

        dashBoardBloodGroupTV.setText("Blood group: "+bloodGroup);

        dashBoardBirthDateTV.setText("Birth Date: "+bDate);

        dashBoardEmailTV.setText("Email:"+email);

        dashBoardMobileTV.setText("Contact no :"+mobile);

        dashBoardHeightTV.setText("Height: "+height+" Inch");

        dashBoardWeightTV.setText("Weight: "+weight+" kg");

        dashBoardDiseaseTV.setText("Major Disease: "+majorDisease);

        dashBoardMobileTV.setPaintFlags(dashBoardMobileTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        dashBoardEmailTV.setPaintFlags(dashBoardEmailTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    private void initializer() {
        dashBoardDiseaseTV = (TextView) findViewById(R.id.dashBoardDiseaseTV);
        dashBoardNameTV = (TextView) findViewById(R.id.dashBoardNameTV);
        dashBoardGenderTV = (TextView) findViewById(R.id.dashBoardGenderTV);
        dashBoardBloodGroupTV = (TextView) findViewById(R.id.dashBoardBloodGroupTV);
        dashBoardBirthDateTV = (TextView) findViewById(R.id.dashBoardBirthDateTV);
        dashBoardAgeTV = (TextView) findViewById(R.id.dashBoardAgeTV);
        dashBoardEmailTV = (TextView) findViewById(R.id.dashBoardEmailTV);
        dashBoardMobileTV = (TextView) findViewById(R.id.dashBoardMobileTV);
        dashBoardHeightTV = (TextView) findViewById(R.id.dashBoardHeightTV);
        dashBoardWeightTV = (TextView) findViewById(R.id.dashBoardWeightTV);
        dashBoardProfilePicIV = (ImageView) findViewById(R.id.dashBoardProfilePicIV);
    }

    public void onClickDashCall(View view)
    {
        new AlertDialog.Builder(DashBoardActivity.this)
                .setTitle("User Action")
                .setMessage("What you want to do?")
                .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String number;
                        number = "tel:" + mobile;
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse(number));
                        if (ActivityCompat.checkSelfPermission(DashBoardActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(callIntent);

                    }
                })
                .setNegativeButton("SEND SMS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",mobile, null)));
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onClickDashEmail(View view)
    {
        new AlertDialog.Builder(DashBoardActivity.this)
                .setTitle("User Action")
                .setMessage("What you want to do?")
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("SEND Email", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
                        i.putExtra(Intent.EXTRA_SUBJECT, "");
                        i.putExtra(Intent.EXTRA_TEXT   , "");
                        try
                        {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        }
                        catch (android.content.ActivityNotFoundException ex)
                        {
                            Toast.makeText(DashBoardActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
