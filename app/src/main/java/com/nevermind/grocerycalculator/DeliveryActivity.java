package com.nevermind.grocerycalculator;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DeliveryActivity extends AppCompatActivity {

    private int selectedQuantity; // Declare selectedQuantity as a class variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        // Retrieve the selected quantity from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SELECTED_QUANTITY")) {
            selectedQuantity = intent.getIntExtra("SELECTED_QUANTITY", 0);
            // Use the selectedQuantity as needed in your DeliveryActivity
            Toast.makeText(this, "Selected Quantity: " + selectedQuantity, Toast.LENGTH_SHORT).show();
        }

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                // Handle the selected time
                String time = formatTime(selectedHour, selectedMinute);
                Toast.makeText(DeliveryActivity.this, "Delivery scheduled at " + time, Toast.LENGTH_SHORT).show();
            }
        }, hour, minute, false);

        // Show the TimePickerDialog when the button is clicked
        Button setTimeButton = findViewById(R.id.setTimeButton);
        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        Button checkoutButton = findViewById(R.id.checkoutButton);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calculate total amount based on the selected quantity and fixed price
                double pricePerItem = 2.00; // Assume a fixed price
                double totalAmount = selectedQuantity * pricePerItem;

                // Display a "Thank you" message with the total amount
                Toast.makeText(DeliveryActivity.this, "Thank you! Total amount: $" + totalAmount, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Format the selected time into a string with proper formatting (e.g., 03:45 PM)
    private String formatTime(int hour, int minute) {
        String timeFormat;
        if (hour >= 12) {
            timeFormat = "PM";
            if (hour > 12) {
                hour -= 12;
            }
        } else {
            timeFormat = "AM";
            if (hour == 0) {
                hour = 12;
            }
        }

        return String.format("%02d:%02d %s", hour, minute, timeFormat);
    }
}
