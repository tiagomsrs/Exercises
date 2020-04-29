package com.example.searchlist;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.searchlist.util.SecurityPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayAdapter<String> itensAdaptador;
    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    public static final String keyList = "key";
    String[] inputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.inputText = findViewById(R.id.txt_input);
        this.mViewHolder.btnSearch = findViewById(R.id.btn_search);
        this.mViewHolder.btnAdd = findViewById(R.id.btn_add);
        this.mViewHolder.list = findViewById(R.id.list);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.btnAdd.setOnClickListener(this);
        this.mViewHolder.inputText.setOnClickListener(this);
        this.mViewHolder.btnSearch.setOnClickListener(this);

        loadList();

    }

    private void loadList() {
        String itemList = this.mSecurityPreferences.getStoredString(keyList);
        inputString = itemList.split(",");
        itensAdaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, inputString);
        this.mViewHolder.list.setAdapter(itensAdaptador);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_input:
                this.mViewHolder.inputText.setText("");
                break;
            case R.id.btn_add:
                addOnList();
                break;
            case R.id.btn_search:
                searchOnList();
                break;
            default:
                break;
        }
    }

    private void addOnList() {
        String input = this.mViewHolder.inputText.getText().toString();
        String itemList = this.mSecurityPreferences.getStoredString(keyList);

        if(this.mViewHolder.inputText .equals("")){
            Toast.makeText(MainActivity.this, "Insira um item", Toast.LENGTH_SHORT).show();
        }else {
            this.mSecurityPreferences.storeString(keyList, input +  "," + itemList);
            Toast.makeText(MainActivity.this, "Item " + input + " inserido!", Toast.LENGTH_SHORT).show();
            this.mViewHolder.inputText.setText(R.string.enter_a_word);
            loadList();
        }
    }

    private void searchOnList() {
        int len, l1, l2;
        boolean mJumbled = false, mTypo = false;

        String input = this.mViewHolder.inputText.getText().toString();
        String itemList = this.mSecurityPreferences.getStoredString(keyList);
        inputString = itemList.split(",");
        len = inputString.length;
        l1 = input.length();

        for (int i = 0; i < len; i++){

            l2 = inputString[i].length();
            if (l1 == l2) {
                mJumbled = jumbled(input, inputString[i]);
            }

            mTypo = typo_check(input, inputString[i]);

            if ( mJumbled ^ mTypo){
                Toast.makeText(MainActivity.this, "Most similar word: " + inputString[i], Toast.LENGTH_LONG).show();
            }
        }

        if (!mJumbled && !mTypo){
            Toast.makeText(MainActivity.this, "Zero results! Please check input!", Toast.LENGTH_LONG).show();
        }else if (mJumbled && mTypo) {
            Toast.makeText(MainActivity.this, "This word it's correct, but it can include one or zero typos and has less than 2/3 jumbled letters! This exercise says \"but not both.\".", Toast.LENGTH_LONG).show();
        }
    }

    private boolean jumbled (String s1, String s2) {
    /*
    :param s1: Reference string
    :param s2: String to check
    :return: True, if possible to read
             False, if not possible to read
    */
        int diff = 0, i;


        if (s1.charAt(0) != s2.charAt(0)) {
            return false;
        }
        else if ((s1.length() == 2) && (s1.charAt(1) != s2.charAt(1))) {
            return false;
        }
        else {
            for (i = 1; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff += 1;
                }

                if (diff > (s1.length()) * (2 / 3)) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean typo_check (String s1, String s2) {
    /*
	:param s1: Reference string
	:param s2: String to check
	:return: True, if there is zero or only one typo away
			 False, if there are two or more typo away (it can be from the same typo type, according "pale, bake ­> false")
	*/
        int insert = 0, remove = 0, replace = 0, i = 0, l1, l2;
        boolean aux;

        l1 = s1.length();  // string 1 length
        l2 = s2.length();  // string 2 length

        if ((l1 >= l2 + 2) || (l2 >= l1 + 2)) {  // two typos
            return false;
        }
        else if (l1 == l2) {  // same length, just check replaced chars

            if (s1.equals(s2)) { // equals
                return true;
            }

            while (i < l1) {
                if (s1.charAt(i) == s2.charAt(i)) {
                    i += 1;
                }
                else {
                    i += 1;
                    if (replace < 1) {  // while exist only one typo
                        replace += 1;
                    }
                    else {
                        return false;  // two or more typo
                    }
                }
            }
        }
        else if (l1 ==l2 + 1 || l2 ==l1 + 1) {  // at least one typo (remotion or insertion)

            while (i < Math.min(l1,l2)) {

                String s2aux = s2.substring(i - remove);
                String s1aux = String.valueOf(s1.charAt(i));
                aux = s2aux.contains(s1aux);

                if(aux && (s1.charAt(i) == s2.charAt(i - remove))) {
                    i += 1;
                }
                else {
                    i += 1;
                    if (remove < 1) { // while exist only one typo
                        remove += 1;
                    }
                    else {
                        return false;  // two or more typo
                    }
                }
            }
            if (remove >= 1 && s1.charAt(l1 - 1) != s2.charAt(l2 - 1)) {
                return false;
            }
        }
        return true;
    }

    private static class ViewHolder {
        EditText inputText;
        Button btnSearch;
        Button btnAdd;
        ListView list;
    }
}
