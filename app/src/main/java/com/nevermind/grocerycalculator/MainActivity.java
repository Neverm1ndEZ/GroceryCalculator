package com.nevermind.grocerycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView appleImageView = findViewById(R.id.appleImageView);
        Spinner appleSpinner = findViewById(R.id.appleSpinner);
        Button proceedButton = findViewById(R.id.proceedButton);

        appleImageView.setImageResource(R.drawable.apple_image);

        // Create an ArrayAdapter with a range of values (1 to 10 for example)
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, generateQuantityValues(1, 10));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appleSpinner.setAdapter(adapter);

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected quantity from the spinner
                int selectedQuantity = Integer.parseInt(appleSpinner.getSelectedItem().toString());

                // Create an Intent to navigate to the next activity
                Intent intent = new Intent(MainActivity.this, DeliveryActivity.class);

                // Pass the selected quantity to the DeliveryActivity using intent.putExtra()
                intent.putExtra("SELECTED_QUANTITY", selectedQuantity);

                // Start the new activity
                startActivity(intent);
            }
        });
    }

    // Generate a list of integers from start to end (inclusive)
    private Integer[] generateQuantityValues(int start, int end) {
        Integer[] values = new Integer[end - start + 1];
        for (int i = 0; i < values.length; i++) {
            values[i] = start + i;
        }
        return values;
    }
}