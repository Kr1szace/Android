package nik.oe.hu.vsa;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class CameraActivityMain extends Activity {
    TextView barcodeResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int cameraPermission = this.checkSelfPermission(Manifest.permission.CAMERA);
        if (ActivityCompat.checkSelfPermission(CameraActivityMain.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            finish();
        }

            setContentView(R.layout.camera_activity_main);
            barcodeResult = (TextView)findViewById(R.id.barcode_result);





    }

    /*add click event to the scan barcode button*/
    public  void scanBarcode(View v)
    {
        Intent intent = new Intent(this,ScanBarcodeActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0)
        {
            if(resultCode== CommonStatusCodes.SUCCESS)
            {
                if(data!=null)
                {
                    Barcode barcode=data.getParcelableExtra("barcode");
                    barcodeResult.setText("Az eredmény tesám: "+barcode.displayValue);
                }else
                    {
                        barcodeResult.setText("No barcode found");
                    }
            }
        }
        else
        {
        super.onActivityResult(requestCode, resultCode, data);}
    }
}
