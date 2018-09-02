package be.pxl.simon.babylistious;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import be.pxl.simon.babylistious.data.BabyListContract;
import be.pxl.simon.babylistious.utilities.BabyListUtils;

public class BabyListItemAdapter extends RecyclerView.Adapter<BabyListItemAdapter.BabyListItemViewHolder> {

    private final Context mContext;

    private final BabyListItemAdapterOnClickHandler mClickHandler;

    public interface BabyListItemAdapterOnClickHandler {
        void onClick(String babyListItemDescription);
    }

    private Cursor mCursor;

    public BabyListItemAdapter(@NonNull Context context, BabyListItemAdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    public class BabyListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView iconView;
        public final TextView mBabyListItemTextView;
        public final AppCompatCheckBox mBabyListItemCheckBox;

        public BabyListItemViewHolder(View view) {
            super(view);

            iconView = (ImageView) view.findViewById(R.id.baby_list_item_icon);
            mBabyListItemTextView = (TextView) view.findViewById(R.id.baby_item_data);
            mBabyListItemCheckBox = (AppCompatCheckBox) view.findViewById(R.id.baby_item_checkbox);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String babyListItemDescription = mBabyListItemTextView.getText().toString();
            mClickHandler.onClick(babyListItemDescription);
        }
    }

    @NonNull
    @Override
    public BabyListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.baby_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new BabyListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BabyListItemViewHolder babyListItemViewHolder, int position) {
        mCursor.moveToPosition(position);

        /****************
         * List Icon *
         ****************/
        int iconId = mCursor.getInt(MainActivity.INDEX_BABYLIST_ICON_ID);
        int iconImageId;
        iconImageId = BabyListUtils.getSmallArtResourceIdForListItem(iconId);
        babyListItemViewHolder.iconView.setImageResource(iconImageId);

        /****************
         * Description *
         ****************/
        String babyListItemDescription = mCursor.getString(MainActivity.INDEX_BABYLIST_DESCRIPTION);
        babyListItemViewHolder.mBabyListItemTextView.setText(babyListItemDescription);

        // Set tag for delete on swipe
        int id = mCursor.getInt(MainActivity.INDEX_BABYLIST_ID);
        babyListItemViewHolder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

}
