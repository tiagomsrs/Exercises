package com.example.emailprocessortext;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkList extends Service {

    public static void removingDup (LinkedList<String> list){
        int firstIndex, lastIndex, i = 0;
        int size = list.size();

        while (i < size) {
            firstIndex = list.indexOf(list.get(i));
            lastIndex = list.lastIndexOf(list.get(i));

            while (firstIndex != lastIndex) {
                list.removeLastOccurrence(list.get(i));
                lastIndex = list.lastIndexOf(list.get(i));
                size -= 1;
            }
            i += 1;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flaghs, int startId) {
        Toast.makeText(getApplicationContext(), "Foiii", Toast.LENGTH_LONG).show();

        ArrayList<String> aList = new ArrayList<String>();
        aList.addAll((ArrayList)intent.getExtras().get("key"));

        LinkedList<String> object = new LinkedList<String>();
        object.addAll(aList);

        removingDup(object);

        return 0;
    }
}

