package com.example.imaginaryworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    public DataBase dt = new DataBase(this);
    static final int GALLERY_REQUEST = 1;
    Button btn, save, open;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.newfile);
        save = findViewById(R.id.SaveFile);
        open = findViewById(R.id.openfile);
        listView = findViewById(R.id.listView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });

    }
    public void showImage(){
        SQLiteDatabase database = dt.getWritableDatabase();
        ArrayList<String> array = new ArrayList<>();
        String[] columns = {DataBase.KEY_ID, DataBase.KEY_NAME};
        @SuppressLint("Recycle") Cursor cursor = database.query(DataBase.TABLE_CONTACTS, columns, null,
                null, null, null, null);
        int idColumnIndex = cursor.getColumnIndex(DataBase.KEY_ID);
        int nameColumnIndex = cursor.getColumnIndex(DataBase.KEY_NAME);
        while(cursor.moveToNext()){
            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            array.add(currentName);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.adapterfor, array);
        listView.setAdapter(arrayAdapter);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (requestCode == GALLERY_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = imageReturnedIntent.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}