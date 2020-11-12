package com.example.ex092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SolverActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    Intent gi;

    ListView sequenceList;

    TextView info;

    float firstNumber, numD, sequenceSum;

    boolean sequenceType;

    String[] sequenceArr;

    float[] sumValuesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        info = (TextView)findViewById(R.id.info);
        sequenceList = (ListView)findViewById(R.id.sequenceList);

        sequenceSum = 0;
        sequenceArr = new String[20];
        sumValuesArr = new float[20];

        gi = getIntent();
        sequenceType = gi.getBooleanExtra("sequenceType", false);
        firstNumber = gi.getFloatExtra("firstNum", 0);
        numD = gi.getFloatExtra("sequenceD", 0);

        calculateArrValues();

        sequenceList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        sequenceList.setOnCreateContextMenuListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, sequenceArr);
        sequenceList.setAdapter(adp);
    }

    public void returnMain(View view) {
        finish();
    }

    public void calculateArrValues(){
        for (int i = 0; i < 20; i++){
            // Arithmetic sequence (true)
            if (sequenceType){
                sequenceArr[i] = fixValue(firstNumber + i * numD);
            }
            // Geometric sequence (false)
            else {
                sequenceArr[i] = fixValue((float) (firstNumber * Math.pow(numD, i)));
            }
            // create the sum until now's sequence value
            sequenceSum += Float.parseFloat(sequenceArr[i]);
            sumValuesArr[i] = sequenceSum;
        }
    }

    public String fixValue(float value){
        // fix the value (without 0.0)
        if ((float)((int)value) == value) {
            return String.valueOf((int)value);
        }
        return String.valueOf(value);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("More Options");
        menu.add("Place of item");
        menu.add("Sum sequence");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String option = item.getTitle().toString();
        if (option.equals("Place of item"))
        {
            info.setText((menuInfo.position + 1) + "");
            return true;
        }
        if (option.equals("Sum sequence"))
        {
            info.setText(fixValue(sumValuesArr[menuInfo.position]));
            return true;
        }
        return super.onContextItemSelected(item);
    }
}