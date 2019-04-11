package com.deucate.camcard;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CropActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_crop);

    Bitmap bitmap = getIntent().getParcelableExtra("bitmap");
    ImageView imageView = findViewById(R.id.cropImageView);

    imageView.setImageBitmap(bitmap);

  }
}
