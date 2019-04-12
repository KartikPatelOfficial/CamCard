package com.deucate.camcard;


import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CropFragment extends Fragment {
  public CropFragment() { }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_crop, container, false);

    final CropImageView cropImageView = rootView.findViewById(R.id.cropImageView);
    SharedViewModel viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

    viewModel.getCaptured().observe(this, new Observer<Bitmap>() {
      @Override
      public void onChanged(Bitmap bitmap) {
        cropImageView.setImageBitmap(bitmap);
      }
    });

    cropImageView.getCroppedImageAsync();

    return rootView;
  }

}
