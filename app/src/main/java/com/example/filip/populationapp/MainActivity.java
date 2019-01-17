package com.example.filip.populationapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://api.population.io:80/1.0/population/1980/Brazil/18/")
                                .build();
                        Response response = null;

                        try{
                            response = client.newCall(request).execute();
                            return response.body().string();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        textView.setText(o.toString());
                    }
                }.execute();
            }
        });

    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, SearchByCountryActivity.class);
        //EditText editText = (EditText) findViewById();
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, "Ahoj");
        startActivity(intent);
    }

}
