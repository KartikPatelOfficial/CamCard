package com.deucate.camcard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.otaliastudios.cameraview.Audio;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

public class MainActivity extends AppCompatActivity {

  private CameraView cameraView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    cameraView = findViewById(R.id.camera);
    cameraView.setAudio(Audio.OFF);
    cameraView.setLifecycleOwner(this);
    cameraView.addCameraListener(new CameraListener() {
      @Override
      public void onPictureTaken(@NonNull PictureResult result) {
        super.onPictureTaken(result);

        result.toBitmap(new BitmapCallback() {
          @Override
          public void onBitmapReady(@Nullable Bitmap bitmap) {
            if (bitmap != null){
              Intent intent = new Intent(MainActivity.this,CropActivity.class);
              intent.putExtra("bitmap",bitmap);
              startActivity(intent);
            }
          }
        });

        Toast.makeText(MainActivity.this, result.getSize().toString(), Toast.LENGTH_SHORT).show();
      }
    });

    findViewById(R.id.clickButton).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        cameraView.takePicture();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    cameraView.open();
  }

  @Override
  protected void onPause() {
    super.onPause();
    cameraView.close();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    cameraView.destroy();
  }
}
