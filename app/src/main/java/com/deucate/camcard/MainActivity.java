package com.deucate.camcard;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.deucate.camcard.capture.CaptureFragment;
import com.deucate.camcard.crop.CropFragment;
import com.otaliastudios.cameraview.Facing;

public class MainActivity extends AppCompatActivity {

  private Boolean isCapturing = true;
  public static CropFragment cropFragment = new CropFragment();
  public static ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    progressDialog = new ProgressDialog(this);
    progressDialog.setTitle("Loading");
    progressDialog.setMessage("Capturing image.");
    progressDialog.setCancelable(false);

    final ImageView captureIV = findViewById(R.id.captureImageView);
    ImageView flashIV = findViewById(R.id.flashImageView);
    ImageView settingIV = findViewById(R.id.settingImageView);
    TextView frontTV = findViewById(R.id.frontTextView);
    TextView backTV = findViewById(R.id.backTextView);

    final CaptureFragment captureFragment = new CaptureFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().add(R.id.container, captureFragment, null).commit();

    captureIV.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isCapturing) {
          captureFragment.onClickCapture();
          isCapturing = false;
          captureIV.setImageResource(R.drawable.ic_done_black_24dp);
        } else {
          cropFragment.onClickCrop();
        }
      }
    });
    flashIV.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        captureFragment.onClickFlash();
      }
    });
    settingIV.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        captureFragment.onClickSettings();
      }
    });
    frontTV.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        captureFragment.onChangeCameraView(Facing.FRONT);
      }
    });
    backTV.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        captureFragment.onChangeCameraView(Facing.BACK);
      }
    });

  }
}
