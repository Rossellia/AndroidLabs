package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String Name = "signature";

TextView textView;
String gameState;
String GAME_STATE_KEY="My Online Store";
String TEXT_VIEW_KEY="My Text View";
public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
String toSend;
public static final String mypreference = "mypref";
    String textmsg;
    static final int READ_BLOCK_SIZE = 100;
    StringBuilder s= new StringBuilder(100);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            gameState = savedInstanceState.getString(GAME_STATE_KEY);
        }


        setContentView(R.layout.activity_main);
        final ListView list = findViewById(R.id.list_view);
        ArrayList<DataStructure> arrayList2 = new ArrayList<>();
        arrayList2.add(new DataStructure("Apple","fruit","1","red"));
        arrayList2.add(new DataStructure("Potato", "vegetable", "2", "best for french fries"));
        arrayList2.add(new DataStructure("Orange", "fruit", "3", "kinda tropical"));
        arrayList2.add(new DataStructure("Carrot","vegetable", "4", "good for eye sight"));
        arrayList2.add(new DataStructure("Milk", "product", "5", "required for cereal"));
        arrayList2.add(new DataStructure("Corn Flakes", "product", "6", "best as they are or with milk"));

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Potato");
        arrayList.add("Orange");
        arrayList.add("Carrot");
        arrayList.add("Milk");
        arrayList.add("Corn Flakes");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) list.getItemAtPosition(position);
                toSend= (String) list.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,clickedItem,Toast.LENGTH_LONG).show();


            }

        });

        textView = findViewById(R.id.textView);
        textView.setText("Online Store"); //set text for text view
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedpreferences.contains(Name)) {
            textView.setText(sharedpreferences.getString(Name, "Online Store"));
        }
        else
        {
            textView.setText("Online Store"); //set text for text view
        }
        textmsg=arrayList.get(0).toString();
        s.append(textmsg);
        s.append(",");
        textmsg=arrayList.get(1).toString();
        s.append(textmsg);
        s.append(",");
        textmsg=arrayList.get(2).toString();
        s.append(textmsg);
        s.append(",");
        textmsg=arrayList.get(3).toString();
        s.append(textmsg);
        s.append(",");
        textmsg=arrayList.get(4).toString();
        s.append(textmsg);
        s.append(",");
        textmsg=arrayList.get(5).toString();
        s.append(textmsg);
    }
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(s.toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            TextView textView1=findViewById(R.id.textView1);
            textView1.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This callback is called only when there is a saved instance that is previously saved by using
// onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
// other state here, possibly usable after onStart() has completed.
// The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
        if (sharedpreferences.contains(Name)) {
            textView.setText(sharedpreferences.getString(Name, ""));
        }
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(GAME_STATE_KEY, gameState);
        savedInstanceState.putString(TEXT_VIEW_KEY, textView.getText().toString());

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        Log.d("My Application","Resuming");
    }
    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Log.d("My Application","Pause");
    }
    @Override
    public void onStop() {
        super.onStop();  // Always call the superclass method first
        Log.d("My Application","Stop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    /** Called when the user taps the Send button */
    public void sendMessage(MenuItem menuItem) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        String message = toSend;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void sendSettings(MenuItem menuItem) {
        Intent intent = new Intent(this, SettingsActivity.class);
        String message = toSend;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sendSensors(MenuItem menuItem) {
        Intent intent = new Intent(this, SensorsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.news:
                textView.setText("News");
                return true;
            case R.id.help:
                textView.setText("You requested help");
                return true;
            case R.id.orders:
                DialogBuild();
                return true;
            case R.id.top_products:
                return true;
            case R.id.settings:
                return true;
            case R.id.sensors:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void DialogBuild()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Your orders");
        builder.setMessage("Do you want to view your orders?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
