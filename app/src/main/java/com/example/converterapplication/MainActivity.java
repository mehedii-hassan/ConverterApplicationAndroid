package com.example.converterapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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
        showAlertDialog();
    }

    private void showAlertDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_alert_dialog, null);

        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();

        Button buttonYes = view.findViewById(R.id.btnYes);
        Button buttonNo = view.findViewById(R.id.btnNo);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

    }

//default alert dialog=============================================================================
   /* void showAlertDialog() {
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
    }*/

}