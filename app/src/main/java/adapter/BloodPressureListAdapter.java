package adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import com.warriors.group.icare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.BloodPressureModel;

/**
 * Created by Jewana on 3/4/2016.
 */
public class BloodPressureListAdapter extends ArrayAdapter {
    ArrayList<BloodPressureModel> bloodPressureModels = new ArrayList<>();
    Context context;
    LayoutInflater inflater;


    public BloodPressureListAdapter(Context context, ArrayList<BloodPressureModel> bloodPressureModels)
    {
        super(context, R.layout.blood_pressurse_listview, bloodPressureModels);
        this.context = context;
        this.bloodPressureModels = bloodPressureModels;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder bpHolder=new ViewHolder();

        if(convertView==null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.blood_pressurse_listview, parent, false);

            bpHolder.bpDateListView = (TextView) convertView.findViewById(R.id.BPDateTV);
            bpHolder.bpTimeListView = (TextView) convertView.findViewById(R.id.BPTimeTV);
            bpHolder.bpSBPListView = (TextView) convertView.findViewById(R.id.BPSBPTV);
            bpHolder.bpDBPListView = (TextView) convertView.findViewById(R.id.BPDBMTV);
            bpHolder.bpBPMListView = (TextView) convertView.findViewById(R.id.BPBPMTV);
            bpHolder.bpNotesListView = (TextView) convertView.findViewById(R.id.BPNotesTV);

            convertView.setTag(bpHolder);
        }
        else
        {
            bpHolder = (ViewHolder) convertView.getTag();
        }

        String bpDate = bloodPressureModels.get(position).getBpDate();
        String bpTime = bloodPressureModels.get(position).getBpTime();
        String bpSBP = bloodPressureModels.get(position).getBpSBP();
        String bpDBP = bloodPressureModels.get(position).getBpDBP();
        String bpBPM = bloodPressureModels.get(position).getBpBPM();
        String bpNotes = bloodPressureModels.get(position).getBpNote();


        //String address = dietMenu+","+diteTime+","+dietDate;



        bpHolder.bpDateListView.setText(bpDate);
        bpHolder.bpTimeListView.setText(bpTime);
        bpHolder.bpSBPListView.setText(bpSBP);
        bpHolder.bpDBPListView.setText(bpDBP);
        bpHolder.bpBPMListView.setText(bpBPM);
        bpHolder.bpNotesListView.setText(bpNotes);

        return convertView;
    }

    private static class ViewHolder
    {

        TextView bpDateListView;
        TextView bpTimeListView;
        TextView bpSBPListView;
        TextView bpDBPListView;
        TextView bpBPMListView;
        TextView bpNotesListView;


    }
}
