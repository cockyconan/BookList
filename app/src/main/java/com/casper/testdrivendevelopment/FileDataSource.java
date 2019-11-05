package com.casper.testdrivendevelopment;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by chen on 2019/10/14.
 */

public class FileDataSource {
    private Context context;

    public FileDataSource(Context context) {
        this.context = context;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    private ArrayList<Book> books= new ArrayList<Book>();
    public void save()
    {

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(context.openFileOutput("Serializable.txt", Context.MODE_PRIVATE));//private是删除以前的，重新写,append是追加
            outputStream.writeObject(books);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//其实是语法上有瑕疵，但是这里不深纠，直接catch
    }
    public ArrayList<Book> load() {

        try {
            ObjectInputStream inputStream = new ObjectInputStream(context.openFileInput("Serializable.txt"));
            books = (ArrayList<Book>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
