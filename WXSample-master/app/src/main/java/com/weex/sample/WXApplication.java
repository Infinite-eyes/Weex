package com.weex.sample;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.weex.sample.extend.adapter.ImageAdapter;
import com.weex.sample.extend.adapter.WxImageAdapter;
import com.weex.sample.extend.compontent.RichText;
import com.weex.sample.extend.compontent.WxTextView;
import com.weex.sample.extend.module.MyModule;
import com.weex.sample.extend.module.NetworkModule;

/**
 * 注意要在Manifest中启用
 * 参考manifest，否则会抛出ExceptionInInitializerError
 * 要实现ImageAdapter 否则图片不能下载
 * gradle 中一定要添加一些依赖，否则初始化会失败。
 * compile 'com.android.support:recyclerview-v7:23.1.1'
 * compile 'com.android.support:support-v4:23.1.1'
 * compile 'com.android.support:appcompat-v7:23.1.1'
 * compile 'com.alibaba:fastjson:1.1.45'
 */
public class WXApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
//    InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();

    InitConfig config = new InitConfig.Builder().setImgAdapter(new WxImageAdapter()).build();
    WXSDKEngine.initialize(this, config);


    engineRegister();

    if(Constants.isDebug) {
      initDebugEnvironment(true, true, Constants.host);
    }


  }

  private void engineRegister(){
    try {
      WXSDKEngine.registerModule("network", NetworkModule.class);
      WXSDKEngine.registerComponent("rich", RichText.class, false);
      WXSDKEngine.registerComponent("wxtextview", WxTextView.class, false);
      WXSDKEngine.registerModule("myModule", MyModule.class);
    } catch (WXException e) {
      e.printStackTrace();
    }

  }




  /**
   *@param connectable debug server is connectable or not.
   *               if true, sdk will try to connect remote debug server when init WXBridge.
   *
   * @param debuggable enable remote debugger. valid only if host not to be "DEBUG_SERVER_HOST".
   *               true, you can launch a remote debugger and inspector both.
   *               false, you can  just launch a inspector.
   * @param host the debug server host, must not be "DEBUG_SERVER_HOST", a ip address or domain will be OK.
   *             for example "127.0.0.1".
   */
  private void initDebugEnvironment(boolean connectable,boolean debuggable, String host) {
//    WXEnvironment.sDebugServerConnectable = connectable;
      WXEnvironment.sRemoteDebugMode = debuggable;
      WXEnvironment.sRemoteDebugProxyUrl = "ws://" + host + ":8088/debugProxy/native";
      WXSDKEngine.reload();//引擎初始化之后调用（after WXSDKEngine.initialize）

      /**
       *
       * initialize -> doInitInternal -> WXBridgeManager.getInstance()  ->new WXBridgeManager()
       * ->initWXBridge(WXEnvironment.sRemoteDebugMode);
       *
       */
  }


}
