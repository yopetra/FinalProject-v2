package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.jokesactivity.JokeActivity;

public class MainActivity extends AppCompatActivity implements AsyncResponseHandler{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void tellJoke(View view) {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.responseHandler = this;
        endpointsAsyncTask.execute(new Pair<Context, String>(this, "Manfred"));
    }

    @Override
    public void goodResult(String output) {
        if(output != null){
            Intent intent = new Intent(this, JokeActivity.class);
            intent.putExtra("jokeItem", output);
            startActivity(intent);

            Toast.makeText(this, "This is a joke", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void badResult(Exception exception) {
        Toast.makeText(this, "Error = " + exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

}