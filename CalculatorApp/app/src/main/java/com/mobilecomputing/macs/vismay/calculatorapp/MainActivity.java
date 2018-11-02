/*==========================================
Title:  Handles the operations of the calculator
Author: Vismay Revankar (vismayhr@dal.ca)
Date:   1 Oct 2018
============================================*/

package com.mobilecomputing.macs.vismay.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView calculatorDisplay;
    CalculatorApp calculatorApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorDisplay = (TextView) findViewById(R.id.portraitModeDisplay);
        calculatorApp = new CalculatorApp(calculatorDisplay);
    }

    //This method handles button clicks and passes on the ID and
    // value of the clicked button to CalculatorApp. The CalculatorApp
    // then performs the required operations based on the clicked button
    public void buttonOnClick(View view) {
        int buttonId = view.getId();
        Button btn = (Button) findViewById(buttonId);
        String clickedButtonValue = btn.getText().toString();
        boolean response = calculatorApp.parseCalculatorInput(buttonId, clickedButtonValue);
        if (!response) {
            Toast.makeText(getApplicationContext(), "Calculator can only display 10 digits at once!", Toast.LENGTH_LONG).show();
        }
    }
}
