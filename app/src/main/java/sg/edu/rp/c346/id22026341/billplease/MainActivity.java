package sg.edu.rp.c346.id22026341.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView amount;
    EditText inputAmount;
    TextView numOfPax;
    EditText inputNumOfPax;
    TextView discount;
    EditText inputDiscount;
    ToggleButton togSvs;
    ToggleButton togGst;
    RadioGroup rgPayment;
    Button btnSplit;
    Button btnReset;
    TextView totalBill;
    TextView eachPays;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amountText);
        inputAmount = findViewById(R.id.editTextAmount);
        numOfPax = findViewById(R.id.numOfPaxText);
        inputNumOfPax = findViewById(R.id.editTextNumOfPax);
        discount = findViewById(R.id.textViewDiscount);
        inputDiscount = findViewById(R.id.editTextDiscount);
        togSvs = findViewById(R.id.toggleButtonSVS);
        togGst = findViewById(R.id.toggleButtonGST);
        rgPayment = findViewById(R.id.radioGroupPayment);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        totalBill = findViewById(R.id.totalBill);
        eachPays = findViewById(R.id.eachPayment);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringResponse = totalBill.getText().toString();
                String stringResponse2 = eachPays.getText().toString();
                String amt = inputAmount.getText().toString();
                String nop = inputNumOfPax.getText().toString();
                String disc = inputDiscount.getText().toString();
                double inputamt = new Double(amt).doubleValue();
                int eachnum = Integer.valueOf(nop);
                double inputdisc = new Double(disc).doubleValue();
                double fullamt = 0;
                double eachamt = 0;
                //double amtdisc = 0;
                int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.radioButtonCash){
                    if (togSvs.isChecked()==true && togGst.isChecked()== true) {
                        fullamt = (inputamt * 1.17)*((100-inputdisc)/100);
                        eachamt = fullamt/eachnum;
                    } else if (togSvs.isChecked()==true && togGst.isChecked()== false) {
                        fullamt = (inputamt * 1.10)*((100-inputdisc)/100);
                        eachamt = fullamt/eachnum;
                    } else if (togSvs.isChecked()==false && togGst.isChecked()== true) {
                        fullamt = (inputamt * 1.07)*((100-inputdisc)/100);
                        eachamt = fullamt/eachnum;
                    }
                    //String newamt = Double.toString(fullamt);
                    String newamt = String.format("%.2f",fullamt);
                    //String newnum = Double.toString(eachamt);
                    String newnum = String.format("%.2f",eachamt);
                    stringResponse = stringResponse + " $" + newamt;
                    totalBill.setText(stringResponse);
                    stringResponse2 = stringResponse2 + " $" + newnum + " in cash";
                    eachPays.setText(stringResponse2);
                }else{
                    if (togSvs.isChecked()==true && togGst.isChecked()== true) {
                        fullamt = inputamt * 1.17*((100-inputdisc)/100);;
                        eachamt = fullamt/eachnum;
                    } else if (togSvs.isChecked()==true && togGst.isChecked()== false) {
                        fullamt = inputamt * 1.10*((100-inputdisc)/100);;
                        eachamt = fullamt/eachnum;
                    } else if (togSvs.isChecked()==false && togGst.isChecked()== true) {
                        fullamt = inputamt * 1.07*((100-inputdisc)/100);;
                        eachamt = fullamt / eachnum;
                    }
                    //String newamt = Double.toString(fullamt);
                    String newamt = String.format("%.2f",fullamt);
                    //String newnum = Double.toString(eachamt);
                    String newnum = String.format("%.2f",eachamt);
                    stringResponse = stringResponse + " $" + newamt;
                    totalBill.setText(stringResponse);
                    stringResponse2 = stringResponse2 + " $" + newnum + " via PayNow to 88002480";
                    eachPays.setText(stringResponse2);
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            inputAmount.setText("");
            inputNumOfPax.setText("");
            inputDiscount.setText("");
            //togSvs.setTextOff("No SVS");
            //togGst.setTextOff("No GST");
            totalBill.setText("Total bill: ");
            eachPays.setText("Each Pays: ");
            }
        });
        }
    }