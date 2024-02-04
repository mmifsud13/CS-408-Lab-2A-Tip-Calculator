package com.example.mifsudcs408lab2btipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mifsudcs408lab2btipcalculator.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText totalBillEditText = findViewById(R.id.totalBillEditText);
        EditText tipPercentageEditText = findViewById(R.id.tipPercentageEditText);
        EditText numPeopleEditText = findViewById(R.id.numPeopleEditText);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView outputField = findViewById(R.id.outputField);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalPerPerson(totalBillEditText, tipPercentageEditText, numPeopleEditText, outputField);
            }
        });
    }

    private void calculateTotalPerPerson(EditText totalBillEditText, EditText tipPercentageEditText, EditText numPeopleEditText, TextView outputField) {
        if (TextUtils.isEmpty(totalBillEditText.getText()) ||
                TextUtils.isEmpty(tipPercentageEditText.getText()) ||
                TextUtils.isEmpty(numPeopleEditText.getText())) {
            return;
        }

        double totalBill = Double.parseDouble(totalBillEditText.getText().toString());
        double tipPercentage = Double.parseDouble(tipPercentageEditText.getText().toString());
        int numPeople = Integer.parseInt(numPeopleEditText.getText().toString());

        if (totalBill == 0 || tipPercentage == 0 || numPeople == 0) {
            return;
        }

        double totalPerPerson = (totalBill * (1 + tipPercentage / 100)) / numPeople;

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String result = currencyFormat.format(totalPerPerson);

        outputField.setText(String.format("%s%s", getString(R.string.totalPerPerson), result));
    }
}
