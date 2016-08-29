package com.k1.fyber;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.k1.fyber.callback.GetParameterDialogFragmentCallback;
import com.k1.fyber.model.Parameters;

/**
 * to get (uid, API Key, appid, pub0) parameters from user
 */
public class GetParameterDialogFragment extends DialogFragment {

    public static final String FRAGMENT_NAME = GetParameterDialogFragment.class.getName();
    private View root;
    private Parameters mParameters;
    private GetParameterDialogFragmentCallback callback;

    public static GetParameterDialogFragment newInstance() {
        Bundle args = new Bundle();
        GetParameterDialogFragment fragment = new GetParameterDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GetParameterDialogFragmentCallback) {
            callback = (GetParameterDialogFragmentCallback) context;
        } else {
            throw new ClassCastException(context + " must implemented GetParameterDialogFragmentCallback interface !!!");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_parameter, container, false);
        mParameters = new Parameters();
        dataBinding.setVariable(com.k1.fyber.BR.parameters, mParameters);
        dataBinding.setVariable(com.k1.fyber.BR.parametersCallback, callback);
        root = dataBinding.getRoot();
        getDialog().setCancelable(false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return root;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
