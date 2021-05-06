package com.example.androidcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Checkable;
import android.widget.Toast;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textViewOrderList;
    private final String defaultStringForPreferenceFile = "Nothing to retrieve";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        textViewOrderList = findViewById(R.id.textView_orderList);
        Spinner spinnerContactType = findViewById(R.id.spinner_contactType);
        if (spinnerContactType != null) {
            ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.contact_numbers, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerContactType.setAdapter(arrayAdapter);

            spinnerContactType.setOnItemSelectedListener(this);
        }

        String previousOrder = retrieveFromPreferenceFile();
        if (previousOrder.equals(defaultStringForPreferenceFile)) {
            previousOrder = "";
        }

        Intent intent = getIntent();
        displayOrders(previousOrder + getAllOrderMessages(intent));
    }

    private String getAllOrderMessages(Intent intent) {
        String orderMessages = "";

        for (Dessert dessert : Dessert.values()) {
            String message = intent.getStringExtra(dessert.getOrderProduct());

            if (message != null) {
                message += "\n\n";

                orderMessages += message;
            }
        }
        return orderMessages;
    }

    private void displayOrders(String orders) { textViewOrderList.setText(orders); }

    private void toastMessage(String message) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 16908332) {
            saveIntoPreferenceFile();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveIntoPreferenceFile() {
        SharedPreferences sharedPreferences = getSharedPreferences("orderPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("previous_order", textViewOrderList.getText().toString());
        editor.apply();
    }

    private String retrieveFromPreferenceFile() {
        SharedPreferences sharedPreferences = getSharedPreferences("orderPreferences", MODE_PRIVATE);
        return sharedPreferences.getString("previous_order", defaultStringForPreferenceFile);
    }

    public void clearOrderList(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("orderPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        textViewOrderList.setText("");
    }

    public void onDeliveryMethodChosen(View view) {
        RadioButton radioButton = (RadioButton)view;
        boolean isChecked = radioButton.isChecked();

            switch (view.getId()) {
                case R.id.radioButton_firstDeliOption :
                    if (isChecked)
                        toastMessage(getString(R.string.radioButton_firstDeliOption));
                    break;
                case R.id.radioButton_secondDeliOption:
                    if (isChecked)
                        toastMessage(getString(R.string.radioButton_secondDeliOption));
                    break;
                case R.id.radioButton_thirdDeliOption:
                    if (isChecked)
                        toastMessage(getString(R.string.radioButton_thirdDeliOption));
                    break;
            }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            return;
        }
        String numberType = parent.getItemAtPosition(position).toString();
        toastMessage(numberType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}