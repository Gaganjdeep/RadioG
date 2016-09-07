package org.ggn.radioG.helpers;

import android.app.Activity;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.widget.Toast;

import org.ggn.radioG.R;

import java.io.File;


/**
 * StorageHelper class
 */
public class StorageHelper
{

    /* Define log tag */
    private static final String LOG_TAG = StorageHelper.class.getSimpleName();


    /* Main class variables */
    private final Activity mActivity;


    /* Constructor */
    public StorageHelper(Activity activity)
    {
        mActivity = activity;
    }


    /* Return a write-able sub-directory from external storage  */
    public File getCollectionDirectory()
    {
        String subDirectory = "Collection";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
        {
            File[] storage = new File[0];

            storage = mActivity.getExternalFilesDirs(subDirectory);

            for (File file : storage)
            {
                if (file != null)
                {
                    String state = EnvironmentCompat.getStorageState(file);
                    if (Environment.MEDIA_MOUNTED.equals(state))
                    {
                        LogHelper.i(LOG_TAG, "External storage: " + file.toString());
                        return file;
                    }
                }
            }

        }
        else
        {
            File folder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "radioChannels");
            if (!folder.exists())
            {
                folder.mkdirs();
            }

            return folder;
        }


        Toast.makeText(mActivity, mActivity.getString(R.string.toastalert_no_external_storage), Toast.LENGTH_LONG).show();
        LogHelper.e(LOG_TAG, "Unable to access external storage.");
        // finish activity
        mActivity.finish();

        return null;
    }

}
