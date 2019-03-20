package info.androidhive.bottomnavigation;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import info.androidhive.bottomnavigation.fragment.GiftsFragment;
import info.androidhive.bottomnavigation.fragment.Ranking;
import info.androidhive.bottomnavigation.fragment.Selector;
import info.androidhive.bottomnavigation.fragment.SobreNos;
import info.androidhive.bottomnavigation.helper.BottomNavigationBehavior;

import static info.androidhive.bottomnavigation.LoginActivity.MY_PREFS_NAME;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_first_layout:


                    fragment = new GiftsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.rank:

                    fragment = new Ranking();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_help:

                    fragment = new Selector();
                    loadFragment(fragment);
                    return true;

                case R.id.nav_third_layout:

                    fragment = new SobreNos();
                    loadFragment(fragment);
                    return true;

                case R.id.logout:

                    //logout
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.remove("token");
                    editor.remove("email");
                    editor.apply();
                    //mudar login
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    MainActivity.this.startActivity(intent);

                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default

        loadFragment(new GiftsFragment());
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
