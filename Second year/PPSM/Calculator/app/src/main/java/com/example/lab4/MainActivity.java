package com.example.lab4;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    String result = "";
    double num1;
    double num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // ##########################
    // saving state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView output = findViewById(R.id.outputField);
        String str = output.getText().toString();
        outState.putString("KEY",str);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String str = savedInstanceState.getString("KEY","0");
        TextView output = findViewById(R.id.outputField);
        result = str;
        output.setText(str);
    }
    // ##########################

    public static long silnia(int n){
        if (n<2)
            return 1;
        return n*silnia(n-1);
    }

    public void calc(View view) {

        String buttonText = ((Button)view).getText().toString();
        TextView output2 = findViewById(R.id.outputField);
        Button btn = findViewById(R.id.plus);

        switch (buttonText) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                this.concatenate(buttonText);
                break;
            case "+":
            case "-":
            case "/":
            case ".":
                if (result != ""){
                    this.concatenate(buttonText);
                }
                break;
            case "x":
                if (result != ""){
                    this.concatenate("*");
                }
                break;
            case "AC":
                num1 = 0;
                num2 = 0;
                result = "";
                output2.setText("0");
                break;
            case "+/-":
                num1 = Double.parseDouble(result.toString());
                num1 *= (-1);
                output2.setText("");
                result = String.valueOf(num1);
                output2.setText(""+ result);
                break;
            case "=" :
                if (result != ""){
                    if (result.contains("/0")){ // TODO increase input or decrease font size
                        output2.setText("Division by zero");
                    }
                    else{
                        Expression e = new Expression(result.toString());
                        double expressionResult = e.calculate();
                        result = String.valueOf(expressionResult);
                        output2.setText(result);
                    }
                }
            break;

            // LANDSCAPE ADVANCED OPTIONS
            case "log10" :
                if (result != "") {
                    num1 = Double.parseDouble(result);
                    num1 = Math.log10(num1);
                    result = String.valueOf(num1);
                    output2.setText(String.valueOf(num1));
                }
                break;
            case "sqrt" :
                if (result != "") {
                    num1 = Double.parseDouble(result);
                    num1 = Math.sqrt(num1);
                    result = String.valueOf(num1);
                    output2.setText(String.valueOf(num1));
                }
                break;
            case "x^3" :
            case "x^2" :
            case "x!" :
                if (result != "" && !result.contains("!")) {
                    this.concatenate(buttonText.substring(1));
                }
                break;
        }

    }

    private void concatenate(String str) {
        result += str;
        TextView output = findViewById(R.id.outputField);
        output.setText(String.valueOf(result));
    }
}