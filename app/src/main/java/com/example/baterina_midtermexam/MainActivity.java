package com.example.baterina_midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spin1, spin2;
    private EditText editText1, editText2;
    private Button buttonChange, buttonClear;
    private TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();



        loadData1(new String [] {"USD", "EUR", "PHP"});
        loadData2(new String [] {"PHP", "EUR", "USD"});


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    double val = Double.parseDouble(editText1.getText().toString());
                    double convertedRes = computeCurrency(val);
                    editText2.setText(String.valueOf(String.format("%.2f",convertedRes)));

                }catch (Exception e){

                    editText2.setText("");
                    Log.e("Error","Unable to convert a blank Number");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    double val = Double.parseDouble(editText2.getText().toString());
                    double convertedRes = computeCurrency(val);
                    editText1.setText(String.valueOf(String.format("%.2f",convertedRes)));

                }catch (Exception e){
                    editText1.setText("");
                    Log.e("Error","Unable to convert a blank Number");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerVal1 = spin1.getSelectedItem().toString();
                String spinnerVal2 = spin2.getSelectedItem().toString();
                String val1 = editText1.getText().toString();
                String val2 = editText2.getText().toString();

                if (val1.isEmpty() || val2.isEmpty()){
                    showMessage("Please Enter Amount");
                    return;
                }
                txtview.setText("You've Change "+ spinnerVal1 + " " + val1 + " To " + spinnerVal2 + " " + val2);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });


    }

    private void initComponent() {
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2= (EditText) findViewById(R.id.editText2);
        spin1 = (Spinner) findViewById(R.id.spinner1);
        spin2 = (Spinner) findViewById(R.id.spinner2);
        buttonChange = (Button) findViewById(R.id.btnChange);
        buttonClear = (Button) findViewById(R.id.btnClear);
        txtview = (TextView) findViewById(R.id.textView);
    }

    private void loadData1(String [] data1) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, data1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
    }

    private void loadData2(String[] data2) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, data2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter);
    }


    private double computeCurrency (double result){
        if (spin1.getSelectedItem().equals("USD") && spin2.getSelectedItem().equals("PHP")){
            result = result * 50;
        }
        if (spin1.getSelectedItem().equals("USD") && spin2.getSelectedItem().equals("EUR")){
            result = result * 0.87;
        }
        if (spin1.getSelectedItem().equals("EUR") && spin2.getSelectedItem().equals("USD")){
            result = result * 1.13;
        }
        if (spin1.getSelectedItem().equals("EUR") && spin2.getSelectedItem().equals("PHP")){
            result = result * 57.471;
        }
        if (spin1.getSelectedItem().equals("PHP") && spin2.getSelectedItem().equals("USD")){
            result = result * 0.020;
        }
        if (spin1.getSelectedItem().equals("PHP") && spin2.getSelectedItem().equals("EUR")){
            result = result * 0.018;
        }
        return result;
    }

    private void showMessage (String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void clear (){
        txtview.setText("");
        editText1.setText("");
        editText2.setText("");
    }
}