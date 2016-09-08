package org.ggn.radioG.webhelperG;

import org.ggn.radioG.core.Station;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by GaganDroid on 08 Sep 2016.
 */
public class WebServicehelperG
{
    private static OkHttpClient client = new OkHttpClient();

    private static WebServicehelperG ourInstance = new WebServicehelperG();

    public static WebServicehelperG getInstance()
    {
        if (client == null)
        {
            client = new OkHttpClient();
        }
        return ourInstance;
    }

    private WebServicehelperG()
    {
    }

    public void GET(String url, Callback callback)
    {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newBuilder().retryOnConnectionFailure(true);

        client.newCall(request).enqueue(callback);
    }


}
