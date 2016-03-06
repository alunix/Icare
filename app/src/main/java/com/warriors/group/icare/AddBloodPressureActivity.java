package com.warriors.group.icare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import database.DataStorage;
import model.BloodPressureModel;
import model.DoctorModel;

public class AddBloodPressureActivity extends AppCompatActivity implements View.OnClickListener {

    EditText addBPDateET;
    EditText addBPTimeET;
    EditText addBPSBPET;
    EditText addBPDBPET;
    EditText addBPBPMET;
    EditText addBPNotesET;

    Button addBPSaveBTN;
    Button addBPNewBTN;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    SharedPreferences preferences;
    SharedPreferences preferences1;
    String personID;
    String bloodPressureIDUpdate;
    private DataStorage dataStorage;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_pressure);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dataStorage = new DataStorage(getApplicationContext());
        preferences = getBaseContext().getSharedPreferences("person_id", MODE_PRIVATE);
        personID =preferences.getString("person_id", "");
        initializer();
        timePicker();
        datePicker();
//        Toast.makeText(AddVaccinationActivity.this, personID, Toast.LENGTH_LONG).show();
        preferences1=getBaseContext().getSharedPreferences("blood_pressure_id_update", MODE_PRIVATE);
        bloodPressureIDUpdate =preferences1.getString("blood_pressure_id_update", "");
        addBPSaveBTN= (Button) findViewById(R.id.addBPSaveBTN);
        addBPNewBTN=(Button) findViewById(R.id.addBPNewBTN);
        if (bloodPressureIDUpdate.equalsIgnoreCase("")) {

            addBPSaveBTN.setText("SAVE");

        }
        else
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Update Blood Pressure");
            addBPSaveBTN.setText("UPDATE");
            id = Integer.valueOf(bloodPressureIDUpdate);
            showDataforUpdate();
            addBPNewBTN.setVisibility(View.INVISIBLE);
        }

    }


    private void initializer(){

       addBPDateET= (EditText) findViewById(R.id.addBPDateET);
        addBPTimeET= (EditText) findViewById(R.id.addBPTimeET);
        addBPSBPET= (EditText) findViewById(R.id.addBPSBP);
        addBPDBPET= (EditText) findViewById(R.id.addDBPBP);
        addBPBPMET= (EditText) findViewById(R.id.addBPMBP);
        addBPNotesET =(EditText) findViewById(R.id.addNotesBP);
    }

    private void timePicker() {
        addBPTimeET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddBloodPressureActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        addBPTimeET.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//true= 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void showDataforUpdate()
    {

        String bpDate = dataStorage.getBloodPressureBybpId(id).get(0).getBpDate();
        String bpTime = dataStorage.getBloodPressureBybpId(id).get(0).getBpTime();
        String bpSBP = dataStorage.getBloodPressureBybpId(id).get(0).getBpSBP();
        String bpDBP = dataStorage.getBloodPressureBybpId(id).get(0).getBpDBP();
        String bpBPM = dataStorage.getBloodPressureBybpId(id).get(0).getBpBPM();
        String bpNotes = dataStorage.getBloodPressureBybpId(id).get(0).getBpNote();



        addBPDateET.setText(bpDate);
        addBPTimeET.setText(bpTime);
        addBPSBPET.setText(bpSBP);
        addBPDBPET.setText(bpDBP);
        addBPBPMET.setText(bpBPM);
        addBPNotesET.setText(bpNotes);

    }

    private void datePicker()   {
        addBPDateET.setOnClickListener(AddBloodPressureActivity.this);


        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(AddBloodPressureActivity.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                addBPDateET.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    public void OnclickSaveBP(View view) {
        String saveUpdate=addBPSaveBTN.getText().toString();

        if (saveUpdate.equalsIgnoreCase("SAVE"))
        {


            String flag = "A";
            String bpDate = addBPDateET.getText().toString();
            String bpTime=addBPTimeET.getText().toString();
            String bpSBP = addBPSBPET.getText().toString();
            String bpDBP = addBPDBPET.getText().toString();
            String bpBPM = addBPBPMET.getText().toString();
            String bpNotes = addBPNotesET.getText().toString();

            //DoctorModel doctorModel = new DoctorModel(doctorName, doctorSpecialist, doctorAddress, doctorEmailAddress, doctorMobileNumber, flag);

            BloodPressureModel bloodPressureModel=new BloodPressureModel(bpDate,bpTime,bpSBP,bpDBP,bpBPM,bpNotes,flag,personID);

            boolean insert = dataStorage.insertBloodPressure(bloodPressureModel);
            if (insert) {
                Toast.makeText(getApplication(), "Blood Pressure Added Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), "Failed or This Blood Pressure has been Exists", Toast.LENGTH_LONG).show();
            }
        }
        else if (saveUpdate.equalsIgnoreCase("UPDATE"))

        {
            String flag = "A";
            String bpDate = addBPDateET.getText().toString();
            String bpTime=addBPTimeET.getText().toString();
            String bpSBP = addBPSBPET.getText().toString();
            String bpDBP = addBPDBPET.getText().toString();
            String bpBPM = addBPBPMET.getText().toString();
            String bpNotes = addBPNotesET.getText().toString();

            BloodPressureModel bloodPressureModel=new BloodPressureModel(bpDate,bpTime,bpSBP,bpDBP,bpBPM,bpNotes,flag,personID);
            boolean updated = dataStorage.updateBloodPressure(id, bloodPressureModel);
            if (updated) {
                Toast.makeText(getApplication(), "Blood Pressure Updated Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), "Failed or This Blood Pressure Record has been Exists", Toast.LENGTH_LONG).show();
            }

        }


    }

    @Override
    public void onClick(View v) {
        if(v == addBPDateET) {
            datePickerDialog.show();
        }

    }
}
