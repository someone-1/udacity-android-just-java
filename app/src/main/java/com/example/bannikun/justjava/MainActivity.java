package com.example.bannikun.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int quantity = 0;

    void increment(View v){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantity++ ;
        quantityTextView.setText("" + quantity);
    }

    void decrement(View v){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantity--;
        quantityTextView.setText("" + quantity);
    }

    private int calculatePrice(){
        int pricePerCup = 5;
        return quantity*pricePerCup;
    }

    private String getUserName(){
        EditText userInput = (EditText) findViewById(R.id.user_name);
        return userInput.getText().toString();
    }


    private String getSummaryString(){
        String sumary = "Name : " + getUserName();
        sumary = sumary + '\n' + "Quantity : " + quantity;
        sumary += '\n' + "Price : " + calculatePrice();
        sumary += '\n' + "Thankyou!";
        return sumary;
    }

    private boolean validateForm(){
        if(getUserName() == "" && quantity > 0){
            return true;
        } else {
            if(getUserName() != ""){
                EditText userInput = (EditText) findViewById(R.id.user_name);
                userInput.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            if(quantity <= 0){
                TextView qunatity = (TextView) findViewById(R.id.quantity);
                qunatity.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }

//        else if(getUserName() != "") {
//            EditText userInput = (EditText) findViewById(R.id.user_name);
//            userInput.setTextColor(getResources().getColor(R.color.colorAccent));
//            return false;
//        } else if(quantity <= 0){
//            TextView qunatity = (TextView) findViewById(R.id.quantity);
//            qunatity.setTextColor(getResources().getColor(R.color.colorPrimary));
//        }
    }

    void orderSummary(View v){

        Intent mapsIntent = new Intent(Intent.ACTION_SENDTO);
        mapsIntent.setData(Uri.parse("mailto:"));
        mapsIntent.putExtra(Intent.EXTRA_SUBJECT, "some random subject");
        mapsIntent.putExtra(Intent.EXTRA_TEXT, getSummaryString());
        if(mapsIntent.resolveActivity(getPackageManager()) != null && validateForm()){
            startActivity(mapsIntent);
        }
    }

//    void orderSummary(View v){
//
//        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
//        TextView priceTextView = (TextView) findViewById(R.id.price);
//        int quantity = Integer.parseInt(quantityTextView.getText().toString());
//        int pricePerUnit = 25;
//        String qty = Integer.toString(quantity*pricePerUnit);
//        priceTextView.setText(qty);
//    }

}
