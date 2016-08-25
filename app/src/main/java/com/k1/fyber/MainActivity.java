package com.k1.fyber;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.k1.fyber.api.ApiService;
import com.k1.fyber.model.Offers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APP_ID = "2070";
    private static final String UID = "spiderman";
    private static final String LOCALE = "de";
    private static final String IP = "109.235.143.113";
    private static final String OFFER_TYPE = "112";
//    private static final String BASE_URL = "http://api.fyber.com/feed/v1/offers.json?";
    private static final String API_KEY = "1c915e3b5d42d05136185030892fbb846c278927";
    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fyber.com/feed/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final ApiService apiService = retrofit.create(ApiService.class);

        // DATE and TIME configs
        final Date date = new Date();
//        final long timeLong = date.getTime();
        final long timeLong = System.currentTimeMillis() / 1000;

        final String time = String.valueOf(timeLong);
        final String release = Build.VERSION.RELEASE;
        Log.i(TAG, " Time : " + time
                + " Current : " + System.currentTimeMillis()
                + " Date : " + DateFormat.getDateFormat(this).format(new Date(timeLong))
                + " release : " + release);

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("appid", APP_ID);
        stringMap.put("timestamp", time);
        stringMap.put("uid", UID);
        stringMap.put("appid", APP_ID);
        stringMap.put("ip", IP);
        stringMap.put("locale", LOCALE);
        stringMap.put("offer_types", OFFER_TYPE);
//         print map before sort
        mapLogger(stringMap);
        SortedMap sortedMap = new TreeMap(stringMap);
//         print map after sort
        mapLogger(sortedMap);
//         add sorted key value pairs with defined characters
        final StringBuilder builder = makeStringFromMap(sortedMap);
//        append API KEY before generate hash key

        builder.append(API_KEY);
        Log.i(TAG, "  <<<<<<< " + builder.toString() + " >>>>>> ");
//        generate SHA1 bytes from string of query params
        final String hashKey = generateSHA1FromString(builder.toString());
        Log.i(TAG, "<*<*<*<" + hashKey + " >*>*>*>");

        sortedMap.put("hashkey", hashKey);
        apiService.getListWithParams(sortedMap).enqueue(new Callback<Offers>() {

            private Fragment fragment;

            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                    if (fragment != null) {
                        ((OffersFragment) fragment).updateList(response.body());
                    }

                } else {
                    try {
                        Log.e(TAG, " error " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {
                t.printStackTrace();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * To generate Hash Key for API request, must generate string of sorted key value pairs with added API key at the end
     * and after that must create SHA1 bytes
     *
     * @param text
     * @return String
     */
    private String generateSHA1FromString(String text) {
        Log.d(TAG, "generateSHA1FromString() called with: " + "text = [" + text + "]");
        StringBuffer stringBuffer = new StringBuffer();
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            final byte[] digest = messageDigest.digest(text.getBytes());
            for (int i = 0; i < digest.length; i++) {
                stringBuffer.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * To make String from map
     *
     * @param map
     */
    private StringBuilder makeStringFromMap(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            builder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return builder;

    }

    /**
     * To print map into Log
     *
     * @param map
     */
    private void mapLogger(Map<String, String> map) {
        Log.i(TAG, " ------------------------------------------");
        for (Map.Entry entry : map.entrySet()) {
            Log.i(TAG, entry.getKey() + " : " + entry.getValue());

        }
        Log.i(TAG, " ------------------------------------------");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
