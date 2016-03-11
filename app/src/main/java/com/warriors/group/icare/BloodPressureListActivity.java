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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import adapter.BloodPressureListAdapter;
import database.DataStorage;
import model.BloodPressureModel;

public class BloodPressureListActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    SharedPreferences preferences;
    SharedPreferences preferences1;
    String personID;

    ArrayList<BloodPressureModel> bloodPressureModels = new ArrayList<>();
    DataStorage dataStorage;
    ListView bpListView;


    protected BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_list);


        dataStorage = new DataStorage(getApplicationContext());
        preferences=getBaseContext().getSharedPreferences("person_id", MODE_PRIVATE);
        personID =preferences.getString("person_id", "");

        bpListView= (ListView) findViewById(R.id.bplistview);
        //graphOfbp();
        showBloodPressure();



        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        addData();

    }



    private void showBloodPressure(){

        bloodPressureModels = dataStorage.getBloodPressureByProfileId(personID);

        BloodPressureListAdapter bloodPressureListAdapter = new BloodPressureListAdapter(BloodPressureListActivity.this, bloodPressureModels);
        bpListView.setAdapter(bloodPressureListAdapter);
        bloodPressureListAdapter.notifyDataSetChanged();







        bpListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder builder = new AlertDialog.Builder(BloodPressureListActivity.this);
                builder.setTitle("User Action");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Update")) {

                            Intent intent = new Intent(BloodPressureListActivity.this, AddBloodPressureActivity.class);
                            String bp_id_update = String.valueOf((bloodPressureModels.get(position)).getBpId());

                            preferences = getBaseContext().getSharedPreferences("blood_pressure_id_update", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("blood_pressure_id_update", bp_id_update);
                            editor.apply();
                            editor.commit();

                            startActivity(intent);


                        } else if (items[item].equals("Delete")) {

                            new AlertDialog.Builder(BloodPressureListActivity.this)
                                    .setTitle("Delete Entry")
                                    .setMessage("Are you sure you want to Delete This Entry ?")
                                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            int bpId = bloodPressureModels.get(position).getBpId();
                                            boolean delete = dataStorage.deleteBloodPressure(bpId, "D");
                                            if (delete) {
                                                Toast.makeText(getApplication(), "Blood Pressure Information Deleted Successfully", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(getApplication(), "Failed", Toast.LENGTH_LONG).show();
                                            }
                                            showBloodPressure();
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
        showBloodPressure();
        //graphOfbp();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blood_pressure_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_blood_pressure_menu) {
            preferences1 = getBaseContext().getSharedPreferences("blood_pressure_id_update", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = preferences1.edit();
            editor1.putString("blood_pressure_id_update", "");
            editor1.apply();
            editor1.commit();
            Intent intent=new Intent(BloodPressureListActivity.this,AddBloodPressureActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void addData(){

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < dataStorage.queryYData(personID).size(); i++)
            yVals.add(new BarEntry(dataStorage.queryYData(personID).get(i), i));

        ArrayList<String> xVals = new ArrayList<String>();
        for(int i = 0; i < dataStorage.queryXData(personID).size(); i++)
            xVals.add(dataStorage.queryXData(personID).get(i));

        BarDataSet dataSet = new BarDataSet(yVals, "expense values");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(xVals, dataSet);


        LimitLine line = new LimitLine(12f, "average daily expense");
        line.setTextSize(12f);
        line.setLineWidth(4f);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.addLimitLine(line);

        mChart.setData(data);
        mChart.setDescription("The expenses chart.");
        mChart.animateY(2000);

    }


}
