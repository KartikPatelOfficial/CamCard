package com.deucate.camcard;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.otaliastudios.cameraview.Audio;
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
        Toast.makeText(MainActivity.this, result.getSize().toString(), Toast.LENGTH_SHORT).show();
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
