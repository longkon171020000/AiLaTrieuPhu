package com.techja.ailatrieuphuproject.view.fragment;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.techja.ailatrieuphuproject.App;
import com.techja.ailatrieuphuproject.MediaManager;
import com.techja.ailatrieuphuproject.R;
import com.techja.ailatrieuphuproject.databinding.M002PlayerFrgBinding;
import com.techja.ailatrieuphuproject.db.entities.Question;
import com.techja.ailatrieuphuproject.viewmodel.M002PlayerVM;

import java.util.List;
import java.util.Random;

public class M002PlayerFrg extends BaseFragment<M002PlayerFrgBinding, M002PlayerVM> {
    public static final String TAG = M002PlayerFrg.class.getName();
    private int pos=1;
    private String[] score = {"200.000","400.000","600.000","1.000.000","2.000.000","3.000.000","6.000.000","10.000.000","14.000.000","22.000.000","30.000.000","40.000.000","80.000.000","85.000.000","120.000.000","150.000.000"};
    private TextView tvCase[];
    private Question questionChange;
    public Handler mHandler;
    private Random random;
    List<Question> questionList=App.getStorage().questionList;
    @Override
    protected void initViews() {
        tvCase=new TextView[4];
        tvCase[0]= binding.tvCaseA;
        tvCase[1]= binding.tvCaseB;
        tvCase[2]= binding.tvCaseC;
        tvCase[3]= binding.tvCaseD;
        random=new Random();
        countTime();
        setUpQuestion();
        excuteOnclick();
    }
    private void setClickAble(boolean b) {

        binding.tvCaseA.setClickable(b);
        binding.tvCaseB.setClickable(b);
        binding.tvCaseC.setClickable(b);
        binding.tvCaseD.setClickable(b);
        binding.btn5050.setClickable(b);
        binding.btnChange.setClickable(b);
    }

    private void excuteOnclick() {
        binding.tvCaseA.setOnClickListener(view -> checkAnswer(view,R.raw.ans_a,R.raw.true_a,"1"));
        binding.tvCaseB.setOnClickListener(view -> checkAnswer(view,R.raw.ans_b,R.raw.true_b,"2"));
        binding.tvCaseC.setOnClickListener(view -> checkAnswer(view,R.raw.ans_c,R.raw.true_c,"3"));
        binding.tvCaseD.setOnClickListener(view -> checkAnswer(view,R.raw.ans_d,R.raw.true_d2,"4"));
        binding.btnChange.setOnClickListener(view -> {
            binding.btnChange.setEnabled(false);
            disPlayDialogChange();
        });
        binding.btn5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btn5050.setEnabled(false);
                do5050Question();
            }
        });
    }

    private void do5050Question() {
        MediaManager.getInstance().voiceSong(R.raw.sound5050, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                int count=0;
                int index=0;
                while(count<2){
                    int temp=random.nextInt(4)+1;
                    if(temp!=Integer.parseInt(App.getStorage().questionList.get(pos).truecase)&&temp!=index){
                        index=temp;
                            tvCase[index-1].setEnabled(false);
                            tvCase[index-1].setBackgroundResource(R.drawable.player_answer_background_hide);
                            tvCase[index-1].setText("");
                            count++;
                    }
                }
            }
        });
    }

    private void disPlayDialogChange() {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Annoucement");
        builder.setMessage("Bạn có chắc chắn muốn đổi câu hỏi ?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               changeQuestion();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        builder.show();
    }

    private void changeQuestion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                questionChange=App.getInstance().getDb().questionDAO().getQuestionByLevel(pos);
                App.getStorage().questionList.get(pos).question=questionChange.question;
                Log.i(TAG,"questionchangetrong app"+App.getStorage().questionList.get(pos).question);
                App.getStorage().questionList.get(pos).casea=questionChange.casea;
                App.getStorage().questionList.get(pos).caseb=questionChange.caseb;
                App.getStorage().questionList.get(pos).casec=questionChange.casec;
                App.getStorage().questionList.get(pos).cased=questionChange.cased;
                App.getStorage().questionList.get(pos).truecase=questionChange.truecase;
                mHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        setUpQuestion();
                    }
                };
//                new Handler(new Handler.Callback() {
//                    @Override
//                    public void handleMessage(Message message) {
//                        setUpQuestion();
//                    }
//                });
            }
        }).start();
    }

    private void setUpQuestion() {
        binding.tvQuestion.setText(App.getStorage().questionList.get(pos).question);
        binding.tvCaseA.setText(String.format("A. %s",(App.getStorage().questionList.get(pos).casea)));
        binding.tvCaseB.setText(String.format("B. %s",(App.getStorage().questionList.get(pos).caseb)));
        binding.tvCaseC.setText(String.format("C. %s",(App.getStorage().questionList.get(pos).casec)));
        binding.tvCaseD.setText(String.format("D. %s",(App.getStorage().questionList.get(pos).cased)));

    }
    private void setBackgroundStart(){
        binding.tvCaseA.setBackgroundResource(R.drawable.btn_answer);
        binding.tvCaseB.setBackgroundResource(R.drawable.btn_answer);
        binding.tvCaseC.setBackgroundResource(R.drawable.btn_answer);
        binding.tvCaseD.setBackgroundResource(R.drawable.btn_answer);
    }
    private void checkAnswer(View view, int ans, int trueans, String selectQuestion) {
        setClickAble(false);
        view.setBackgroundResource(R.drawable.player_answer_background_selected);

        MediaManager.getInstance().voiceSong(ans, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                MediaManager.getInstance().stopvoiceBG();
                    if(selectQuestion.equals(App.getStorage().questionList.get(pos).truecase)){
                        viewModel.interruptCountime();
                        answerTrue(view,trueans);
                    }else{
                        Log.i(TAG,"Ban chon sai");
                        viewModel.interruptCountime();
                        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("Annoucement");
                        builder.setMessage("Bạn có muốn chơi tiếp với chúng tôi không ?");
                        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                callBack.showFragment(M002PlayerFrg.TAG,null,false);
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                callBack.showFragment(M001HomeFrg.TAG,null,false);
                            }
                        });
                        builder.show();

                    }
            }
        });
    }

    private void answerTrue(View view, int trueans) {
        MediaManager.getInstance().voiceSong(trueans, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                setBackgroundStart();
                binding.tvMoney.setText(score[pos-1]);
                Log.i(TAG,"vi tri diem"+score[Integer.parseInt(questionList.get(pos).level)]);
                viewModel.countTime();
                pos++;
                setUpQuestion();
                setClickAble(true);
            }
        });
    }

    @Override
    public void onStop() {
        MediaManager.getInstance().pauseBG();
        super.onStop();
    }

    @Override
    public void onStart() {
        MediaManager.getInstance().playBG();
        super.onStart();
    }


    private void countTime() {
        viewModel.countTime();
        viewModel.getTimeData().observe(this, i -> binding.tvTimer.setText(String.format("%s", i)));
    }


    @Override
    protected Class<M002PlayerVM> getClassViewModel() {
        return M002PlayerVM.class;
    }

    @Override
    protected M002PlayerFrgBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M002PlayerFrgBinding.inflate(inflater, null, false);
    }
}
