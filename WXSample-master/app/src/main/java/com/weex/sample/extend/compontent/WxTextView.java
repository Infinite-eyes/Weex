package com.weex.sample.extend.compontent;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import org.w3c.dom.Text;

/**
 * Created by cwm02 on 2017/3/6.
 */

public class WxTextView  extends WXComponent<TextView>{

   public WxTextView(WXSDKInstance instance,WXDomObject dom, WXVContainer parent){
       super(instance,dom,parent);
   }

    @Override
    protected TextView initComponentHostView(@NonNull Context context) {
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        textView.setTextColor(Color.BLACK);

        return textView;
    }

    @WXComponentProp(name = "tel" )
    public void setTel(String number){
        SpannableString spannableString = new SpannableString(number);
        spannableString.setSpan(new URLSpan("tel:"+number), 0, number.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getHostView().setText(spannableString);
    }

    @JSMethod(uiThread = true)
    public void focus(){
       System.out.print("------focus------");
    }
}
