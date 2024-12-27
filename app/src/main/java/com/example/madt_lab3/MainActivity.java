package com.example.madt_lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtDisplay;
    private CalculatorLogic calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new CalculatorLogic();
        txtDisplay = findViewById(R.id.txtDisplay);

        // Initialize buttons
        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnBack, R.id.btnC, R.id.btnCE, R.id.btnSign, R.id.btnSqrt,
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnEquals
        };

        // Set click listeners for all buttons
        for (int id : buttonIds) {
            Button button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        int id = view.getId();

        if (id == R.id.btnC || id == R.id.btnCE) {  // Added btnCE here
            calculator.clear();
        }
        else if (id == R.id.btnBack) {
            calculator.backspace();
        }
        else if (id == R.id.btnEquals) {
            calculator.calculateResult();
        }
        else if (id == R.id.btnSqrt) {
            calculator.squareRoot();
        }
        else if (id == R.id.btnSign) {
            calculator.changeSign();
        }
        else {
            if (Character.isDigit(buttonText.charAt(0))) {
                calculator.appendNumber(buttonText);
            } else if ("+-×÷".contains(buttonText)) {
                calculator.setOperation(buttonText);
            }
        }

        txtDisplay.setText(calculator.getCurrentValue());
    }
}