package be.pxl.simon.babylistious;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BabyListItemAdapter extends RecyclerView.Adapter<BabyListItemAdapter.BabyListItemViewHolder> {

    private String[] mBabyListData;

    private final BabyListItemAdapterOnClickHandler mClickHandler;

    public interface BabyListItemAdapterOnClickHandler {
        void onClick(String babyListItemDescription);
    }

    public BabyListItemAdapter(BabyListItemAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class BabyListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mBabyListItemTextView;

        public BabyListItemViewHolder(View view) {
            super(view);
            mBabyListItemTextView = (TextView) view.findViewById(R.id.baby_item_data);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            String babyListItemDescription = mBabyListData[adapterPosition];
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
        String babyListItemDescription = mBabyListData[position];
        babyListItemViewHolder.mBabyListItemTextView.setText(babyListItemDescription);
    }

    @Override
    public int getItemCount() {
        if (mBabyListData == null) return 0;
        return mBabyListData.length;
    }

    public void setBabyListData(String[] babyListData) {
        mBabyListData = babyListData;
        notifyDataSetChanged();
    }
}
