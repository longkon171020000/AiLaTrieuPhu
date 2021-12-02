package com.techja.ailatrieuphuproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.techja.ailatrieuphuproject.App;
import com.techja.ailatrieuphuproject.db.entities.Question;

import java.util.List;

public class M002PlayerVM extends BaseViewModel{
    private static final String TAG = M002PlayerVM.class.getName();
    private Thread th;
    private List<Question> questionList;
    private MutableLiveData<Integer> timeData=new MutableLiveData<>(10);
    private Question question;
    public MutableLiveData<Integer> getTimeData() {
        return timeData;
    }


    public List<Question> getQuestionList() {
        return questionList;
    }



    public void countTime(){
        th=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=30;i>=0;i--){
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                        break;
                    }
                    timeData.postValue(i);
                }
            }
        });
        th.start();
    }
    public void interruptCountime(){
        th.interrupt();
    }
}
