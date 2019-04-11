package com.deucate.camcard.capture;

import com.otaliastudios.cameraview.Facing;

public interface CameraCallback {

  void onClickFlash();

  void onClickSettings();

  void onChangeCameraView(Facing facing);

  void onClickCapture();

}
