package com.biggerbytes.serverremote;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toolbar;

import com.biggerbytes.serverremote.commandUtils.ClassesInformationDialogFragment;
import com.biggerbytes.serverremote.commandfragments.ChooseHeaderFragment;
import com.biggerbytes.serverremote.commandfragments.HeaderFragment;


/**
 * Created by shach on 2/21/2016.
 */
public class MainActivity extends Activity {
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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


    public void onClassesInformation(View v) {
        FragmentManager fm = getFragmentManager();
        ClassesInformationDialogFragment dialog = new ClassesInformationDialogFragment();
        dialog.show(fm, "CLASSES_INFO_DIALOG");

    }
}
