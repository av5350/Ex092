package com.example.ex092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    Intent si;

    /**
     * The Sequence type.
     */
    Switch sequenceType;

    /**
     * The First num.
     */
    EditText firstNum, /**
     * The Sequence d.
     */
    sequenceD;

    /**
     * The First value.
     */
    String firstValue, /**
     * The String d.
     */
    stringD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = (EditText)findViewById(R.id.firstNum);
        sequenceD = (EditText)findViewById(R.id.sequenceD);

        sequenceType = (Switch)findViewById(R.id.sequenceType);

        si = new Intent(this, SolverActivity.class);
    }

    /**
     * Get the fields from the text views, check if they are good
     * if so, go the the second activity, else - print(with toast) an error massage.
     *
     * @param view the view
     */
    public void goSolver(View view) {
        firstValue = firstNum.getText().toString();
        stringD = sequenceD.getText().toString();

        if (!firstValue.equals("") && !stringD.equals("")
                && !firstValue.equals(".") && !stringD.equals(".")
                && !firstValue.equals("-") && !stringD.equals("-")
                && !firstValue.equals("-.") && !stringD.equals("-."))
        {
            // is it Arithmetic sequence (true)
            // or Geometric sequence (false)
            si.putExtra("sequenceType", sequenceType.isChecked());

            si.putExtra("firstNum", Float.parseFloat(firstValue));
            si.putExtra("sequenceD", Float.parseFloat(stringD));

            startActivity(si);
        }
        else {
            Toast.makeText(this, "Error! You have to enter values", Toast.LENGTH_SHORT).show();
        }
    }
}