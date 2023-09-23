package com.example.lab2_os;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView u_Input, u_Result;
    Button btn_Clear, percentage, btn_Divide, btn_Multiply, btn_minus, btn_plus, btn_equals, btn_null;
    Button btn_7, btn_8, btn_9, btn_6, btn_5, btn_4, btn_3, btn_2, btn_1, btn_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u_Result = findViewById(R.id.u_Result);
        u_Input = findViewById(R.id.u_Input);

        assingId(btn_Clear, R.id.btn_Clear);
        assingId(percentage, R.id.percentage);
        assingId(btn_Divide, R.id.btn_Divide);
        assingId(btn_Multiply, R.id.btn_Multiply);
        assingId(btn_minus, R.id.btn_minus);
        assingId(btn_plus, R.id.btn_plus);
        assingId(btn_equals, R.id.btn_equals);
        assingId(btn_null, R.id.btn_null);
        assingId(btn_7, R.id.btn_7);
        assingId(btn_8, R.id.btn_8);
        assingId(btn_9, R.id.btn_9);
        assingId(btn_6, R.id.btn_6);
        assingId(btn_5, R.id.btn_5);
        assingId(btn_4, R.id.btn_4);
        assingId(btn_3, R.id.btn_3);
        assingId(btn_2, R.id.btn_2);
        assingId(btn_1, R.id.btn_1);
        assingId(btn_dot, R.id.btn_dot);

    }

    void assingId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = u_Input.getText().toString();

        if (buttonText.equals("=")){
            u_Input.setText(u_Result.getText());
            return;
        }
        if ((buttonText.equals("C")) && (!u_Input.getText().toString().isEmpty())){
            if (u_Input.length() == 1){
                dataToCalculate = "";
                u_Input.setText("");
                u_Result.setText("0");
            }else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        }
        else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        if (!dataToCalculate.isEmpty()) {
            if (!dataToCalculate.equals("C")) {
                u_Input.setText(dataToCalculate);
            }
            String finalRes = getResult(dataToCalculate);
            if (!finalRes.equals("Err")){
                u_Result.setText(finalRes);
            }
        }
    }
    String getResult(String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalRes = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalRes.endsWith(".0")){
                finalRes = finalRes.replace(".0", "");
            }
            return finalRes;
        }catch (Exception e){
            return "Err";
        }
    }
}