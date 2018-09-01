package be.pxl.simon.babylistious;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import be.pxl.simon.babylistious.data.BabyListContract;

public class MainActivity extends AppCompatActivity implements
        BabyListItemAdapter.BabyListItemAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<Cursor> {

    private final String TAG = MainActivity.class.getSimpleName();

    public static final String[] MAIN_BABYLIST_PROJECTION = {
            BabyListContract.BabyListEntry.COLUMN_DESCRIPTION,
            BabyListContract.BabyListEntry.COLUMN_AMOUNT,
            BabyListContract.BabyListEntry.COLUMN_BABYLIST_ID
    };

    public static final int INDEX_BABYLIST_DESCRIPTION = 0;
    public static final int INDEX_BABYLIST_AMOUNT = 1;
    public static final int INDEX_BABYLIST_ICON_ID = 2;

    private RecyclerView mRecyclerView;
    private BabyListItemAdapter mBabyListItemAdapter;
    private FloatingActionButton mFloatingActionButton;

    private int mPosition = RecyclerView.NO_POSITION;
    private static  final int BABYLIST_LOADER_ID = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DataUtils.insertInitialData(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_baby_list);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, AddItemActivity.class);
            startActivity(intent);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        /* RecyclerView */
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mBabyListItemAdapter = new BabyListItemAdapter(this,this);
        mRecyclerView.setAdapter(mBabyListItemAdapter);

        int loaderId = BABYLIST_LOADER_ID;
        LoaderManager.LoaderCallbacks<Cursor> callback = MainActivity.this;
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
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {

        switch (loaderId) {
            case BABYLIST_LOADER_ID:
                Uri babylistQueryUri = BabyListContract.BabyListEntry.CONTENT_URI;
                return new CursorLoader(this,
                        babylistQueryUri,
                        MAIN_BABYLIST_PROJECTION,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader not implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        Log.d("Main", "LOAD FINISHED");
        mBabyListItemAdapter.swapCursor(data);
        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
        mRecyclerView.smoothScrollToPosition(mPosition);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<Cursor> loader) {
        mBabyListItemAdapter.swapCursor(null);
    }

}
