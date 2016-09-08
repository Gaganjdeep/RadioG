package org.ggn.radioG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.ggn.radioG.core.Station;
import org.ggn.radioG.helpers.ConstantKeys;
import org.ggn.radioG.helpers.StorageHelper;
import org.ggn.radioG.webhelperG.WebServicehelperG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SplashActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        System.setProperty("http.keepAlive", "false");

        WebServicehelperG.getInstance().GET(ConstantKeys.GET_STATIONS_LIST, new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    StorageHelper storageHelper = new StorageHelper(SplashActivity.this);
                    File          mFolder       = storageHelper.getCollectionDirectory();


                    JSONObject jsonObject = new JSONObject(response.body().string());

                    if (jsonObject.getString("Status").equals("success"))
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("Message");

                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jobjInner = jsonArray.getJSONObject(i);

                            new Station(mFolder, jobjInner.getString("FilePath"));
                        }
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


                startActivity(new Intent(SplashActivity.this, MainActivity.class));

            }
        });


    }
}
