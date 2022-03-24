package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View v) {
        CheckBox whippedCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox=(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate=chocolateCheckBox.isChecked();
        EditText getname=(EditText) findViewById(R.id.Name);
        String name= String.valueOf(getname.getText());
        Log.v("MainActivity","hasWhippedCream "+hasWhippedCream);
        int price= calculatePrice(quantity,10,hasChocolate,hasWhippedCream);
       // displayMessage(createOrderSummary(price,hasWhippedCream,hasChocolate,name));

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "mail.mohitkr13@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for : "+name);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(price,hasWhippedCream,hasChocolate,name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    int quantity=2;
    public void incre_button(View view){
    quantity+=1;
        if(quantity>100){
            quantity=100;
            Toast.makeText(getApplicationContext(),"You cannot have more than 100 coffee",Toast.LENGTH_SHORT).show();
        }
    displayQuantity(quantity);
    }
    public void decre_button(View view){
    quantity-=1;
        if(quantity<1){
            quantity=1;
            Toast.makeText(getApplicationContext(),"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
        }

    displayQuantity(quantity);
    }
    public int calculatePrice(int quantity,int pricePerCup,boolean hasChocolate,boolean hasWhippedCream){
        if(hasChocolate && hasWhippedCream){
            pricePerCup+=1;
            pricePerCup+=2;
        }else if(!hasChocolate && hasWhippedCream){
        pricePerCup+=2;}
        else if(hasChocolate && !hasWhippedCream){
            pricePerCup+=1;
        }
       int price=quantity*pricePerCup;
       return price;
    }
    public String createOrderSummary(int price,boolean hasWhippedCream,boolean hasChocolate,String Name){

        String priceMessage="Name: "+Name+" \nAdd Whipped Cream? "+hasWhippedCream+"\nAdd Chocolate? "+hasChocolate+"\nQuantity: "+quantity+"\nTotal: $"+price+"\nThank You!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /*private void displayPrice(int num) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(num));
    }
    */



}