package com.weex.sample.extend.module;

import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

/**
 * Created by cwm02 on 2017/2/27.
 */

public class MyModule extends WXModule {

    @JSMethod( uiThread = true)
    public void printLog(String msg){
        Toast.makeText(mWXSDKInstance.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

}
