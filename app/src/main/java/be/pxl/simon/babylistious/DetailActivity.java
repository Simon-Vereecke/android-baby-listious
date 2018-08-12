package be.pxl.simon.babylistious;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private String mDescription;
    private TextView mDescriptionDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDescriptionDisplay = (TextView) findViewById(R.id.detail_display_description);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mDescription = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                mDescriptionDisplay.setText(mDescription);
            }
        }
    }
}
