package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InternalStorage extends AppCompatActivity implements View.OnClickListener {
    EditText text;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internalstorage);
        View v1 = findViewById(R.id.button3);
        v1.setOnClickListener(this);
        text=(EditText)findViewById(R.id.editText1);
    }

    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream out=openFileOutput("my.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(out);
            outputWriter.write(text.getText().toString());
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
            FileInputStream fileIn=openFileInput("my.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String read=String.copyValueOf(inputBuffer,0,charRead);
                s = s + read;
            }
            InputRead.close();
            text.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View arg0) {
        if (arg0.getId() == R.id.button3) {
            //define a new Intent for the second Activity
            Intent intent = new Intent(this, MainActivity.class);

            //start the second Activity
            this.startActivity(intent);
        }
    }
}