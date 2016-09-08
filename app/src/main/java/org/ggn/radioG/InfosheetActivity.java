package org.ggn.radioG;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.ggn.radioG.helpers.ConstantKeys;


/**
 * InfosheetActivity class
 */
public final class InfosheetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get activity title from intent
        Intent intent = this.getIntent();

        // set activity title
        if (intent.hasExtra(ConstantKeys.EXTRA_INFOSHEET_TITLE)) {
            this.setTitle(intent.getStringExtra(ConstantKeys.EXTRA_INFOSHEET_TITLE));
        }

        // set activity view
        if (intent.hasExtra(ConstantKeys.EXTRA_INFOSHEET_CONTENT) && intent.getIntExtra(ConstantKeys.EXTRA_INFOSHEET_CONTENT, -1) == ConstantKeys.INFOSHEET_CONTENT_ABOUT) {
            setContentView(R.layout.fragment_infosheet_about);
        } else if (intent.hasExtra(ConstantKeys.EXTRA_INFOSHEET_CONTENT) && intent.getIntExtra(ConstantKeys.EXTRA_INFOSHEET_CONTENT, -1) == ConstantKeys.INFOSHEET_CONTENT_HOWTO) {
            setContentView(R.layout.fragment_infosheet_howto);
        }

    }

}
