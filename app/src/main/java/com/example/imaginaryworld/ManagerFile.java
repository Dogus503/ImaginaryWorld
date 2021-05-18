package com.example.imaginaryworld;

import java.io.File;
import java.util.ArrayList;

public class ManagerFile{
    public File currentFile, lastFile;
    public boolean isSave = false;
    public ArrayList <File> fileArray;
    //public boolean saveFile(File file, String name){
        //fileArray.add(file);
        //try{
         //   file.createNewFile();
        //}catch (Exception e){
            //e.printStackTrace();
        //}
    //}
    public void change(File file)
    {
        lastFile = currentFile;
        currentFile = file;
    }

}
