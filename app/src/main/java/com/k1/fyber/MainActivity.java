package com.k1.fyber;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.k1.fyber.api.ApiService;
import com.k1.fyber.api.ServiceGenerator;
import com.k1.fyber.callback.GetParameterCallback;
import com.k1.fyber.callback.GetParameterDialogFragmentCallback;
import com.k1.fyber.model.OffersData;
import com.k1.fyber.model.Parameters;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GetParameterDialogFragmentCallback, GetParameterCallback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APP_ID = "2070";
    private static final String UID = "spiderman";
    private static final String LOCALE = "de";
    private static final String IP = "109.235.143.113";
    private static final String OFFER_TYPE = "112";
    //    private static final String BASE_URL = "http://api.fyber.com/feed/v1/offers.json?";
    private static final String API_KEY = "1c915e3b5d42d05136185030892fbb846c278927";

    private static GetParameterDialogFragmentCallback callback;
    private OkHttpClient mClient;
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mApiService = ServiceGenerator.createService(ApiService.class);
        getOffers(null);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() called with: " + "view = [" + view + "]");
                showGetParameterDialog();
            }
        });
    }

    /**
     * Show {@link GetParameterDialogFragment}
     */
    private void showGetParameterDialog() {
        GetParameterDialogFragment.newInstance().show(getSupportFragmentManager(), GetParameterDialogFragment.FRAGMENT_NAME);
    }

    /**
     * to get {@link SortedMap<String,String>()} from received {@link Parameters} or
     * even though use predefined values as docs
     *
     * @param parameters
     * @return
     */
    @NonNull
    private SortedMap<String, String> getSortedMap(@Nullable Parameters parameters) {
        final TreeMap<String, String> mTreeMap = new TreeMap<>();
        if (parameters != null) {
            mTreeMap.put("appid", parameters.getAppId());
            mTreeMap.put("uid", parameters.getUid());
            mTreeMap.put("pub0", parameters.getPub());
        } else { // even we can use predefined values as docs
            mTreeMap.put("appid", APP_ID);
            mTreeMap.put("uid", UID);
            mTreeMap.put("pub0", "");
        }

        mTreeMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        mTreeMap.put("locale", LOCALE);
        mTreeMap.put("ip", IP);
        mTreeMap.put("offer_type", OFFER_TYPE);

        return mTreeMap;
    }

    /**
     * To use generated {@link SortedMap} from {@link Parameters} and send it as {@link retrofit2.http.QueryMap}
     * and return back data as {@link OffersData} with in {@link OffersDataCallback}
     *
     * @param parameters
     */
    private void getOffers(@Nullable Parameters parameters) {

        SortedMap sortedMap = getSortedMap(parameters);

        mapLogger(sortedMap);
//         add sorted key mParameters pairs with defined characters
        final StringBuilder builder = makeStringFromMap(sortedMap);
//        append API KEY before generate hash key
        if (parameters != null && !parameters.getApiKey().isEmpty())
            builder.append(parameters.getApiKey());
        else
            builder.append(API_KEY);
        Log.i(TAG, "  <<<<<<< " + builder.toString() + " >>>>>> ");

        final String hashKey = generateSHA1FromString(builder.toString());
        Log.i(TAG, "<*<*<*<" + hashKey + " >*>*>*>");

        sortedMap.put("hashkey", hashKey);
        mApiService.getListWithParams(sortedMap).enqueue(new OffersDataCallback());
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Opps there is not any Offers now Server Response message is : " + offersData.getMessage()
                        + "\nCode : " + offersData.getCode()
//                + " and \n Information is : " + offersData.getInformation()
        );
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new OnPositiveClickListener());
        builder.show();
    }

    /**
     * To generate Hash Key for API request, must generate string of sorted key mParameters pairs with added API key at the end
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
    public void onTryWithPredefinedValues(Parameters mParameters) {
        Log.d(TAG, "onTryWithPredefinedValues() called with: " + "mParameters = [" + mParameters + "]");
    }

    @Override
    public void onParametersUpdated(Parameters mParameters) {
        Log.d(TAG, "onParametersUpdated() called with: " + "mParameters = [" + mParameters + "]");

    }

    @Override
    public void onOkClicked(Parameters parameters) {
        Log.d(TAG, "onOkClicked() called with: " + "parameters = [" + parameters + "]"
                + " isValid : " + parameters.isValid()
        );
        if (parameters.isValid()) {
            dismissDialog();
            getOffers(parameters);
        } else {
            Toast.makeText(MainActivity.this, R.string.error_fields_are_not_valid, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onTryClicked(Parameters parameters) {
        Log.d(TAG, "onTryClicked() called with: " + "parameters = [" + parameters + "]");
        dismissDialog();
    }

    /**
     * to {@link GetParameterDialogFragment#dismiss()}
     */
    private void dismissDialog() {
        final DialogFragment fragmentByTag = (DialogFragment) getSupportFragmentManager().findFragmentByTag(GetParameterDialogFragment.FRAGMENT_NAME);
        if (fragmentByTag != null) {
            fragmentByTag.dismiss();
        }
    }

    @Override
    public void onFillClicked(Parameters parameters) {
        Log.d(TAG, "onFillClicked() called with: " + "parameters = [" + parameters + "]");
        parameters.setUid(UID);
        parameters.setAppId(APP_ID);
        parameters.setApiKey(API_KEY);
        parameters.setPub(""); // to prevent null pointer exception
    }

    private static class OnPositiveClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    /**
     * when response of {@link ApiService#getListWithParams(Map)} received
     */
    private class OffersDataCallback implements Callback<ResponseBody> {

        private static final String RESPONSE_SIGNATURE = "X-Sponsorpay-Response-Signature";
        private Fragment fragment;
        private boolean checkSignature;
        private String responseBody;

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                // To check signature of header and API Key with sha1 format
                final String signatureHeader = response.headers().get(RESPONSE_SIGNATURE);
                Log.i(TAG, " signatureHeader : " + signatureHeader);
                responseBody = response.body().string();
                final StringBuilder builder = new StringBuilder(responseBody).append(API_KEY);
                final String sha1FromString = generateSHA1FromString(builder.toString());
                Log.i(TAG, " sha1FromString : " + sha1FromString);
                checkSignature = signatureHeader.equals(sha1FromString);
                Log.i(TAG, String.format(" %s = %s => %s ", signatureHeader, sha1FromString, String.valueOf(checkSignature)));

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (checkSignature) { // signature is ok ?
                if (response.isSuccessful()) { // response is successful
                    final OffersData offersData = new GsonBuilder().create().fromJson(responseBody, OffersData.class);

                    if (offersData.getOffers().isEmpty()) { // check the offers list to handle empty list
                        try {
                            showNoOfferDialog(offersData); // show dialog if there isn't any offers
                            return;
                        } catch (Exception e) { // Ops, something getting wrong
                            e.printStackTrace();
                        }
                    }
                    fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                    if (fragment != null) {
                        ((OffersFragment) fragment).updateList(offersData);
                    }

                } else {
                    try {
                        Log.e(TAG, " error " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(MainActivity.this,
                        "Oopps, Signature of response is INCORRECT !!!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
        }
    }
}
