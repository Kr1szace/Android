package nik.oe.hu.vsa;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScanBarcodeActivity extends Activity {
    SurfaceView cameraPreview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
            cameraPreview = (SurfaceView) findViewById(R.id.camera_preview);
            createCameraSource();


    }

    private void createCameraSource() {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).build();
        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(ScanBarcodeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Camera start problem", e.getMessage());
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                //Ez a rész még egyenlőre hónap vége
                   // surfaceHolder.setFormat(i);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                   if (barcodes.size() > 0) {

                     Intent intent = new Intent();
                   intent.putExtra("barcode", barcodes.valueAt(0)); //get latest barcode from the array
                 setResult(CommonStatusCodes.SUCCESS, intent);
                finish();


               // if (barcodes.size() != 0)

               // {
               //     final StringBuilder sb = new StringBuilder();
               //     for (int i = 0; i < barcodes.size(); ++i) {
               //         sb.append(barcodes.valueAt(i).rawValue).append("\n");
                //    }
                 //   final TextView alma=(TextView)findViewById(R.id.barcode_result);
                 //   alma.post(new Runnable() {
                 //       @Override
                 //       public void run() {
                 //           alma.setText(sb.toString());
                 //       }
                 }

               // }
            }

    });
}
}
