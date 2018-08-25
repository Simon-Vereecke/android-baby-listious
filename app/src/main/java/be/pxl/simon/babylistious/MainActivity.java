package be.pxl.simon.babylistious;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
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

public class MainActivity extends AppCompatActivity implements
        BabyListItemAdapter.BabyListItemAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<String[]> {

    private RecyclerView mRecyclerView;
    private BabyListItemAdapter mBabyListItemAdapter;

    private static  final int BABYLIST_LOADER_ID = 0;

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

        // Inintialize the AsyncTaskLoader
        int loaderId = BABYLIST_LOADER_ID;
        LoaderManager.LoaderCallbacks<String[]> callback = MainActivity.this;
        Bundle bundleForLoader = null;
        getSupportLoaderManager().initLoader(loaderId, bundleForLoader, callback);

    }

    @Override
    public void onClick(String babyListItemDescription) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, babyListItemDescription);
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

        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<String[]> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<String[]>(this) {

            String[] mBabyListData = null;

            @Override
            protected void onStartLoading() {
                if (mBabyListData != null) {
                    deliverResult(mBabyListData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public String[] loadInBackground() {
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

                return dummyData;
            }

            @Override
            public void deliverResult(String[] data) {
                mBabyListData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<String[]> loader, String[] data) {
        mBabyListItemAdapter.setBabyListData(data);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<String[]> loader) {

    }

}
