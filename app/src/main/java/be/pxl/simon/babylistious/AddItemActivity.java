package be.pxl.simon.babylistious;

import android.content.ContentValues;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import be.pxl.simon.babylistious.data.BabyListContract;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickAddItem(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        String input = ((EditText) findViewById(R.id.editTextItemDescription)).getText().toString();
        if (input.length() == 0) {
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(BabyListContract.BabyListEntry.COLUMN_DESCRIPTION, input);
        contentValues.put(BabyListContract.BabyListEntry.COLUMN_CATEGORY, 4);
        contentValues.put(BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID, 4);
        contentValues.put(BabyListContract.BabyListEntry.COLUMN_CHECKED, false);
        contentValues.put(BabyListContract.BabyListEntry.COLUMN_AMOUNT, 1);
        contentValues.put(BabyListContract.BabyListEntry.COLUMN_MANUFACTURER_LINK, "http://www.google.com");

        Uri uri = getContentResolver().insert(BabyListContract.BabyListEntry.CONTENT_URI, contentValues);

        if (uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
