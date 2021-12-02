package com.techja.ailatrieuphuproject.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.techja.ailatrieuphuproject.view.OnMainCallBack;


public abstract class BaseFragment<B extends ViewBinding, V extends ViewModel>
        extends Fragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.class.getName();
    protected Context context;
    protected B binding;
    protected V viewModel;
    protected OnMainCallBack callBack;
    protected Object mData;

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {

        binding = initViewBinding(inflater, container);
        viewModel = new ViewModelProvider(this).get(getClassViewModel());
        initViews();
        return binding.getRoot();
    }

    @Override
    public final void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);
    }

    protected void clickView(View view) {
        //do nothing

    }

    public final void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    protected abstract void initViews();

    protected abstract Class<V> getClassViewModel();

    protected abstract B initViewBinding(@NonNull LayoutInflater inflater,
                                         @Nullable ViewGroup container);

    public void setData(Object data) {
        mData = data;
    }
}
