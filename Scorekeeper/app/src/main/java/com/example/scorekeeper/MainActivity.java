package com.example.scorekeeper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";
    private int mScore1;
    private int mScore2;
    private TextView mScoreTxt1;
    private TextView mScoreTxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreTxt1 = findViewById(R.id.score_1);
        mScoreTxt2 = findViewById(R.id.score_2);

        //If savedInstanceState bundle is not null,
        //Means that there's already a saved data,
        //Get that int data from the bundle
        //And assign it to score variables
        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            mScoreTxt1.setText(String.valueOf(mScore1));
            mScoreTxt2.setText(String.valueOf(mScore2));
        }

    }

    public void changeScore(View view) {
        //Change score according to the button that the user clicked
        switch (view.getId()) {
            case R.id.increase_1:
                mScore1++;
                mScoreTxt1.setText(String.valueOf(mScore1));
                break;
            case R.id.increase_2:
                mScore2++;
                mScoreTxt2.setText(String.valueOf(mScore2));
                break;
            case R.id.decrease_1:
                mScore1--;
                mScoreTxt1.setText(String.valueOf(mScore1));
                break;
            case R.id.decrease_2:
                mScore2--;
                mScoreTxt2.setText(String.valueOf(mScore2));
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreate the activity for the theme change to take effect.
            recreate();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the scores.
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }
}
