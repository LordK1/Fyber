package com.k1.fyber.callback;

import com.k1.fyber.model.Parameters;

/**
 * Created by K1 on 8/28/16.
 */
public interface GetParameterCallback {

    void onOkClicked(Parameters parameters);

    void onTryClicked(Parameters parameters);

    void onFillClicked(Parameters parameters);

}
