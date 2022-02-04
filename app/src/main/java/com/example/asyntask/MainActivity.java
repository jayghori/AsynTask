package com.example.asyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    Button button;
    Switch aSwitch;
    ProgressBar progressBar, progressIndicater;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        progressIndicater = findViewById(R.id.progressIndicater);
        aSwitch = findViewById(R.id.switch1);


        textView.setText("");
        progressIndicater.setVisibility(View.INVISIBLE);


    }

    public void uploadTask(View view) {

        new UploadTask().execute("my name is jay");


    }

    class UploadTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute: " + Thread.currentThread().getName());

            textView.setText("uploaded data...");
            progressIndicater.setVisibility(View.VISIBLE);
            button.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i(TAG, "doInBackground: string: " + strings[0]);
            Log.i(TAG, "doInBackground: Thread :" + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return "Finally this task was Complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            progressBar.setProgress(values[0] + 1);
            Log.i(TAG, "onProgressUpdate: "+Thread.currentThread().getName());

        }

        @Override
        protected void onPostExecute(String string) {
            Log.i(TAG, "onPostExecute: " + Thread.currentThread().getName());
            textView.setText(string);
            progressIndicater.setVisibility(View.INVISIBLE);
            button.setEnabled(true);
        }
    }
}