package org.ggn.radioG;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import org.ggn.radioG.core.Station;
import org.ggn.radioG.helpers.ConstantKeys;


/**
 * PlayerActivity class
 */
public final class PlayerActivity extends AppCompatActivity {

    /* Define log tag */
    private static final String LOG_TAG = PlayerActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set content view
        setContentView(R.layout.activity_player);

        // get intent
        Intent intent = getIntent();

        // CASE: show player in phone mode
        if (intent != null && ConstantKeys.ACTION_SHOW_PLAYER.equals(intent.getAction())) {

            // get station from intent
            Station station;
            if (intent.hasExtra(ConstantKeys.EXTRA_STATION)) {
                station = intent.getParcelableExtra(ConstantKeys.EXTRA_STATION);
            } else {
                station = null;
            }

            // get id of station from intent
            int stationID = 0;
            if (intent.hasExtra(ConstantKeys.EXTRA_STATION_ID)) {
                stationID = intent.getIntExtra(ConstantKeys.EXTRA_STATION_ID, 0);
            }

            // get playback action from intent (if started from shortcut)
            boolean startPlayback;
            if (intent.hasExtra(ConstantKeys.EXTRA_PLAYBACK_STATE)) {
                startPlayback = intent.getBooleanExtra(ConstantKeys.EXTRA_PLAYBACK_STATE, false);

                // enable the Up button
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null ) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }

            } else {
                startPlayback = false;
            }

            // create bundle for player activity fragment
            Bundle args = new Bundle();
            args.putParcelable(ConstantKeys.ARG_STATION, station);
            args.putInt(ConstantKeys.ARG_STATION_ID, stationID);
            args.putBoolean(ConstantKeys.ARG_PLAYBACK, startPlayback);

            PlayerActivityFragment playerActivityFragment = new PlayerActivityFragment();
            playerActivityFragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .add(R.id.player_container, playerActivityFragment)
                    .commit();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
