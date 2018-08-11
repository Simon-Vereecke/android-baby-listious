package be.pxl.simon.babylistious;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        mBabyListItemAdapter = new BabyListItemAdapter();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
