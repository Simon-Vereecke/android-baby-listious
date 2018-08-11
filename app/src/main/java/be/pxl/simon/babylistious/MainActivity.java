package be.pxl.simon.babylistious;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mBabyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBabyTextView = (TextView) findViewById(R.id.baby_list_data);

        String[] dummyData = {
                "6 stuks bovenkleding",
                "6 broekjes",
                "4 paar sokjes",
                "Jasje of vestje",
                "2 katoenen mutsjes",
                "Kledingkast voor de baby",
                "Zuigenlingenvoeding",
                "2 flesjes en 4 spenen",
                "Flessenwarmer",
                "Flessenborstel",
                "Magnetron sterilisator",
                "Melkpoedertoren of doseerbakje",
                "Babyfoo",
                "Speelgoed en knuffels",
        };

        for (String dummyItem: dummyData) {
            mBabyTextView.append(dummyItem + "\n\n\n");
        }
    }
}
