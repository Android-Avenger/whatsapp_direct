package com.example.whatsapp_direct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp_direct.databinding.ActivityEventListnersBinding;

public class EventListeners extends AppCompatActivity {

      private float x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         ActivityEventListnersBinding mainBinding = ActivityEventListnersBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

         mainBinding.onClick.setOnClickListener(view -> Toast.makeText(EventListeners.this, "This is simple on Click", Toast.LENGTH_SHORT).show());


        mainBinding.ed1.setOnFocusChangeListener((view, b) -> {
            if(hasWindowFocus()){
                Toast.makeText(EventListeners.this, "hasChanged", Toast.LENGTH_SHORT).show();
            }
        });

         mainBinding.ed2.setOnFocusChangeListener((view, b) -> {

             if (!hasWindowFocus()){
                 Toast.makeText(EventListeners.this, "FocusChanged", Toast.LENGTH_SHORT).show();
             }
         });

        mainBinding.showDialog.setOnClickListener(view -> {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this).setTitle("Show Dialog")
                    .setMessage("Are you sure to quite the Dialog Box?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.cancel();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    }).setCancelable(true);

            AlertDialog myDialogBox = alertDialogBuilder.create();
            myDialogBox.show();

        });




    }
}