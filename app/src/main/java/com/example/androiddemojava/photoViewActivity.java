package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class photoViewActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_FROM_ALBUM = 2;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        Button takePhoto=findViewById(R.id.take_photo);
        Button choose_from_album=findViewById(R.id.choose_from_album);
        imageView = findViewById(R.id.picture);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        choose_from_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPickFromGalleryIntent();
            }
        });
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchPickFromGalleryIntent() {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (pickPhotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_FROM_ALBUM);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                // Handle the captured image, for example, display it in an ImageView
                imageView.setImageBitmap(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_FROM_ALBUM) {
                // Handle the image picked from the gallery, for example, get the image URI
                Uri selectedImageUri = data.getData();
                // Do something with the URI
                imageView.setImageURI(selectedImageUri);
            }
        } else {
            Toast.makeText(this, "Action canceled", Toast.LENGTH_SHORT).show();
        }
    }
}