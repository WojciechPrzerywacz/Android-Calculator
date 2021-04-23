package com.example.calculator_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.*;

public class SimpleCalcActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calc);

        display = findViewById(R.id.myDisplay);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });

    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        //sprawdzanie wejścia
        if(leftStr.endsWith("s") || leftStr.endsWith("i") || leftStr.endsWith("n") || rightStr.endsWith("s") || rightStr.startsWith("i") || rightStr.startsWith("n")){
            return;
        }
        if (strToAdd.equals(".") && (rightStr.startsWith(".") || leftStr.endsWith("."))){
            return;
        }
        //
        if (getString(R.string.display).equals(display.getText().toString())){
           display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s", leftStr,strToAdd,rightStr));
        }
        display.setSelection(cursorPos+1);
    }

    public void zeroBTN(View view){
        updateText("0");
    }

    public void oneBTN(View view){
        updateText("1");
    }

    public void twoBTN(View view){
        updateText("2");
    }

    public void threeBTN(View view){
        updateText("3");
    }
    public void fourBTN(View view){
        updateText("4");
    }
    public void fiveBTN(View view){
        updateText("5");
    }
    public void sixBTN(View view){
        updateText("6");
    }
    public void sevenBTN(View view){
        updateText("7");
    }
    public void eightBTN(View view){
        updateText("8");
    }
    public void nineBTN(View view){
        updateText("9");
    }
    public void exponentBTN(View view){
        updateText("^");
    }
    public void parenthesesBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i=0;i<cursorPos;i++){
            if (display.getText().toString().substring(i,i+1).equals("(")){
                openPar += 1;
            }
            if (display.getText().toString().substring(i,i+1).equals(")")){
                closedPar += 1;
            }
        }
        if (openPar==closedPar || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        } else if (closedPar<openPar && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }
    public void backBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

    public void clearBTN(View view){
        display.setText("");
    }
    public void divideBTN(View view){
        updateText("/");
    }
    public void multiplyBTN(View view){
        updateText("x");
    }
    public void addBTN(View view){
        updateText("+");
    }
    public void subtractBTN(View view){
        updateText("-");
    }
    public void equalsBTN(View view){
        String expression = display.getText().toString();
        expression = expression.replaceAll("x", "*");

        Expression exp = new Expression(expression);
        String result = String.valueOf(exp.calculate());

        //sprawdzanie wyrażenia
        String currentString = display.getText().toString();
        if (currentString.contains("/0")){
            result="Dzielenie przez zero";
        }else if (result.equals("NaN")){
            result="Błędne wyrażenie";
        }
        display.setText(result);
        display.setSelection(result.length());
    }
    public void pointBTN(View view){
        int cursorPos = display.getSelectionStart();
        updateText(".");
    }
}