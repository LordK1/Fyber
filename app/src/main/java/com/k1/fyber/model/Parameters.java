package com.k1.fyber.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.k1.fyber.BR;
import com.k1.fyber.callback.ParameterTextWatcher;

import org.parceler.Parcel;

/**
 * Created by K1 on 8/28/16.
 */
@Parcel
public class Parameters extends BaseObservable {

    private static final String TAG = Parameters.class.getSimpleName();
    private String mUid;
    private String mApiKey;
    private String mAppId;
    private String mPub;

    public Parameters() {
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "mUid='" + mUid + '\'' +
                ", mApiKey='" + mApiKey + '\'' +
                ", mAppId='" + mAppId + '\'' +
                ", mPub='" + mPub + '\'' +
                '}';
    }

    @Bindable
    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
        notifyPropertyChanged(BR.uid);
    }

    @Bindable
    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String mApiKey) {
        this.mApiKey = mApiKey;
        notifyPropertyChanged(BR.apiKey);
    }

    @Bindable
    public String getAppId() {
        return mAppId;
    }

    public void setAppId(String mAppId) {
        this.mAppId = mAppId;
        notifyPropertyChanged(BR.appId);
    }

    @Bindable
    public String getPub() {
        return mPub;
    }

    public void setPub(String mPub) {
        this.mPub = mPub;
        notifyPropertyChanged(BR.pub);
    }

    /**
     * to set {@link Parameters#setUid(String)}
     *
     * @return
     */
    public TextWatcher getUidTextWatcher() {
        return new ParameterTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setUid(String.valueOf(charSequence));
            }
        };
    }

    /**
     * to set for {@link Parameters#setAppId(String)}
     *
     * @return
     */
    public TextWatcher getAppIdTextWatcher() {
        return new ParameterTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setAppId(String.valueOf(charSequence));
            }
        };
    }


    /**
     * to set value for {@link Parameters#setApiKey(String)}
     *
     * @return
     */
    public TextWatcher getAppKeyTextWatcher() {
        return new ParameterTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setApiKey(String.valueOf(charSequence));
            }
        };
    }


    /**
     * to set {@link Parameters#setPub(String)}
     *
     * @return
     */
    public TextWatcher getPubTextWatcher() {
        return new ParameterTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setPub(String.valueOf(charSequence));
            }
        };
    }

    /**
     * Just check for empty or null characters
     *
     * @return
     */
    public boolean isValid() {
        return !TextUtils.isEmpty(this.mApiKey) & !TextUtils.isEmpty(this.mAppId) & !TextUtils.isEmpty(this.getUid());
    }
}
