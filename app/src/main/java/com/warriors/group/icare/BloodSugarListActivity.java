package com.warriors.group.icare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.BloodPressureListAdapter;
import adapter.BloodSugarListAdapter;
import database.DataStorage;
import model.BloodPressureModel;
import model.BloodSugarModel;

public class BloodSugarListActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences preferences1;
    String personID;

    ArrayList<BloodSugarModel> bloodSugarModels = new ArrayList<>();
    DataStorage dataStorage;
    ListView bsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_list);
        bsListView = (ListView) findViewById(R.id.bsListView);
        dataStorage = new DataStorage(getApplicationContext());
        preferences=getBaseContext().getSharedPreferences("person_id", MODE_PRIVATE);
        personID =preferences.getString("person_id", "");
        showBloodSugar();
    }
    private void showBloodSugar(){

        bloodSugarModels = dataStorage.getBloodSugarByProfileId(personID);
        BloodSugarListAdapter bloodSugarListAdapter = new BloodSugarListAdapter(BloodSugarListActivity.this, bloodSugarModels);
        bsListView.setAdapter(bloodSugarListAdapter);
        bloodSugarListAdapter.notifyDataSetChanged();



        bsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder builder = new AlertDialog.Builder(BloodSugarListActivity.this);
                builder.setTitle("User Action");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Update")) {

                            Intent intent = new Intent(BloodSugarListActivity.this, AddBloodSugarActivity.class);
                            String bs_id_update = String.valueOf((bloodSugarModels.get(position)).getBsId());

                            preferences = getBaseContext().getSharedPreferences("blood_sugar_id_update", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("blood_sugar_id_update",bs_id_update);
                            editor.apply();
                            editor.commit();

                            startActivity(intent);


                        } else if (items[item].equals("Delete")) {

                            new AlertDialog.Builder(BloodSugarListActivity.this)
                                    .setTitle("Delete Entry")
                                    .setMessage("Are you sure you want to Delete This Entry ?")
                                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            int bsId = bloodSugarModels.get(position).getBsId();
                                            boolean delete = dataStorage.deleteBloodSugar(bsId, "D");
                                            if (delete) {
                                                Toast.makeText(getApplication(), "Blood Sugar Information Deleted Successfully", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(getApplication(), "Failed", Toast.LENGTH_LONG).show();
                                            }
                                            showBloodSugar();
                                        }
                                    })
                                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // do nothing
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    }
                });
                builder.show();


                return true;
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        showBloodSugar();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blood_sugar_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_blood_sugar_menu) {
            preferences1 = getBaseContext().getSharedPreferences("blood_sugar_id_update", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = preferences1.edit();
            editor1.putString("blood_sugar_id_update", "");
            editor1.apply();
            editor1.commit();
            Intent intent=new Intent(BloodSugarListActivity.this,AddBloodSugarActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
