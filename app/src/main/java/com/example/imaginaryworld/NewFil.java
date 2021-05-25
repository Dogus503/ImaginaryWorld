package com.example.imaginaryworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;

public class NewFil extends AppCompatActivity {
    ListView listView;
    static boolean aBoolean = false;
    static int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fil);
        listView = findViewById(R.id.listView);
        adapterImage adapter = new adapterImage(this);
        listView.setAdapter(adapter);
        Log.d("Me", String.valueOf(MainActivity.images.toArray().length));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewFil.this, MainActivity.class);
                pos = position;
                aBoolean = true;
                startActivity(intent);
            }
        });
    }
    private static class adapterImage extends ArrayAdapter<ImageMy> {


        public adapterImage(Context context) {
            super(context, R.layout.foradapter, MainActivity.images);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.foradapter, null);
            }
            ((TextView) convertView.findViewById(R.id.textView)).setText(MainActivity.images.get(position).string);
            ((ImageView) convertView.findViewById(R.id.image)).setImageURI(MainActivity.images.get(position).imageView);
            Log.d("Me", "Monster");
            return convertView;
        }
    }
}