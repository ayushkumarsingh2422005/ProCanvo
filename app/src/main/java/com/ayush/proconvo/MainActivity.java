package com.ayush.proconvo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ayush.proconvo.R;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerFrom, spinnerTo;
    TextView fromTextView, toTextView;
    StringBuffer fromString = new StringBuffer();
//    StringBuffer toString = new StringBuffer();
    GridLayout gridLayout;
    String from = "Millimeter (mm)", to = "Millimeter (mm)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        TextView t = findViewById(R.id.button1);
        fromString.append(0);
        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        fromTextView = (TextView) findViewById(R.id.fromTextView);
        toTextView = (TextView) findViewById(R.id.toTextView);

        ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapterFrom);

        ArrayAdapter<CharSequence> adapterTo = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(adapterTo);


        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from = adapterView.getItemAtPosition(i).toString();
                covertUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to = adapterView.getItemAtPosition(i).toString();
                covertUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View view = gridLayout.getChildAt(i);
            if (view instanceof Button){
                Button button = (Button) view;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!button.getText().toString().equals(" ")){
                            if (!fromString.toString().equals("0")){
                                fromString.append(button.getText().toString());
                                fromTextView.setText(fromString.toString());
                            }else {
                                fromString.setLength(0);
                                fromString.append(button.getText().toString());
                                fromTextView.setText(fromString.toString());
                            }
                        } else if (button.getText().toString().equals(" ") && fromString.length() > 0) {
                            fromString.delete(fromString.length() - 1, fromString.length());
                            if (fromString.length() == 0) fromString.append(0);
                            fromTextView.setText(fromString.toString());
                        }
                        covertUnit();
                    }
                });
            }
        }
    }
    public void covertUnit(){
        double in_meter = 0;
        double from_value = Float.valueOf(fromTextView.getText().toString());
        double to_value = 0;

        switch (from) {
            case "Millimeter (mm)":
                in_meter = 0.001 * from_value;
                break;
            case "Centimeter (cm)":
                in_meter = 0.01 * from_value;
                break;
            case "Decimeter (dm)":
                in_meter = 0.1 * from_value;
                break;
            case "Meter (m)":
                in_meter = 1 * from_value;
                break;
            case "Decameter (dam)":
                in_meter = 10 * from_value;
                break;
            case "Hectometer (hm)":
                in_meter = 100 * from_value;
                break;
            case "Kilometer (km)":
                in_meter = 1000 * from_value;
                break;
            case "Inch (in)":
                in_meter = 0.0254 * from_value;
                break;
            case "Foot (ft)":
                in_meter = 0.3048 * from_value;
                break;
            case "Yard (yd)":
                in_meter = 0.9144 * from_value;
                break;
            case "Mile (mi)":
                in_meter = 1609.34 * from_value;
                break;
            case "Fathom (ftm)":
                in_meter = 1.8288 * from_value;
                break;
            case "Nautical mile":
                in_meter = 1852 * from_value;
                break;
            case "Cubit (ancient)":
                in_meter = 0.5 * from_value;
                break;
            case "Span (ancient)":
                in_meter = 0.22 * from_value;
                break;
            case "Finger (ancient)":
                in_meter = 0.022 * from_value;
                break;
            case "Hand (ancient)":
                in_meter = 0.1016 * from_value;
                break;
            case "Pace (ancient)":
                in_meter = 0.76 * from_value;
                break;
            case "Astronomical unit (AU)":
                in_meter = 149597870.7 * from_value;
                break;
            case "Light year (ly)":
                in_meter = 9.461 * Math.pow(10, 15) * from_value;
                break;
            case "Parsec (pc)":
                in_meter = 3.086 * Math.pow(10, 16) * from_value;
                break;
            case "Astronomical league":
                in_meter = 5.93 * Math.pow(10, 12) * from_value;
                break;
            case "Light minute":
                in_meter = 1.798 * Math.pow(10, 10) * from_value;
                break;
            case "Light second":
                in_meter = 2.998 * Math.pow(10, 8) * from_value;
                break;
            case "Angstrom (Å)":
                in_meter = 1 * Math.pow(10, -10) * from_value;
                break;
            case "Fermi (fm)":
                in_meter = 1 * Math.pow(10, -15) * from_value;
                break;
            case "Micron (µm)":
                in_meter = 1 * Math.pow(10, -6) * from_value;
                break;
            case "Thou (th)":
                in_meter = 2.54 * Math.pow(10, -5) * from_value;
                break;
            case "Chain (ch)":
                in_meter = 20.1168 * from_value;
                break;
            case "Link (li)":
                in_meter = 0.201168 * from_value;
                break;
            case "Rod or Perch (rd)":
                in_meter = 5.0292 * from_value;
                break;
            case "Furlong (fur)":
                in_meter = 201.168 * from_value;
                break;
            case "Handbreadth":
                in_meter = 0.089 * from_value;
                break;
            case "Fingerbreadth":
                in_meter = 0.0225 * from_value;
                break;
            case "Cubit (Hebrew)":
                in_meter = 0.4572 * from_value;
                break;
            case "Cubit (Egyptian)":
                in_meter = 0.524 * from_value;
                break;
            case "Arshin (Russian)":
                in_meter = 0.7112 * from_value;
                break;
            case "Li (Chinese)":
                in_meter = 500 * from_value;
                break;
            case "Zhang (Chinese)":
                in_meter = 3.58 * from_value;
                break;
            case "Chi (Chinese)":
                in_meter = 0.3 * from_value;
                break;
            case "Bu (Chinese)":
                in_meter = 0.3 * from_value;
                break;
            case "Survey foot":
                in_meter = 0.3048006096 * from_value;
                break;
            case "US survey mile":
                in_meter = 1609.347219 * from_value;
                break;
        }

        switch (to) {
            case "Millimeter (mm)":
                to_value = in_meter / 0.001;
                break;
            case "Centimeter (cm)":
                to_value = in_meter / 0.01;
                break;
            case "Decimeter (dm)":
                to_value = in_meter / 0.1;
                break;
            case "Meter (m)":
                to_value = in_meter / 1;
                break;
            case "Decameter (dam)":
                to_value = in_meter / 10;
                break;
            case "Hectometer (hm)":
                to_value = in_meter / 100;
                break;
            case "Kilometer (km)":
                to_value = in_meter / 1000;
                break;
            case "Inch (in)":
                to_value = in_meter / 0.0254;
                break;
            case "Foot (ft)":
                to_value = in_meter / 0.3048;
                break;
            case "Yard (yd)":
                to_value = in_meter / 0.9144;
                break;
            case "Mile (mi)":
                to_value = in_meter / 1609.34;
                break;
            case "Fathom (ftm)":
                to_value = in_meter / 1.8288;
                break;
            case "Nautical mile":
                to_value = in_meter / 1852;
                break;
            case "Cubit (ancient)":
                to_value = in_meter / 0.5;
                break;
            case "Span (ancient)":
                to_value = in_meter / 0.22;
                break;
            case "Finger (ancient)":
                to_value = in_meter / 0.022;
                break;
            case "Hand (ancient)":
                to_value = in_meter / 0.1016;
                break;
            case "Pace (ancient)":
                to_value = in_meter / 0.76;
                break;
            case "Astronomical unit (AU)":
                to_value = in_meter / 149597870.7;
                break;
            case "Light year (ly)":
                to_value = in_meter / (9.461 * Math.pow(10, 15));
                break;
            case "Parsec (pc)":
                to_value = in_meter / (3.086 * Math.pow(10, 16));
                break;
            case "Astronomical league":
                to_value = in_meter / (5.93 * Math.pow(10, 12));
                break;
            case "Light minute":
                to_value = in_meter / (1.798 * Math.pow(10, 10));
                break;
            case "Light second":
                to_value = in_meter / (2.998 * Math.pow(10, 8));
                break;
            case "Angstrom (Å)":
                to_value = in_meter / (1 * Math.pow(10, -10));
                break;
            case "Fermi (fm)":
                to_value = in_meter / (1 * Math.pow(10, -15));
                break;
            case "Micron (µm)":
                to_value = in_meter / (1 * Math.pow(10, -6));
                break;
            case "Thou (th)":
                to_value = in_meter / (2.54 * Math.pow(10, -5));
                break;
            case "Chain (ch)":
                to_value = in_meter / 20.1168;
                break;
            case "Link (li)":
                to_value = in_meter / 0.201168;
                break;
            case "Rod or Perch (rd)":
                to_value = in_meter / 5.0292;
                break;
            case "Furlong (fur)":
                to_value = in_meter / 201.168;
                break;
            case "Handbreadth":
                to_value = in_meter / 0.089;
                break;
            case "Fingerbreadth":
                to_value = in_meter / 0.0225;
                break;
            case "Cubit (Hebrew)":
                to_value = in_meter / 0.4572;
                break;
            case "Cubit (Egyptian)":
                to_value = in_meter / 0.524;
                break;
            case "Arshin (Russian)":
                to_value = in_meter / 0.7112;
                break;
            case "Li (Chinese)":
                to_value = in_meter / 500;
                break;
            case "Zhang (Chinese)":
                to_value = in_meter / 3.58;
                break;
            case "Chi (Chinese)":
                to_value = in_meter / 0.3;
                break;
            case "Bu (Chinese)":
                to_value = in_meter / 0.3;
                break;
            case "Survey foot":
                to_value = in_meter / 0.3048006096;
                break;
            case "US survey mile":
                to_value = in_meter / 1609.347219;
                break;
        }
            
        toTextView.setText(Double.toString(to_value));

    }
}