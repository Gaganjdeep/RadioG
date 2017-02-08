package org.ggn.radioG;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import org.ggn.radioG.core.Station;
import org.ggn.radioG.helpers.StorageHelper;

import java.io.File;

public class SplashActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new AsyncTask<Void, Void, Void>()
        {

            @Override
            protected Void doInBackground(Void... voids)
            {
                try
                {
                    StorageHelper storageHelper = new StorageHelper(SplashActivity.this);
                    File          mFolder       = storageHelper.getCollectionDirectory();


                    new Station(mFolder, "http://69.175.94.98:8146");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                super.onPostExecute(aVoid);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }, 4000);

            }
        }.execute();


//        WebServicehelperG.getInstance().GET(ConstantKeys.GET_STATIONS_LIST, new Callback()
//        {
//            @Override
//            public void onFailure(Call call, IOException e)
//            {
//
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException
//            {
//                try
//                {
//                    StorageHelper storageHelper = new StorageHelper(SplashActivity.this);
//                    File          mFolder       = storageHelper.getCollectionDirectory();
//
//
//                    JSONObject jsonObject = new JSONObject(response.body().string());
//
//                    if (jsonObject.getString("Status").equals("success"))
//                    {
//                        JSONArray jsonArray = jsonObject.getJSONArray("Message");
//
//                        for (int i = 0; i < jsonArray.length(); i++)
//                        {
//                            JSONObject jobjInner = jsonArray.getJSONObject(i);
//
//                            new Station(mFolder, jobjInner.getString("FilePath"));
//                        }
//                    }
//
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//
//            }
//        });


    }
}
