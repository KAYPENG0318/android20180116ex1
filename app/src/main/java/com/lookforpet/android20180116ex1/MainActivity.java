package com.lookforpet.android20180116ex1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
    }
    public void clickGet(View v)
    {
        //getFilesDir取得可以存檔的位置
        String str = getFilesDir().getAbsolutePath();
        Log.d("FILE", str);
        String str1 = getCacheDir().getAbsolutePath();
        Log.d("FILE", str1);

    }
    public void clickSave(View v)
    {
        File f = new File(getFilesDir(),"myfile2.txt");
        try {
            FileWriter FWriter = new FileWriter(f);
            FWriter.write("Hello");
            FWriter.close();

            Toast.makeText(MainActivity.this,"已儲存",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clickGson(View v)
    {
        ArrayList<String> mylist = new ArrayList();
        mylist.add("Ruby");
        mylist.add("Kay");
        mylist.add("WooJin");
        File f = new File(getFilesDir(),"myfileName.txt");
        try {
            FileWriter fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
            Toast.makeText(MainActivity.this,"已儲存",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clickGetJson(View v)
    {

        File f = new File(getFilesDir(),"myfileName.txt");
        try {
            FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
        Log.d("FILE",str);
        Gson gson = new Gson();
        ArrayList<String> mydata = gson.fromJson(str,new TypeToken<ArrayList<String>>(){}.getType());
       for(String s: mydata)
       {
           Log.d("FILE","data:"+s);
       }
       br.close();
       fr.close();
            Toast.makeText(MainActivity.this,"已讀取",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void clickGson2(View v)
    {
        ArrayList<Student> mydata = new ArrayList();
        mydata.add(new Student(1,"Kay",80));
        mydata.add(new Student(2,"WooJin",90));
        mydata.add(new Student(3,"JiHOO",100));

        File f = new File(getFilesDir(),"myfileName2.txt");
        try {
            FileWriter fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mydata);
            fw.write(data);
            fw.close();
            Toast.makeText(MainActivity.this,"已儲存",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class Student
    {
        public int id;
        public String name;
        public int score;
        public Student(int id,String name, int score)
        {
            this.id=id;
            this.name = name;
            this.score = score;
        }
    }
    public void clickGetJson2(View v)
    {
        File f = new File(getFilesDir(),"myfileName2.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Log.d("FILE",str);
            Gson gson = new Gson();
            ArrayList<Student> mydata = gson.fromJson(str,new TypeToken<ArrayList<Student>>(){}.getType());
            for(Student s: mydata)
            {
                Log.d("FILE","data:" +s.id + "," + s.name+","+s.score);
            }
            br.close();
            fr.close();
            Toast.makeText(MainActivity.this,"已讀取",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //textView儲存
    public void click(View v)
    {
        ArrayList<Student> mydata = new ArrayList();
        int i=Integer.valueOf(tv.getText().toString());
        //int i2=Integer.valueOf(tv2.getText().toString());
        int i3=Integer.valueOf(tv3.getText().toString());
        mydata.add(new Student(i,tv2.getText().toString(),i3));

        File f = new File(getFilesDir(),"click.txt");
        try {
            FileWriter fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mydata);
            fw.write(data);
            fw.close();
            Toast.makeText(MainActivity.this,"已儲存",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //getExternalCacheDir取得外部記憶體空間
    public void clickOutGet(View v)
    {
        File f = getExternalFilesDir("data");
        Log.d("FILE",f.getAbsolutePath());
    }
    public void click6(View v)
    {
        File f= Environment.getExternalStorageDirectory();
        Log.d("FILE",f.getAbsolutePath());
    }




}
