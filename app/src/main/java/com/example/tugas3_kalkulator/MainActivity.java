package com.example.tugas3_kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentOperator = "";
    private String currentValue = "";
    private StringBuilder operationBuilder = new StringBuilder();  // Menyimpan operasi hitung
    private double firstValue = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    public void onDigit(View view) {
        Button button = (Button) view;
        if (isOperatorPressed) {
            currentValue = "";
            isOperatorPressed = false;
        }
        currentValue += button.getText().toString();
        operationBuilder.append(button.getText().toString());  // Tambahkan angka ke operasi
        display.setText(operationBuilder.toString());  // Tampilkan operasi
    }

    public void onOperator(View view) {
        Button button = (Button) view;
        if (!isOperatorPressed) {
            currentOperator = button.getText().toString();
            firstValue = Double.parseDouble(currentValue);
            operationBuilder.append(" ").append(currentOperator).append(" ");  // Tambahkan operator ke operasi
            isOperatorPressed = true;
            display.setText(operationBuilder.toString());  // Tampilkan operasi
        }
    }

    public void onEqual(View view) {
        double result = 0;
        double secondValue = Double.parseDouble(currentValue);

        switch (currentOperator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "x":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = firstValue / secondValue;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }

        // Mengonversi hasil ke bentuk integer
        int finalResult = (int) result;

        // Tambahkan hasil ke operasi
        operationBuilder.append(" = ").append(finalResult);

        // Tampilkan operasi dengan hasil dalam bentuk integer
        display.setText(operationBuilder.toString());

        // Reset nilai untuk operasi selanjutnya
        currentValue = String.valueOf(finalResult);
        firstValue = finalResult;
    }

    public void onClear(View view) {
        currentValue = "";
        firstValue = 0;
        currentOperator = "";
        operationBuilder.setLength(0);  // Reset operasi
        display.setText("0");
    }
}
