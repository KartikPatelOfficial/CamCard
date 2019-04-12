package com.deucate.camcard;


import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.deucate.camcard.crop.CropCallback;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class CropFragment extends Fragment implements CropCallback {

  public CropFragment() {
  }

  private CropImageView cropImageView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_crop, container, false);

    cropImageView = rootView.findViewById(R.id.cropImageView);
    SharedViewModel viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

    viewModel.getCaptured().observe(this, new Observer<Bitmap>() {
      @Override
      public void onChanged(Bitmap bitmap) {
        cropImageView.setImageBitmap(bitmap);
      }
    });

    return rootView;
  }

  @Override
  public void onClickCrop() {
    Bitmap croppedImage = cropImageView.getCroppedImage();
    SaveImage(croppedImage);
  }

  private void SaveImage(Bitmap finalBitmap) {

    String root = Environment.getExternalStorageDirectory().toString();
    File myDir = new File(root + "/saved_images");
    if (!myDir.exists()) {
      myDir.mkdirs();
    }
    Random generator = new Random();
    int n = 10000;
    n = generator.nextInt(n);
    String fname = "Image-" + n + ".jpg";
    File file = new File(myDir, fname);
    if (file.exists()) {
      file.delete();
    }
    try {
      FileOutputStream out = new FileOutputStream(file);
      finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
      out.flush();
      out.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
