package com.biggerbytes.serverremote;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.biggerbytes.serverremote.commandfragments.ChooseHeaderFragment;
import com.biggerbytes.serverremote.commandfragments.HeaderFragment;


/**
 * Created by shach on 2/21/2016.
 */
public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, new ChooseHeaderFragment()).commit();
    }

    private void switchHeaderFragment(HeaderFragment cFragment) {
        FragmentManager fm = getFragmentManager();
        Log.d(TAG, "Header chosen");
        if (fm.findFragmentById(R.id.fragment_container) != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fragment_container, cFragment);
            ft.commit();
        }

    }

    public void choseHeader(View v) {
        switchHeaderFragment(DataMaps.btnToHeaderFragment.get(v.getId()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onClassesInformation(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        ClassesInformationDialogFragment dialog = new ClassesInformationDialogFragment();
        dialog.show(fm, "CLASSES_INFO_DIALOG");
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) getFragmentManager().popBackStack();
        else super.onBackPressed();
    }
}
