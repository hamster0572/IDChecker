package com.mouse.kevin.idchecker;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final HashMap<String, int[]> charToInt = new HashMap();
    private final int[] individual_multiplier = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};
    private final int[] corporate_multiplier = {1, 2, 1, 2, 1, 2, 4, 1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateCharToIntMap();

//        final Button btn_check = (Button) findViewById(R.id.btn_check);
        final Button btn_clear = (Button) findViewById(R.id.btn_clear);
        final EditText et_ID = (EditText) findViewById(R.id.input_ID);
        final TextView tv_msg = (TextView) findViewById(R.id.text_msg);
        et_ID.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);


        et_ID.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});

//        btn_check.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                tv_msg.setText((checkID(et_ID.getText().toString().toUpperCase())) ? R.string.msg_ID_valid : R.string.msg_ID_invalid);
//                et_ID.setSelection(et_ID.getText().length());
//            }
//        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et_ID.setText("");
                tv_msg.setText("");
            }
        });

        et_ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et_ID.clearComposingText();
                tv_msg.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int strLength = String.valueOf(editable).length();
                String inputID = et_ID.getText().toString().toUpperCase();
                if (strLength > 0) {
                    if ((strLength == 8 || strLength == 10) && (checkID(inputID))) {
                        tv_msg.setText(R.string.msg_ID_valid);
                        et_ID.setTextColor(Color.BLUE);
                    } else {
                        tv_msg.setText(R.string.msg_ID_invalid);
                        et_ID.setTextColor(Color.RED);
                    }
                }
                else{
                    et_ID.setTextColor(Color.BLACK);
                }
                et_ID.setSelection(et_ID.getText().length());
            }
        });
    }

    private boolean checkID(String inputID) {
        boolean result = false;
        // Individual ID
        if (inputID.matches("^[A-Z][1-2]\\d{8}$")) {
            result = checkPid(inputID);
        }
        // Corporate ID
        else if (inputID.matches("^\\d{8}$")) {
            result = checkCid(inputID);
        }
        return result;
    }

    private boolean checkPid(String pid) {
        int[] code = charToInt.get(pid.substring(0, 1));
        int sum = individual_multiplier[0] * code[0] +
                individual_multiplier[1] * code[1] +
                individual_multiplier[2] * Character.getNumericValue(pid.charAt(1)) +
                individual_multiplier[3] * Character.getNumericValue(pid.charAt(2)) +
                individual_multiplier[4] * Character.getNumericValue(pid.charAt(3)) +
                individual_multiplier[5] * Character.getNumericValue(pid.charAt(4)) +
                individual_multiplier[6] * Character.getNumericValue(pid.charAt(5)) +
                individual_multiplier[7] * Character.getNumericValue(pid.charAt(6)) +
                individual_multiplier[8] * Character.getNumericValue(pid.charAt(7)) +
                individual_multiplier[9] * Character.getNumericValue(pid.charAt(8)) +
                individual_multiplier[10] * Character.getNumericValue(pid.charAt(9));
        return (sum % 10 == 0);
    }

    private boolean checkCid(String cid) {
        int sum = 0;
        boolean type2 = ((cid.charAt(6) == '7'));
        for (int i = 0; i < cid.length(); i++) {
            int tmp = Character.getNumericValue(cid.charAt(i)) * corporate_multiplier[i];
            sum += (tmp / 10) + (tmp % 10);
        }
        if (!type2) {
            return (sum % 10 == 0);
        } else {
            return ((sum % 10 == 0) || (sum + 1) % 10 == 0);
        }
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
}
