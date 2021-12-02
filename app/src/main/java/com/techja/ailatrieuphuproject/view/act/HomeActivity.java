package com.techja.ailatrieuphuproject.view.act;


import android.util.Log;

import com.techja.ailatrieuphuproject.App;
import com.techja.ailatrieuphuproject.databinding.ActivityHomeBinding;
import com.techja.ailatrieuphuproject.db.entities.Question;
import com.techja.ailatrieuphuproject.view.fragment.M000SplashFrg;
import com.techja.ailatrieuphuproject.viewmodel.CommonVM;

import java.util.List;

public class HomeActivity extends BaseAct<ActivityHomeBinding, CommonVM> {
    private static final String TAG = HomeActivity.class.getName();
    private List<Question> questionList;
    private void queryDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                questionList=App.getInstance().getDb().questionDAO().getAllQuestions();
                App.getStorage().questionList=questionList;
            }
        }).start();
    }
    @Override
    public void backToPrevious() {
        onBackPressed();
    }

    @Override
    protected void initViews() {
        showFragment(M000SplashFrg.TAG, null, false);
        queryDB();
    }

    @Override
    protected ActivityHomeBinding initViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }


    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }
}