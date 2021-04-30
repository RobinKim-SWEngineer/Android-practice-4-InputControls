package com.example.androidcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private TextView textViewOrderList;
    private final String defaultStringForPreferenceFile = "Nothing to retrieve";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        textViewOrderList = findViewById(R.id.textView_orderList);

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
}