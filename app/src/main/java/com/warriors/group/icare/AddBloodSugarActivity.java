package com.warriors.group.icare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import database.DataStorage;
import model.BloodPressureModel;
import model.BloodSugarModel;

public class AddBloodSugarActivity extends AppCompatActivity implements View.OnClickListener {
    EditText addBSTimeET;
    EditText addBSDateET;
    EditText addLevelBSET;
    EditText addNotesBSET;
    Button addBSSaveBTN;
    Button addBSNewBTN;

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    SharedPreferences preferences;
    SharedPreferences preferences1;
    String personID;
    String bloodSugarIdUpdate;
    DataStorage dataStorage;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_sugar);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dataStorage = new DataStorage(getApplicationContext());
        preferences = getBaseContext().getSharedPreferences("person_id", MODE_PRIVATE);
        personID =preferences.getString("person_id", "");
        initializer();
        timePicker();
        datePicker();

        preferences1=getBaseContext().getSharedPreferences("blood_sugar_id_update", MODE_PRIVATE);
        bloodSugarIdUpdate =preferences1.getString("blood_sugar_id_update", "");
        if (bloodSugarIdUpdate.equalsIgnoreCase("")) {

            addBSSaveBTN.setText("SAVE");

        }
        else
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Update Blood Sugar");
            addBSSaveBTN.setText("UPDATE");
            id = Integer.valueOf(bloodSugarIdUpdate);
            showDataforUpdate();
            addBSNewBTN.setVisibility(View.INVISIBLE);
        }
    }

    private void initializer(){

        addBSTimeET= (EditText) findViewById(R.id.addBSTimeET);
        addBSDateET= (EditText) findViewById(R.id.addBSDateET);
        addLevelBSET= (EditText) findViewById(R.id.addLevelBS);
        addNotesBSET= (EditText) findViewById(R.id.addNotesBS);
        addBSSaveBTN= (Button) findViewById(R.id.addBSSaveBTN);
        addBSNewBTN =(Button) findViewById(R.id.addBSNewBTN);
    }

    private void showDataforUpdate()
    {

        String bsDate = dataStorage.getBloodSugarBybsId(id).get(0).getBsDate();
        String bsTime = dataStorage.getBloodSugarBybsId(id).get(0).getBsTime();
        String bsLevel = dataStorage.getBloodSugarBybsId(id).get(0).getBsLevel();
        String bsNotes = dataStorage.getBloodSugarBybsId(id).get(0).getBsNotes();

        addBSTimeET.setText(bsDate);
        addBSDateET.setText(bsTime);
        addLevelBSET.setText(bsLevel);
        addNotesBSET.setText(bsNotes);

    }

    private void timePicker() {
        addBSTimeET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddBloodSugarActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        addBSTimeET.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//true= 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void datePicker()   {
        addBSDateET.setOnClickListener(AddBloodSugarActivity.this);


        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(AddBloodSugarActivity.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                addBSDateET.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View v) {
        if(v == addBSDateET) {
            datePickerDialog.show();
        }

    }

    public void OnclickSaveBS(View view) {
        String saveUpdate=addBSSaveBTN.getText().toString();

        if (saveUpdate.equalsIgnoreCase("SAVE"))
        {


            String flag = "A";
            String bsDate = addBSDateET.getText().toString();
            String bsTime=addBSTimeET.getText().toString();
            String bsLevel = addLevelBSET.getText().toString();
            String bsNotes = addNotesBSET.getText().toString();

            BloodSugarModel bloodSugarModel =new BloodSugarModel(bsDate,bsTime,bsLevel,bsNotes,flag,personID);

            boolean insert = dataStorage.insertBloodSugar(bloodSugarModel);
            if (insert) {
                Toast.makeText(getApplication(), "Blood Sugar Added Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), "Failed or This Blood Sugar has been Exists", Toast.LENGTH_LONG).show();
            }
        }
        else if (saveUpdate.equalsIgnoreCase("UPDATE"))

        {
            String flag = "A";
            String bsDate = addBSDateET.getText().toString();
            String bsTime= addBSTimeET.getText().toString();
            String bsLevel = addLevelBSET.getText().toString();
            String bsNotes = addNotesBSET.getText().toString();

            BloodSugarModel bloodSugarModel =new BloodSugarModel(bsDate,bsTime,bsLevel,bsNotes,flag,personID);
            boolean updated = dataStorage.updateBloodSugar(id, bloodSugarModel);
            if (updated) {
                Toast.makeText(getApplication(), "Blood Sugar Updated Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), "Failed or This Blood Sugar Record has been Exists", Toast.LENGTH_LONG).show();
            }

        }

    }
}
