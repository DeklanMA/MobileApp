package com.example.uts.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.uts.R;

public class CalculatorFragmen extends Fragment implements View.OnClickListener {

    private TextView display;
    private StringBuilder currentNumber;
    private double result;
    private String operator;
    private boolean operatorClicked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);

        display = rootView.findViewById(R.id.display);
        currentNumber = new StringBuilder();
        result = 0.0;
        operator = "";
        operatorClicked = false;

        // Mengatur listener untuk setiap tombol
        int[] buttonIds = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
                R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_subtract,
                R.id.btn_multiply, R.id.btn_divide, R.id.btn_equals, R.id.btn_clear};

        for (int buttonId : buttonIds) {
            Button button = rootView.findViewById(buttonId);
            button.setOnClickListener(this);
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "=":
                evaluateExpression();
                break;
            case "C":
                clearDisplay();
                break;
            case "+":
            case "-":
            case "x":
            case "/":
                handleOperator(buttonText);
                break;
            default:
                appendToDisplay(buttonText);
                break;
        }
    }

    private void appendToDisplay(String text) {
        if (operatorClicked) {
            currentNumber = new StringBuilder();
            operatorClicked = false;
        }
        currentNumber.append(text);
        display.setText(currentNumber.toString());
    }

    private void handleOperator(String selectedOperator) {
        if (!currentNumber.toString().isEmpty()) {
            operator = selectedOperator;
            result = Double.parseDouble(currentNumber.toString());
            currentNumber = new StringBuilder();
            operatorClicked = true;
        }
    }

    private void evaluateExpression() {
        if (!currentNumber.toString().isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber.toString());
            switch (operator) {
                case "+":
                    result += secondNumber;
                    break;
                case "-":
                    result -= secondNumber;
                    break;
                case "x":
                    result *= secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result /= secondNumber;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            currentNumber = new StringBuilder();
            operator = "";
        }
    }

    private void clearDisplay() {
        currentNumber = new StringBuilder();
        result = 0.0;
        operator = "";
        operatorClicked = false;
        display.setText("0");
    }
}