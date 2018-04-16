package nik.oe.hu.vsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class CameraActivityMain extends Activity {
    TextView barcodeResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    barcodeResult.setText("Barcode value: "+barcode.displayValue);
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
