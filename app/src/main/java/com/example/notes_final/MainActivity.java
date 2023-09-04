package com.example.notes_final;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    ArrayList<String> l1=new ArrayList<>();
    ArrayList<String> l2=new ArrayList<>();;
    ArrayList<String> l3=new ArrayList<>();;
    ArrayList<String> l4=new ArrayList<>();;
    ArrayList<String> l5=new ArrayList<>();
    boolean locked=false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson=new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        toolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Perform your action here when the Toolbar is long-pressed
                showToast("Toolbar long-pressed");
                generate(l1,l2,l3,l4,l5);
                locked=false;
                return true; // Return true to indicate that the long click is consumed
            }
        });
        locked=sharedPreferences.getBoolean("lock",false);
        String json1 = sharedPreferences.getString("list1", "");
        String json2 = sharedPreferences.getString("list2", "");
        String json3 = sharedPreferences.getString("list3", "");
        String json4 = sharedPreferences.getString("list4", "");
        String json5 = sharedPreferences.getString("list5", "");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        if(json1=="")
        {
            generate(l1,l2,l3,l4,l5);
        }
        else
        {
            l1 = gson.fromJson(json1, type);
            l2 = gson.fromJson(json2, type);
            l3 = gson.fromJson(json3, type);
            l4 = gson.fromJson(json4, type);
            l5 = gson.fromJson(json5, type);
        }

        // Sample data
        final String[] titles = {"Title 1", "Title 2", "Title 3", "Title 4", "Title 5"};
        ArrayList<ArrayList<String>> content=new ArrayList<>();
        content.add(l1);
        content.add(l2);
        content.add(l3);
        content.add(l4);
        content.add(l5);


        // Initialize the ListView
        ListView listView = findViewById(R.id.listView);

        // Create an ArrayAdapter and set it to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.titleTextView, titles);
        listView.setAdapter(adapter);

        // Set an item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click here
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Calendar calendar = Calendar.getInstance();
                Date currentTime = calendar.getTime();

                // Format the current time as a string
                SimpleDateFormat dateFormat = new SimpleDateFormat("ss");
                String currentTimeString = dateFormat.format(currentTime);

                // Create an intent to start the second activity
                intent.putExtra("current_time", currentTimeString);
                int pos= Integer.parseInt(String.valueOf(currentTimeString));
                if(!locked)
                {

                    Log.i("gandu2", String.valueOf(position));
                    switch(position)
                    {
                        case 0:
                            l1.set(50+pos-1,"Crazy");
                            String List1 = gson.toJson(l1);
                            editor.putString("list1", List1);
                            break;
                        case 1:
                            l2.set(50+pos-1,"Crazy");
                            String List2 = gson.toJson(l2);
                            editor.putString("list2", List2);
                            break;
                        case 2:
                            l3.set(50+pos-1,"Crazy");
                            String List3 = gson.toJson(l3);
                            editor.putString("list1", List3);
                            break;
                        case 3:
                            l4.set(50+pos-1,"Crazy");
                            String List4 = gson.toJson(l4);
                            editor.putString("list5", List4);
                            break;
                        case 4:
                            l5.set(50+pos-1,"Crazy");
                            String List5 = gson.toJson(l5);
                            editor.putString("list5", List5);
                            break;

                    }
                    locked=true;
                    editor.putBoolean("lock",true);
                }
                String selectedTitle = titles[position];
                ArrayList<String> selectedList= content.get(position);

                // Start DetailActivity with the selected title (you can pass any necessary data)




                intent.putExtra("selected_title", selectedTitle);
                intent.putStringArrayListExtra("custom_texts",selectedList);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void generate(ArrayList<String> l1, ArrayList<String> l2, ArrayList<String> l3, ArrayList<String> l4, ArrayList<String> l5) {
        l1.clear();
        l2.clear();
        l3.clear();
        l4.clear();
        l5.clear();
        for (int i = 0; i < 100; i++) {
            l1.add("Shaheen");
            l2.add("Messi");
            l3.add("Sehwag");
            l4.add("Salman");
            l5.add("Tata");
        }
        String List1 = gson.toJson(l1);
        String List2 = gson.toJson(l2);
        String List3 = gson.toJson(l3);
        String List4 = gson.toJson(l4);
        String List5 = gson.toJson(l5);
        editor.putString("list1", List1);
        editor.putString("list2", List2);
        editor.putString("list3", List3);
        editor.putString("list4", List4);
        editor.putString("list5", List5);
        editor.apply();

    }
}
