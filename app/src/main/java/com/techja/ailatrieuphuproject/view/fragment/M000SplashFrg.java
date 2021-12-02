package com.techja.ailatrieuphuproject.view.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.techja.ailatrieuphuproject.databinding.M000SplashFrgBinding;
import com.techja.ailatrieuphuproject.viewmodel.CommonVM;


public class M000SplashFrg extends BaseFragment<M000SplashFrgBinding, CommonVM> {
    public static final String TAG = M000SplashFrg.class.getName();

    @Override
    protected void initViews() {
        Log.i(TAG, "initViews...");
        new Handler().postDelayed(this::gotoMainScreen, 2000);
    }

    private void gotoMainScreen() {
        callBack.showFragment(M001HomeFrg.TAG,null,false);

    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M000SplashFrgBinding initViewBinding(@NonNull LayoutInflater inflater,
                                                   @Nullable ViewGroup container) {
        return M000SplashFrgBinding.inflate(inflater, container, false);
    }
}
