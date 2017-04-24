package com.example.android.currencyconverter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.currencyconverter.R.id.amount;
import static com.example.android.currencyconverter.R.layout.activity_main;


public class MainActivity extends AppCompatActivity {

    double EURtoUSD = 1.09;
    double USDtoEUR = 0.92;
    double EURtoDKK = 7.4373;
    double DKKtoEUR = 0.13;
    double USDtoDKK = 6.85;
    double DKKtoUSD = 0.15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
    }

    public void convert(View view) {

        //hides the keyboard when button is pressed
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        EditText amountEntered = (EditText) findViewById(amount);
        if (amountEntered.getText() == null || amountEntered.getText().toString().equals("")) {
            Toast.makeText(this, "You need to enter an amount", Toast.LENGTH_LONG).show();
            return;
        }

        Double amount = Double.parseDouble(amountEntered.getText().toString());

        TextView resultText = (TextView) findViewById(R.id.resultText);
        TextView resultAmount = (TextView) findViewById(R.id.resultAmount);

        RadioGroup rgFrom = (RadioGroup) findViewById(R.id.rgFrom);
        RadioGroup rgTo = (RadioGroup) findViewById(R.id.rgTo);

        int checkedFrom = rgFrom.getCheckedRadioButtonId();
        int checkedTo = rgTo.getCheckedRadioButtonId();

        if (checkedFrom == -1 || checkedTo == -1) {
            Toast.makeText(this, "you need to select both TO and FROM currencies",
                    Toast.LENGTH_LONG).show();
            return;
        }

        String fromSelection = ((RadioButton)
                findViewById(rgFrom.getCheckedRadioButtonId())).getText().toString();
        String toSelection = ((RadioButton)
                findViewById(rgTo.getCheckedRadioButtonId())).getText().toString();

        if (fromSelection == toSelection) {
            Toast.makeText(this, "You need to select different currencies to convert",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (fromSelection.equals("USD") && toSelection.equals("EUR")) {
            amount = amount * USDtoEUR;
        } else if (fromSelection.equals("USD") && toSelection.equals("DKK")) {
            amount = amount * USDtoDKK;
        } else if (fromSelection.equals("EUR") && toSelection.equals("USD")) {
            amount = amount * EURtoUSD;
        } else if (fromSelection.equals("EUR") && toSelection.equals("DKK")) {
            amount = amount * EURtoDKK;
        } else if (fromSelection.equals("DKK") && toSelection.equals("USD")) {
            amount = amount * DKKtoUSD;
        } else if (fromSelection.equals("DKK") && toSelection.equals("EUR")) {
            amount = amount * DKKtoEUR;
        }

        // displays the result
        resultText.setVisibility(View.VISIBLE);
        String amountToDisplay = String.format("%.2f", amount);
        resultAmount.setText(amountToDisplay + " " + toSelection);
        resultAmount.setVisibility(View.VISIBLE);
    }
}
