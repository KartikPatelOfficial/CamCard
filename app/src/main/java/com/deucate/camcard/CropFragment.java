package com.deucate.camcard;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CropFragment extends Fragment {
  public CropFragment() { }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_crop, container, false);

    CropImageView cropImageView = rootView.findViewById(R.id.cropImageView);

    cropImageView.getCroppedImageAsync();

    return rootView;
  }

}
