package be.pxl.simon.babylistious;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BabyListItemAdapter.BabyListItemAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private BabyListItemAdapter mBabyListItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_baby_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mBabyListItemAdapter = new BabyListItemAdapter(this);

        mRecyclerView.setAdapter(mBabyListItemAdapter);

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

        mBabyListItemAdapter.setBabyListData((dummyData));
    }

    @Override
    public void onClick(String babyListItemDescription) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        startActivity(intentToStartDetailActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.baby_listious_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
