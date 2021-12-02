package com.techja.ailatrieuphuproject.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.techja.ailatrieuphuproject.MediaManager;
import com.techja.ailatrieuphuproject.R;
import com.techja.ailatrieuphuproject.databinding.M001HomeFrgBinding;
import com.techja.ailatrieuphuproject.viewmodel.M001HomeVM;

public class M001HomeFrg extends BaseFragment<M001HomeFrgBinding, M001HomeVM> {
    public static final String TAG = M001HomeFrg.class.getName();
    @Override
    protected void initViews() {
        MediaManager.getInstance().playBgPlayer(R.raw.bgmusic, true);
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(M001BackgroundPoitnFrg.TAG,null,false);
            }
        });
    }

    @Override
    protected Class<M001HomeVM> getClassViewModel() {
        return M001HomeVM.class;
    }

    @Override
    protected M001HomeFrgBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M001HomeFrgBinding.inflate(inflater,container,false);
    }
}
