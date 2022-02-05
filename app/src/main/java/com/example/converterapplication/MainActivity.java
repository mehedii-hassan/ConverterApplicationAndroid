package com.example.converterapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder;
    private androidx.appcompat.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Fragment fragment = new ConverterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        setAlertDialog();
    }

    void setAlertDialog() {
        alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);

        //for setting title----------
        alertDialogBuilder.setTitle("Alert title");

        //for setting message----------
        alertDialogBuilder.setMessage("Do you want to exit ?");

        //for setting icon--------
        alertDialogBuilder.setIcon(R.drawable.question);

        //set positive button--------
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();

            }
        });

        //set negative button----------
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        //set neutral button----------
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        //create alert dialog------------
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    //Data conversion end================================================================

}