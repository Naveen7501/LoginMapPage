package com.example.loginpagemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView txthomepage, txtresult;
    Button btnlogout;


    private static final String Shared_Pref_Name = "mypref";
    private static final String Key_Email = "email";
    private static final String Key_Pass = "name";
    private ListView lvproduct;
    private ListProductAdaptor Adaptor;
    private List<Product> mproductlist;
    private DataBaseHelper mdbhelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnlogout = findViewById(R.id.buttonlogout);
        sharedPreferences = getSharedPreferences(Shared_Pref_Name, MODE_PRIVATE);
        lvproduct=(ListView)findViewById(R.id.listview);
        mdbhelper=new DataBaseHelper(this);

        //check exist database
        File database=getApplicationContext().getDatabasePath(DataBaseHelper.DBNAME);
        if (database.exists()==false)
        {
            mdbhelper.getReadableDatabase();
            //copy db
            try {
                if(CopyDatabases(this))
                {
                    Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //get product list in db when db exist
        mproductlist=mdbhelper.getListProduct();
        //init adapter
        Adaptor=new ListProductAdaptor(this,mproductlist);
        //set adapter for listview
        lvproduct.setAdapter(Adaptor);
        lvproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Product product = (Product) lvproduct.getItemAtPosition(position);


                    Double latitude = Double.valueOf(product.getLatitude());
                    Double longitude = Double.valueOf(product.getLongitude());
                    String Address=String.valueOf(product.getAddress1());
                    Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                    intent.putExtra("Latitude", latitude);
                    intent.putExtra("Longitude", longitude);
                    intent.putExtra("Address",Address);
                    startActivity(intent);


            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                Toast.makeText(HomeActivity.this, "Log Out Sucessfully", Toast.LENGTH_SHORT).show();

            }
        });


    }

public boolean CopyDatabases(Context context) throws IOException {
    try {
        InputStream inputStream=context.getAssets().open(DataBaseHelper.DBNAME);
        String outfilename=DataBaseHelper.DBLOCATION+DataBaseHelper.DBNAME;
        OutputStream outputStream=new FileOutputStream(outfilename);
        byte[] buff=new byte[1024];
        int length=0;
        while ((length=inputStream.read(buff))>0)
        {
            outputStream.write(buff,0,length);
        }
        outputStream.flush();
        outputStream.close();
        Log.w("HomeActivity","DB copied");
        return true;

    }catch (Exception e)
    {
        e.printStackTrace();
    return false;
    }
}


}