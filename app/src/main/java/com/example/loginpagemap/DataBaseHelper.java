package com.example.loginpagemap;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
public static final String DBNAME="ExternalDatabase.db";
public static final String DBLOCATION="/data/data/com.example.loginpagemap/databases/";
private Context mcontext;
private SQLiteDatabase mdatabase;


    public DataBaseHelper(@Nullable Context context) {
        super(context,DBNAME,null,1);
        this.mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
   public void OpenDatabase()
   {
       String dbpath=mcontext.getDatabasePath(DBNAME).getPath();
       if (mdatabase!=null && mdatabase.isOpen())
       {
           return;
       }
       mdatabase=SQLiteDatabase.openDatabase(dbpath,null,SQLiteDatabase.OPEN_READWRITE);
   }

   public void CloseDatabase()
   {
       if (mdatabase!=null)
       {
           mdatabase.close();
       }
   }

   public List<Product> getListProduct()
   {
       Product product=null;
       List<Product> productList=new ArrayList<>();
       OpenDatabase();
       Cursor cursor= mdatabase.rawQuery("select r.RetailerName,r.RetailerID, ra.Address1,ra.ContactNumber,ra.Latitude,ra.Longitude,ra.pincode,ra.Phno1 from RetailerMaster as r Cross join  RetailerAddress as ra ON  r.RetailerId== ra.RetailerId",null);
       cursor.moveToFirst();
       while (!cursor.isAfterLast())
       {
           product=new Product(cursor.getString(0),cursor.getInt(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5) ,cursor.getString(6),cursor.getString(7));
       productList.add(product);
       cursor.moveToNext();
       }
       cursor.close();
       CloseDatabase();
       return productList;
   }
}



