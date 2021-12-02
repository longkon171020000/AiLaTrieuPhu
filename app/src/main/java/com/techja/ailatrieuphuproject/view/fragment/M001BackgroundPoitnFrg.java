package com.techja.ailatrieuphuproject.view.fragment;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.techja.ailatrieuphuproject.MediaManager;
import com.techja.ailatrieuphuproject.R;
import com.techja.ailatrieuphuproject.databinding.M001BackgroundPointFrgBinding;
import com.techja.ailatrieuphuproject.viewmodel.CommonVM;

public class M001BackgroundPoitnFrg extends BaseFragment<M001BackgroundPointFrgBinding, CommonVM> {
    public static final String TAG = M001BackgroundPoitnFrg.class.getName();
    @Override
    protected void initViews() {
        MediaManager.getInstance().voiceSong(R.raw.sound_rule, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                MediaManager.getInstance().voiceSong(R.raw.ready,null);
                disPlayDialogReady();
            }
        });
        binding.btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(M002PlayerFrg.TAG,null,false);
            }
        });
    }

    private void disPlayDialogReady() {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Annoucement");
        builder.setMessage("Bạn đã sẵn sàng chơi với chúng tôi ?");
        builder.setPositiveButton("Sẵn sàng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MediaManager.getInstance().stopBG();
                callBack.showFragment(M002PlayerFrg.TAG,null,false);
            }
        });
        builder.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                callBack.showFragment(M001HomeFrg.TAG,null,false);
            }
        });
        builder.show();
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M001BackgroundPointFrgBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M001BackgroundPointFrgBinding.inflate(inflater,container,false);
    }
}
