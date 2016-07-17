package com.mouse.kevin.idchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, int[]> charToInt = new HashMap();
    private int[] individual_multiplier = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateCharToIntMap();

        final Button btn_check = (Button) findViewById(R.id.btn_check);
        final Button btn_clear = (Button) findViewById(R.id.btn_clear);
        final EditText et_ID = (EditText) findViewById(R.id.input_ID);

        et_ID.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});

        btn_check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String output = checkInput(et_ID.getText().toString());
                et_ID.setText(output);
                et_ID.setSelection(output.length());
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et_ID.setText("");
            }
        });
    }

    private void CreateCharToIntMap() {
        charToInt.put("A", new int[]{1, 0}); //臺北市
        charToInt.put("B", new int[]{1, 1}); //臺中市
        charToInt.put("C", new int[]{1, 2}); //基隆市
        charToInt.put("D", new int[]{1, 3}); //臺南市
        charToInt.put("E", new int[]{1, 4}); //高雄市
        charToInt.put("F", new int[]{1, 5}); //新北市
        charToInt.put("G", new int[]{1, 6}); //宜蘭縣
        charToInt.put("H", new int[]{1, 7}); //桃園市
        charToInt.put("J", new int[]{1, 8}); //新竹縣
        charToInt.put("K", new int[]{1, 9}); //苗栗縣
        charToInt.put("L", new int[]{2, 0}); //臺中縣
        charToInt.put("M", new int[]{2, 1}); //南投縣
        charToInt.put("N", new int[]{2, 2}); //彰化縣
        charToInt.put("P", new int[]{2, 3}); //雲林縣
        charToInt.put("Q", new int[]{2, 4}); //嘉義縣
        charToInt.put("R", new int[]{2, 5}); //臺南縣
        charToInt.put("S", new int[]{2, 6}); //高雄縣
        charToInt.put("T", new int[]{2, 7}); //屏東縣
        charToInt.put("U", new int[]{2, 8}); //花蓮縣
        charToInt.put("V", new int[]{2, 9}); //臺東縣
        charToInt.put("X", new int[]{3, 0}); //澎湖縣
        charToInt.put("Y", new int[]{3, 1}); //陽明山管理局
        charToInt.put("W", new int[]{3, 2}); //金門縣
        charToInt.put("Z", new int[]{3, 3}); //連江縣
        charToInt.put("I", new int[]{3, 4}); //嘉義市
        charToInt.put("O", new int[]{3, 5}); //新竹市
    }


    private String checkInput(String s) {
        // Individual ID
        if (s.matches("^[A-Z]\\d{8,9}$")) {
            int[] code = charToInt.get(s.substring(0, 1));
            int sum;
            sum = individual_multiplier[0] * code[0] +
                    individual_multiplier[1] * code[1] +
                    individual_multiplier[2] * Integer.parseInt(s.substring(1, 2)) +
                    individual_multiplier[3] * Integer.parseInt(s.substring(2, 3)) +
                    individual_multiplier[4] * Integer.parseInt(s.substring(3, 4)) +
                    individual_multiplier[5] * Integer.parseInt(s.substring(4, 5)) +
                    individual_multiplier[6] * Integer.parseInt(s.substring(5, 6)) +
                    individual_multiplier[7] * Integer.parseInt(s.substring(6, 7)) +
                    individual_multiplier[8] * Integer.parseInt(s.substring(7, 8)) +
                    individual_multiplier[9] * Integer.parseInt(s.substring(8, 9));
            s = s.substring(0, 9) + String.valueOf((10 - (sum % 10)));
        }
        // Corporate ID
        else if (s.matches("^\\d{7,8}$")) {

        } else {
            Toast.makeText(this, R.string.msg_ID_invalid, Toast.LENGTH_SHORT).show();
        }
        return s;
    }
}
