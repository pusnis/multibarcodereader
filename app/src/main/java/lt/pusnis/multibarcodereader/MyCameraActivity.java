package lt.pusnis.multibarcodereader;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyCameraActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_SELECT = 1;
    private static final String TAG = "Capture_Save_Show_Image";
    private ImageView imageView;
    private InputImage image;
    private Uri selectedImage;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }

        imageView = (ImageView) this.findViewById(R.id.imageView1);

        setSelectButton();
        setRedBarcodesButton();

    }

    private void setRedBarcodesButton() {
        Button readButton = (Button) this.findViewById(R.id.button1);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBarCodes();
            }
        });
    }


    private void setSelectButton() {
        Button captureButton = (Button) this.findViewById(R.id.button2);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///storage/self/primary/Android/data/lt.pusnis.multibarcodereader/files/Pictures/JPEG_20220413_195912_1042563541446792019.jpg
                writeLog("Select button clicked");

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(intent, REQUEST_IMAGE_SELECT );
                writeLog("Select picture intent opened.");
            }
        });
    }



//    private void captureImage_01() {
//        ImageCapture imageCapture =
//                new ImageCapture.Builder()
//                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
//                        .build();
//
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        ImageCapture.OutputFileOptions outputFileOptions =
//                new ImageCapture.OutputFileOptions.Builder(new File(imageFileName)).build();
//
//        imageCapture.takePicture(outputFileOptions, cameraExecutor,
//                new ImageCapture.OnImageSavedCallback() {
//                    @Override
//                    public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
//                        // insert your code here.
//                    }
//                    @Override
//                    public void onError(ImageCaptureException error) {
//                        // insert your code here.
//                    }
//                }
//        );
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        writeLog("onActivityResult() - start");
        super.onActivityResult(requestCode, resultCode, data);
        writeLog("Intent : "+ requestCode + " resultCode: "+resultCode ) ;
        if (  requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK) {
            selectedImage = data.getData();

            writeLog(requestCode + " selectedImage : "+ selectedImage);
            try {
                image = InputImage.fromFilePath(getApplicationContext(), selectedImage);
                imageView.setImageURI(selectedImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
            writeLog("onActivityResult() - finish. ");

        }

    }

    private void getBarCodes() {

        BarcodeScannerOptions options =
                new BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_CODE_39
                                )
                        .build();

        BarcodeScanner scanner = BarcodeScanning.getClient(options);

        Task<List<Barcode>> result = scanner.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                    @Override
                    public void onSuccess(List<Barcode> barcodes) {
                        writeLog("Nuskaityta kodų: "+barcodes.size());
                        Toast.makeText(getApplicationContext(),"Nuskaityta kodų: "+barcodes.toArray().length,Toast.LENGTH_LONG).show();

                        int i =0;
                        for (Barcode barkodas:barcodes){
                            i++;
                            writeLog("Nuskaitytas kodas: "+i+". " + barkodas.getFormat()+ " : "+barkodas.getDisplayValue());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ...
                        writeLog("Klaida : " + e.getMessage());
                    }
                });
    }

    private void writeLog(String txt) {
        //Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
        Log.i(TAG, txt);
    }

}