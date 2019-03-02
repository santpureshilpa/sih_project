package com.example.signup.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.signup.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ScanActivity extends AppCompatActivity{

    ImageView qrImage;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        qrImage = findViewById(R.id.imageView);
        generateAndShowQRCode();
    }

    public void generateAndShowQRCode(){
        QRGEncoder qrgEncoder = new QRGEncoder("distributor", null, QRGContents.Type.TEXT, 500);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v("ScanActivity", e.toString());
        }
    }




}
