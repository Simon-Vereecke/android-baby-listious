package be.pxl.simon.babylistious.utilities;

import android.util.Log;

import be.pxl.simon.babylistious.R;

public class BabyListUtils {
    private static final String LOG_TAG = BabyListUtils.class.getSimpleName();

    public static int getSmallArtResourceIdForListItem(int iconId) {
        switch (iconId) {
            case 1:
                return R.drawable.ic_round_child_care;
            case 2:
                return R.drawable.ic_round_child_friendly;
            case 3:
                return R.drawable.ic_round_free_breakfast;
            case 4:
                return R.drawable.ic_round_hot_tub;
            case 5:
                return R.drawable.ic_round_pregnant_woman;
            case 6:
                return R.drawable.ic_round_all_inclusive;

            default:
                Log.d(LOG_TAG, "Unknown category " + iconId);
                return R.drawable.ic_round_child_care;
        }
    }
}
