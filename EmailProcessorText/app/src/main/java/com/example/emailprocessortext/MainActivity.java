package com.example.emailprocessortext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.btn = findViewById(R.id.btn);
        this.mViewHolder.btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                sendLL();
                break;
            default:
                break;
        }
    }

    private void sendLL() {

        LinkedList<String> object = new LinkedList<String>();

        object.add("E");
        object.add("B");
        object.addFirst("A");
        object.add("D");
        object.add("A");
        object.add("F");
        object.add("B");
        object.add("B");
        object.add("E");
        object.add("D");
        object.add("C");
        object.addLast("D");
        object.add("A");
        object.add("J");
        object.add("K");
        object.add("C");
        object.add("C");
        object.add("C");
        object.add("C");
        object.add("C");


         Intent intent = new Intent(this, LinkList.class);
         intent.putExtra("key", object);
         startService(intent);
    }

    private static class ViewHolder {
        Button btn;
    }
}
