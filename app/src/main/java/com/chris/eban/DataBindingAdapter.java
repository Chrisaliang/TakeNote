package com.chris.eban;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

@BindingMethods({
        @BindingMethod(type = View.class, attribute = "isGone", method = "bindIsGone")
})
public class DataBindingAdapter {

    @BindingAdapter("isGone")
    public static void bindIsGone(View view, boolean isGone) {
        if (isGone) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
