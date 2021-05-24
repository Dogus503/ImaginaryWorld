package com.example.imaginaryworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    public DataBase dt = new DataBase(this);
    static final int GALLERY_REQUEST = 1;
    Button btn, save, open, butt;
    public static ArrayList<ImageMy> images = new ArrayList<>();
    ArrayList<String> array = new ArrayList<>();
    EditText editText;
    Filters filters;
    ImageView view;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.newfile);
        view = findViewById(R.id.imageView);
        save = findViewById(R.id.SaveFile);
        seekBar = findViewById(R.id.seekBar);
        open = findViewById(R.id.openfile);
        butt = findViewById(R.id.button);
        editText = findViewById(R.id.EditText);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filters.blackOrWhite();
            }
        });
        seekBar.setOnSeekBarChangeListener(this);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(editText.getText().toString());
            }
        });
    }
    public void showImage(){
        SQLiteDatabase database = dt.getWritableDatabase();
        String[] columns = {DataBase.KEY_ID, DataBase.KEY_NAME};
        Cursor cursor = database.query(DataBase.TABLE_CONTACTS, columns, null,
                null, null, null, null);
        int idColumnIndex = cursor.getColumnIndex(DataBase.KEY_ID);
        int nameColumnIndex = cursor.getColumnIndex(DataBase.KEY_NAME);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                array.add(currentName);
            }
        }
        Intent intent = new Intent(this, NewFil.class);
        startActivity(intent);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;

        if (requestCode == GALLERY_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = imageReturnedIntent.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.setImageBitmap(bitmap);
                filters = new Filters(view);
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        filters.bright(seekBar.getProgress());
    }

    private static class adapterImage extends ArrayAdapter<ImageMy>{


        public adapterImage(Context context) {
            super(context, R.layout.foradapter, images);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.foradapter, null);
            }
            ((TextView) convertView.findViewById(R.id.textView)).setText(images.get(position).string);
            ((ImageView) convertView.findViewById(R.id.image)).setImageDrawable(images.get(position).imageView.getDrawable());
            return convertView;
        }
    }
    public void saveImage(String name){
        SQLiteDatabase database = dt.getWritableDatabase();
        String[] columns = {DataBase.KEY_ID, DataBase.KEY_NAME};
        images.add(new ImageMy(view, name));
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.KEY_NAME, name);
        database.insert(DataBase.TABLE_CONTACTS, null, contentValues);
    }
}