package com.techja.ailatrieuphuproject.view.act;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;


import com.techja.ailatrieuphuproject.R;
import com.techja.ailatrieuphuproject.db.entities.Question;
import com.techja.ailatrieuphuproject.view.OnMainCallBack;
import com.techja.ailatrieuphuproject.view.fragment.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseAct<T extends ViewBinding, M extends ViewModel>
        extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    protected T binding;
    protected M viewModel;
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(initViewModel());
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract void initViews();

    protected abstract T initViewBinding();

    protected abstract Class<M> initViewModel();

    @Override
    public void onClick(View v) {
        //do nothing
    }

    protected final void notify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected final void notify(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag);  //Trỏ vào 1 fragment class
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) cons.newInstance(); //Tạo ra 1 thực thể từ lớp fragment
            frg.setData(data);
            frg.setCallBack(this);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            if (isBack) {
                trans.addToBackStack(null);//go back to previous screen
            }
            trans.replace(R.id.ln_main, frg, tag).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
