package com.destack.decision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.destack.decision.ui.fragments.ChoiceFragment;
import com.destack.decision.ui.fragments.YesNoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch(menuItem.getItemId()) {
                    case R.id.yesNoMenuItem:
                        fragment = new YesNoFragment();
                        break;
                    case R.id.choiceMenuItem:
                        fragment = new ChoiceFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        };
}
