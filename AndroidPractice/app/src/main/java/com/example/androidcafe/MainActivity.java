package com.example.androidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent checkingOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        checkingOrderList = new Intent(MainActivity.this, OrderActivity.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(checkingOrderList);
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this , message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDonutOrder(View view) {
        toastMessage(getString(R.string.order_message_donut));
        checkingOrderList.putExtra(Dessert.donut.getOrderProduct(), Dessert.donut.getOrderMessage());
    }

    public void showIceCreamOrder(View view) {
        toastMessage(getString(R.string.order_message_iceCream));
        checkingOrderList.putExtra(Dessert.iceCream.getOrderProduct(), Dessert.iceCream.getOrderMessage());
    }

    public void showFroyoOrder(View view) {
        toastMessage(getString(R.string.order_message_froyo));
        checkingOrderList.putExtra(Dessert.froyo.getOrderProduct(), Dessert.froyo.getOrderMessage());
    }
}