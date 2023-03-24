package com.example.third;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.third.fragment.LogInFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.log_in_fragment, LogInFragment.class, null)
                    .commit();
        }
    }
}
