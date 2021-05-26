package com.example.imaginaryworld;

import android.graphics.Bitmap;
import android.graphics.Color;import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.ImageView;

public class Filters {
    ImageView imageView;
    Bitmap bmp;
    boolean falser = false;
    public Filters(ImageView image){
        imageView = image;
        BitmapDrawable abmp = (BitmapDrawable) imageView.getDrawable();
        bmp = abmp.getBitmap();
        falser = true;
    }
    public void contrast(int value){
        if(falser){
        Bitmap bmper = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        double cont_con = Math.pow((float)(100 + value * 2) / 100, 2);
        for(int i = 0; i < bmp.getWidth(); i++){
            for(int j = 0; j < bmp.getHeight(); j++){
                int pixel = bmp.getPixel(i, j);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                r = (int)(((((r / 255.0) - 0.5) * cont_con) + 0.5) * 255.0);
                if(r < 0) { r = 0; }
                else if(r > 255) { r = 255; }
                int g = Color.green(pixel);
                g = (int)(((((g / 255.0) - 0.5) * cont_con) + 0.5) * 255.0);
                if(g < 0) { g = 0; }
                else if(g > 255) { g = 255; }
                int b = Color.blue(pixel);
                b = (int)(((((b / 255.0) - 0.5) * cont_con) + 0.5) * 255.0);
                if(b < 0) { b = 0; }
                else if(b > 255) { b = 255; }
                bmper.setPixel(i, j, Color.argb(a, r, g, b));
            }
        }
        imageView.setImageBitmap(bmper);}
    }


    public void blackOrWhite(){
        if (falser){
            Bitmap bmper = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());
            float width = bmp.getWidth();
            float height = bmp.getHeight();
            for(int i = 0; i < width; i++){
                for(int j = 0; j < height; j++){
                    int pixel = bmp.getPixel(i, j);
                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b =  Color.blue(pixel);
                    int value = (r + g + b) / 3;
                    bmper.setPixel(i ,j, Color.argb(Color.alpha(pixel), value, value, value));
                    }
                }
            imageView.setImageBitmap(bmper);
        }
    }
    public void bright(int plus){
        if(falser){
        Bitmap bmper = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());
        for(int i = 0; i < bmp.getWidth(); i++){
            for(int j = 0; j < bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = plus +  r;
                if(r > 255){
                    r = 255;
                }else if (r < 0){
                    r = 0;
                }
                g = plus  + g;
                if(g > 255){
                    g = 255;
                }else if(g < 0){
                    g = 0;
                }
                b = plus  + b;
                if (b > 255){
                    b = 255;
                }else if(b < 0){
                    b = 0;
                }
                alpha = plus + alpha;
                if (alpha > 200){
                    alpha = 200;
                }
                else if(alpha < 50){
                    alpha = 50;
                }
                bmper.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        imageView.setImageBitmap(bmper);}
    }
    public void alphaMinus(ImageView imageView){
        if(falser){
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
                bitmap.setPixel(i, j, Color.argb(alpha, r, g, b));}
            }
        }
    }
}
