package com.example.whatsapp_direct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import com.example.whatsapp_direct.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

       mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

       setContentView(mainBinding.getRoot());

       mainBinding.countryCode.registerCarrierNumberEditText(mainBinding.phoneNumber);


       mainBinding.gotoWhatsapp.setOnClickListener(view -> {
           if (isValidated() && checkForNetworkConnection() ){


               goToWhatsapp(mainBinding.countryCode.getFullNumberWithPlus());
           }

       });

    }

    private boolean isValidated(){
        if (mainBinding.phoneNumber.getText().toString().trim().isEmpty()){
            mainBinding.phoneNumber.setError("Enter Phone Number");
            mainBinding.phoneNumber.requestFocus();
        }else if(!mainBinding.countryCode.isValidFullNumber()){
            mainBinding.phoneNumber.setError("Enter valid phone");
            mainBinding.phoneNumber.requestFocus();
        }
        return mainBinding.countryCode.isValidFullNumber();
    }

    private void goToWhatsapp(String phone){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        startActivity(intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+phone)));
    }

    private boolean checkForNetworkConnection(){
        ConnectivityManager internetCon = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(internetCon.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED){
            return true;
        }else {
          showDialog();
          return false;
        }
    }

    private void showDialog(){
       new AlertDialog.Builder(MainActivity.this)
                .setTitle("Connectivity Error!")
                .setMessage("Check your Internet Connection")
                .create()
                .show();
    }

}