package org.ggn.radioG.helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import org.ggn.radioG.MainActivity;
import org.ggn.radioG.R;
import org.ggn.radioG.core.Station;


/**
 * ShortcutHelper class
 */
public class ShortcutHelper {

    /* Define log tag */
    private static final String LOG_TAG = ShortcutHelper.class.getSimpleName();


    /* Main class variables */
    private final Context mContext;


    /* Constructor */
    public ShortcutHelper(Context context) {
        mContext = context;
    }


    /* Places shortcut on Home screen */
    public void placeShortcut(Station station) {
        // create and launch intent to put shortcut on Home screen
        Intent addIntent = createShortcutIntent(station);
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        mContext.getApplicationContext().sendBroadcast(addIntent);

        // notify user
        Toast.makeText(mContext, mContext.getString(R.string.toastmessage_shortcut_created), Toast.LENGTH_LONG).show();
    }


    /* Removes shortcut for given station from Home screen */
    public void removeShortcut(Station station) {
        // create and launch intent to remove shortcut on Home screen
        Intent removeIntent = createShortcutIntent(station);
        removeIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        mContext.getApplicationContext().sendBroadcast(removeIntent);
    }


    /* Creates Intent for a station shortcut */
    private Intent createShortcutIntent (Station station) {

        // create shortcut icon
        ImageHelper imageHelper;
        Bitmap stationImage;
        Bitmap shortcutIcon;
        if (station.getStationImageFile().exists()) {
            // use station image
            stationImage = BitmapFactory.decodeFile(station.getStationImageFile().toString());
        } else {
            stationImage = null;
        }
        imageHelper = new ImageHelper(stationImage, mContext);
        shortcutIcon = imageHelper.createShortcut(192);

        String stationUri = station.getStreamUri().toString();

        // create intent to start MainActivity
        Intent shortcutIntent = new Intent(mContext, MainActivity.class);
        shortcutIntent.setAction(ConstantKeys.ACTION_SHOW_PLAYER);
        shortcutIntent.putExtra(ConstantKeys.EXTRA_STREAM_URI, stationUri);
        shortcutIntent.putExtra(ConstantKeys.EXTRA_PLAYBACK_STATE, true);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        LogHelper.v(LOG_TAG, "Intent for Home screen shortcut: " + shortcutIntent.toString() + " Activity: " + mContext);

        // create and launch intent put shortcut on Home screen
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, station.getStationName());
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, shortcutIcon);
        addIntent.putExtra("duplicate", false);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

        return addIntent;
    }

}
