package lt.pusnis.multibarcodereader;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import java.io.IOException;
import java.util.List;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.common.network.RemoteRepository;
import lt.pusnis.multibarcodereader.model.MbrResults;

public class MyCameraActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_SELECT = 1;
    private static final String TAG = "Capture_Save_Show_Image";
    private ImageView imageView;
    private InputImage image;
    String device_id;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Button readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_id=getIntent().getExtras().getString(Constants.EXTRA_KEY_USER_ID);
        Log.i(Constants.LOG_TAG,"MyCameraActivity get deviec_id: "+device_id);

        setContentView(R.layout.activity_my_camera);


        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }

        imageView = (ImageView) this.findViewById(R.id.imageView1);

        setSelectButton();
        setRedBarcodesButton();

    }

    private void setRedBarcodesButton() {
        readButton = (Button) this.findViewById(R.id.button1);
        readButton.setVisibility(View.INVISIBLE);
        readButton.setOnClickListener(v -> getBarCodes());
    }


    private void setSelectButton() {
        Button captureButton = (Button) this.findViewById(R.id.button2);
        captureButton.setOnClickListener(v -> {
            ///storage/self/primary/Android/data/lt.pusnis.multibarcodereader/files/Pictures/JPEG_20220413_195912_1042563541446792019.jpg
            writeLog("Select button clicked");

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(intent, REQUEST_IMAGE_SELECT );
            writeLog("Select picture intent opened.");
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        writeLog("onActivityResult() - start");
        super.onActivityResult(requestCode, resultCode, data);
        writeLog("Intent : "+ requestCode + " resultCode: "+resultCode ) ;
        if (  requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();

            writeLog(requestCode + " selectedImage : "+ selectedImage);
            try {
                image = InputImage.fromFilePath(getApplicationContext(), selectedImage);
                imageView.setImageURI(selectedImage);

                readButton.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
                readButton.setVisibility(View.INVISIBLE);
            }
            writeLog("onActivityResult() - finish. ");

        }

    }

    private void getBarCodes() {

        BarcodeScannerOptions options =
                new BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_ALL_FORMATS
                                )
                        .build();

        BarcodeScanner scanner = BarcodeScanning.getClient(options);

        Task<List<Barcode>> result = scanner.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                    @Override
                    public void onSuccess(List<Barcode> barcodes) {
                        postResults(barcodes);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        writeLog("Klaida : " + e.getMessage());
                    }
                });
    }

    private void postResults(List<Barcode> barcodes) {
        writeLog("Nuskaityta kod??: "+ barcodes.size());
        Toast.makeText(getApplicationContext(),"Nuskaityta kod??: "+ barcodes.toArray().length,Toast.LENGTH_LONG).show();

        RemoteRepository service = new RemoteRepository();

        int i =0;
        for (Barcode barkodas: barcodes){
            i++;
            writeLog("Nuskaitytas kodas: "+i+". " + barkodas.getFormat()+ " / " + barkodas.getValueType() +" : "+barkodas.getDisplayValue());

            MbrResults mbrResult = new MbrResults();

            mbrResult.setDevice_id(device_id);
            mbrResult.setCode_format(barkodas.getFormat());
            mbrResult.setCode_type(barkodas.getValueType());
            mbrResult.setResult(barkodas.getDisplayValue());

            service.postResult(mbrResult);
        }
    }

    private void writeLog(String txt) {
        //Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
        Log.i(TAG, txt);
    }

}