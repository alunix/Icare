package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.warriors.group.icare.R;

import java.util.ArrayList;

import model.BloodPressureModel;
import model.BloodSugarModel;

/**
 * Created by Jewana on 3/4/2016.
 */
public class BloodSugarListAdapter extends ArrayAdapter {

    ArrayList<BloodSugarModel> bloodSugarModels = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public BloodSugarListAdapter(Context context,ArrayList<BloodSugarModel> bloodSugarModels)
    {
        super(context, R.layout.blood_sugar_listview, bloodSugarModels);
        this.context = context;
        this.bloodSugarModels = bloodSugarModels;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder bsHolder=new ViewHolder();

        if(convertView==null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.blood_sugar_listview, parent, false);

            bsHolder.BSDateTV = (TextView) convertView.findViewById(R.id.BSDateTV);
            bsHolder.BSTimeTV = (TextView) convertView.findViewById(R.id.BSTimeTV);
            bsHolder.BSLevelTV = (TextView) convertView.findViewById(R.id.BSLevelTV);
            bsHolder.BSNotesTV = (TextView) convertView.findViewById(R.id.BSNotesTV);

            convertView.setTag(bsHolder);
        }
        else
        {
            bsHolder = (ViewHolder) convertView.getTag();
        }

        String bsDate = bloodSugarModels.get(position).getBsDate();
        String bsTime = bloodSugarModels.get(position).getBsTime();
        String bsLevel = bloodSugarModels.get(position).getBsLevel();
        String bsNotes = bloodSugarModels.get(position).getBsNotes();

        bsHolder.BSDateTV.setText(bsDate);
        bsHolder.BSTimeTV.setText(bsTime);
        bsHolder.BSLevelTV.setText(bsLevel);
        bsHolder.BSNotesTV.setText(bsNotes);

        return convertView;
    }

    private static class ViewHolder
    {
        TextView BSDateTV;
        TextView BSLevelTV;
        TextView BSTimeTV;
        TextView BSNotesTV;

    }
}
