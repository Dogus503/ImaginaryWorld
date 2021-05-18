package com.example.imaginaryworld;

import android.graphics.Bitmap;
import android.graphics.Color;import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.ImageView;

public class Filters {
    public void blackOrWhite(ImageView image){
        BitmapDrawable drawable = (BitmapDrawable)image.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        float width = bmp.getWidth();
        float height = bmp.getHeight();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int pixel = bmp.getPixel(i, j);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b =  Color.blue(pixel);
                if ((r + g + b) / 3 > 255){
                    bmp.setPixel(i, j, Color.BLACK);
                }
                else{
                    bmp.setPixel(i, j, Color.WHITE);
                }
            }

        }
        image.setImageBitmap(bmp);
    }
    public void dark(ImageView im){
        BitmapDrawable drawable = (BitmapDrawable)im.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        for(int i = 0; i < bmp.getWidth(); i++){
            for(int j = 0; j < bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  r - 100;
                g =  g - 100;
                b =  b - 100;
                alpha = alpha - 50;
                bmp.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        im.setImageBitmap(bmp);
    }
    public void bright(ImageView imageView){
        BitmapDrawable drawable = (BitmapDrawable)imageView.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        for(int i = 0; i < bmp.getWidth(); i++){
            for(int j = 0; j < bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = 100  +  r;
                g = 100  + g;
                b = 100  + b;
                alpha = 50 + alpha;
                bmp.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        imageView.setImageBitmap(bmp);
    }
    public void alphaMinus(ImageView imageView){
        BitmapDrawable drawable = (BitmapDrawable)imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        for(int i = 0; i < bitmap.getWidth(); i++){
            for(int j = 0; j < bitmap.getHeight(); i++){
                int p = bitmap.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b  = Color.blue(p);
                int alpha = Color.alpha(p);


                alpha -= 100;
                bitmap.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
    }
}
