package com.k1.fyber;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.k1.fyber.api.ApiService;
import com.k1.fyber.model.OffersData;

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
        apiService.getListWithParams(sortedMap).enqueue(new Callback<OffersData>() {

            private Fragment fragment;

            @Override
            public void onResponse(Call<OffersData> call, Response<OffersData> response) {
                Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    if (response.body().getOffers().isEmpty()) { // check the offers list to handle empty list
                        try {
                            showNoOfferDialog(response.body());
                            return;
                        } catch (Exception e) { // Ops, something getting wrong
                            e.printStackTrace();
                        }
                    }
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
            public void onFailure(Call<OffersData> call, Throwable t) {
                t.printStackTrace();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() called with: " + "view = [" + view + "]");
                GetParameterDialogFragment.newInstance().show(getSupportFragmentManager(), GetParameterDialogFragment.FRAGMENT_NAME);
            }
        });
    }

    /**
     * When there aren't any {@link com.k1.fyber.model.Offer} in received {@link OffersData} from API
     * just show an {@link android.support.v7.app.AlertDialog} to user
     *
     * @param offersData
     */
    private void showNoOfferDialog(OffersData offersData) throws Exception {
        Log.d(TAG, "showNoOfferDialog() called with: " + "offersData = [" + offersData + "]");
        if (!offersData.getOffers().isEmpty()) {
            throw new Exception("OOPPPSS, something get it wrong, Please concentrate on your code !!!");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Opps there is not any Offers now Server Response is : " + offersData.getMessage()
                + " Code : " + offersData.getCode()
                + " and Count is : " + offersData.getCode()
        );
        builder.setCancelable(true);
        builder.show();
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

    /**
     * to get (uid, API Key, appid, pub0) parameters from user
     */
    public static class GetParameterDialogFragment extends DialogFragment {

        public static final String FRAGMENT_NAME = GetParameterDialogFragment.class.getName();
        private View root;
        private Button mOkButton;
        private Button mFillButton;
        private Button mTryButton;

        public static GetParameterDialogFragment newInstance() {
            Bundle args = new Bundle();
            GetParameterDialogFragment fragment = new GetParameterDialogFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_get_parameter, container, false);
            getDialog().setCancelable(false);
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            mOkButton = (Button) root.findViewById(R.id.get_ok_button);
            mTryButton = (Button) root.findViewById(R.id.get_try_with_values);
            mTryButton.setOnClickListener(new OnTryClickListener());
            mFillButton = (Button) root.findViewById(R.id.get_fill_values_button);
            mFillButton.setOnClickListener(new OnFillClickListener());
            mOkButton.setOnClickListener(new OnOkClickListener());

            return root;
        }


        /**
         * When Try button clicked, check the fields values and if there isn't any problem
         * must go on
         */
        private static class OnTryClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() called with: " + "view = [" + view + "]");
            }
        }

        /**
         * When fill button clicked, just fill fields with predefined values
         */
        private static class OnFillClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() called with: " + "view = [" + view + "]");
            }
        }

        /**
         * When ok button clicked, just try to make request with predefined values as documentaion
         */
        private static class OnOkClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() called with: " + "view = [" + view + "]");
            }
        }
    }
}
