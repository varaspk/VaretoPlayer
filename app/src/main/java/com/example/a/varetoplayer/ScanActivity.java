package com.example.a.varetoplayer;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.example.a.varetoplayer.ScanAdaptor.RowObj;

public class ScanActivity extends ListActivity  {
    public String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);


        path = "/";
        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }
        setTitle(path);

        List<RowObj> values = new ArrayList<>();
        File dirFile = new File(path);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //Pedir permisos
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }


            String[] list = dirFile.list();
            if (list != null) {
                for (String file : list) {
                    File a = new File(dirFile + File.separator + file);
                    if (a.isDirectory()) {
                        values.add(new RowObj(file, R.drawable.ic_menu_camera , false));
                    }else {
                        values.add(new RowObj(file, R.drawable.ic_menu_slideshow, false));
                    }
                }
            }


        final ListView listView = (ListView) findViewById(android.R.id.list);

        final ListAdapter adapter = new ScanAdaptor(this,values);
        listView.setAdapter(adapter);

        FloatingActionButton floatButton = (FloatingActionButton)findViewById(R.id.fab);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        RowObj row = (RowObj)getListView().getItemAtPosition(position);

        if (row.str.endsWith(File.separator)) {
            row.str = path + row.str;
        } else {
            row.str = path + File.separator + row.str;
        }
        File file = new File(row.str);
        if (!file.isFile()) {
            Intent intent = new Intent(this, ScanActivity.class);
            intent.putExtra("path", row.str);
            startActivity(intent);
        } else {
            Toast.makeText(this, row.str + " is not a directory", Toast.LENGTH_LONG).show();
        }
    }
}
