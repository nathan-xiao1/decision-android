package com.destack.decision;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.destack.decision.ui.fragments.ChoiceFragment;
import com.destack.decision.ui.fragments.RNGFragment;
import com.destack.decision.ui.fragments.RSGFragment;
import com.destack.decision.ui.fragments.TimeFragment;
import com.destack.decision.ui.fragments.YesNoFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        drawerLayout = findViewById(R.id.main_drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.toggle_open, R.string.toggle_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new YesNoFragment()).commit();
            navigationView.setCheckedItem(R.id.yesNoMenuItem);
        }



    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.yesNoMenuItem:
                fragment = new YesNoFragment();
                break;
            case R.id.choiceMenuItem:
                fragment = new ChoiceFragment();
                break;
            case R.id.rngMenuItem:
                fragment = new RNGFragment();
                break;
            case R.id.rsgMenuItem:
                fragment = new RSGFragment();
                break;
            case R.id.timeMenuItem:
                fragment = new TimeFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
        drawerLayout.closeDrawers();
        return true;
    }

}
