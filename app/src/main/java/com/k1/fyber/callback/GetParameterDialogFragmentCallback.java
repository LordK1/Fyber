package com.k1.fyber.callback;

import com.k1.fyber.GetParameterDialogFragment;
import com.k1.fyber.model.Parameters;

/**
 * To make communication between {@link com.k1.fyber.MainActivity } & {@link GetParameterDialogFragment}
 * Created by K1 on 8/28/16.
 */
public interface GetParameterDialogFragmentCallback {


    /**
     * When user wants to use the predefined values as documentation
     *
     * @param mParameters
     */
    void onTryWithPredefinedValues(Parameters mParameters);

    /**
     * When user filled all fields, set these fields as QueryMap of request
     *
     * @param mParameters
     */
    void onParametersUpdated(Parameters mParameters);
}
