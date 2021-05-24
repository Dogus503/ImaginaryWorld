package com.example.imaginaryworld;

import android.graphics.Bitmap;
import android.graphics.Color;import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.ImageView;

public class Filters {
    ImageView imageView;
    Bitmap bmp;
    public Filters(ImageView image){
        imageView = image;
        BitmapDrawable abmp = (BitmapDrawable) imageView.getDrawable();
        bmp = abmp.getBitmap();
    }

    public void blackOrWhite(){
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
    public void dark(){

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
        imageView.setImageBitmap(bmp);
    }
    public void bright(int plus){
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
                }
                g = plus  + g;
                if(g > 255){
                    g = 255;
                }
                b = plus  + b;
                if (b > 255){
                    b = 255;
                }
                alpha = plus + alpha;
                if (alpha > 200){
                    alpha = 200;
                }
                bmper.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        imageView.setImageBitmap(bmper);
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
