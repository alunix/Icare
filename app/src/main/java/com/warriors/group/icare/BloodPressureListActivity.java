package com.warriors.group.icare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BloodPressureListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_list);


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
//            preferences1 = getBaseContext().getSharedPreferences("diet_id_update", MODE_PRIVATE);
//            SharedPreferences.Editor editor1 = preferences1.edit();
//            editor1.putString("diet_id_update", "");
//            editor1.apply();
//            editor1.commit();
//            Intent intent=new Intent(DietListActivity.this,AddDietActivity.class);
//            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
