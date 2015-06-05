package com.example.marlonvilorio.multi_threadingprogram;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainPage extends ActionBarActivity {

    public void createOnClick(View v) {

        Handler handler = new Handler();
        final ProgressBar p = (ProgressBar) findViewById(R.id.progressbar);
        String filename = "numbers.txt";
        FileOutputStream outputStream;

        p.setProgress(0);
        StringBuilder sb = new StringBuilder();

        for( int i = 1; i < 11;i++) {

            sb.append(i).append("\n");
                    p.setProgress(10 * i);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            try {

                File file = new File(this.getFilesDir(), filename);
                outputStream = new FileOutputStream(file);
                outputStream.write(sb.toString().getBytes());
                outputStream.close();


           } catch (Exception e) {
                e.printStackTrace();
            }




    }

    public void loadOnClick(View v) {

        ListView myListView = (ListView) findViewById(R.id.listview);
        ProgressBar p = (ProgressBar) findViewById(R.id.progressbar);

       ArrayList<String> array = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(new File(this.getFilesDir(), "numbers.txt"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                array.add(line);
            }
            fis.close();
        } catch(OutOfMemoryError om){
            om.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }

        ArrayAdapter<String> myArrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        myListView.setAdapter(myArrayAdapter);

    }

    public void clearOnClick(View v) {
        ListView myListView = (ListView) findViewById(R.id.listview);
        ProgressBar p = (ProgressBar) findViewById(R.id.progressbar);
        p.setProgress(0);
        myListView.setAdapter(null);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ProgressBar p = (ProgressBar) findViewById(R.id.progressbar);
        p.setProgress(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
