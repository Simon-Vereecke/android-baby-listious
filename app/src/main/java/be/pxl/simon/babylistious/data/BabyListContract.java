package be.pxl.simon.babylistious.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class BabyListContract {

    public static final String CONTENT_AUTHORITY = "be.pxl.simon.babylistious";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BABYLIST = "babylist";

    public static final class BabyListEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_BABYLIST)
                .build();

        public static final String TABLE_NAME = "babylist";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_MANUFACTURER_LINK =  "manufacturer_link"; //"https://www.babybjorn.com/";

        public static final String COLUMN_BABYLIST_ID = "babylist_id";

        public static final String COLUMN_CATEGORY = "category";

        public static final String COLUMN_AMOUNT = "amount";

        public static final String COLUMN_CHECKED = "checked";

    }
}
