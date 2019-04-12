package com.deucate.camcard.capture;


import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProviders;
import com.deucate.camcard.R;
import com.deucate.camcard.SharedViewModel;
import com.otaliastudios.cameraview.Audio;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.PictureResult;

public class CaptureFragment extends Fragment implements CameraCallback {

  public CaptureFragment() {
  }

  private CameraView cameraView;
  private SharedViewModel viewModel;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_capture, container, false);
    cameraView = rootView.findViewById(R.id.camera);
    cameraView.setAudio(Audio.OFF);
    cameraView.setLifecycleOwner(getViewLifecycleOwner());

    viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

    cameraView.addCameraListener(new CameraListener() {
      @Override
      public void onPictureTaken(@NonNull PictureResult result) {
        super.onPictureTaken(result);

        result.toBitmap(new BitmapCallback() {
          @Override
          public void onBitmapReady(@Nullable Bitmap bitmap) {
            viewModel.capture(bitmap);
          }
        });
      }
    });
    return rootView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    cameraView.setLifecycleOwner(getViewLifecycleOwner());
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

  @Override
  public void onResume() {
    super.onResume();
    cameraView.open();
  }

  @Override
  public void onPause() {
    super.onPause();
    cameraView.close();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    cameraView.destroy();
  }
}
