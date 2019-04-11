package com.deucate.camcard.capture;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.deucate.camcard.R;
import com.otaliastudios.cameraview.Audio;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.PictureResult;

public class CaptureFragment extends Fragment implements CameraCallback {

  public CaptureFragment() {
  }

  private CameraView cameraView;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_capture, container, false);
    cameraView = rootView.findViewById(R.id.camera);
    cameraView.setAudio(Audio.OFF);
    cameraView.setLifecycleOwner(getViewLifecycleOwner());

    cameraView.addCameraListener(new CameraListener() {
      @Override
      public void onPictureTaken(@NonNull PictureResult result) {
        super.onPictureTaken(result);
      }
    });
    return rootView;
  }

  @Override
  public void onClickFlash() {
    if (cameraView.getFlash() == Flash.OFF) {
      cameraView.setFlash(Flash.ON);
    } else {
      cameraView.setFlash(Flash.OFF);
    }
  }

  @Override
  public void onClickSettings() {

  }

  @Override
  public void onChangeCameraView(Facing facing) {
    cameraView.setFacing(facing);
  }

  @Override
  public void onClickCapture() {
    cameraView.takePicture();
  }
}