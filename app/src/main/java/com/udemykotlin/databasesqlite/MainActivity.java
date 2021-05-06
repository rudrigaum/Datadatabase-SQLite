package com.udemykotlin.databasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = openOrCreateDatabase("app", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS people (name VARCHAR, age INT(3) ) ");

            myDatabase.execSQL("INSERT INTO people(name, age ) VALUES ('Rodrigo', 30) ");
            myDatabase.execSQL("INSERT INTO people(name, age ) VALUES ('Rudrigo', 51) ");

            Cursor cursor = myDatabase.rawQuery("SELECT name, age FROM people", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            while (cursor != null ){
                Log.i("Result - name: ", cursor.getString(nameIndex));
                Log.i("Result - age: ", cursor.getString(ageIndex));
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}