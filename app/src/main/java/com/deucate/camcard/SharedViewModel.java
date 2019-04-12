package com.deucate.camcard;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

  private final MutableLiveData<Bitmap> capturedImage = new MutableLiveData<>();

  public void capture(Bitmap bitmap){
    capturedImage.setValue(bitmap);
  }

  public LiveData<Bitmap> getCaptured(){
    return capturedImage;
  }

}
