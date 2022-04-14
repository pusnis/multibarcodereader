package lt.pusnis.multibarcodereader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCameraActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "Capture_Save_Show_Image";
    private ImageView imageView;
    private Button captureButton;
    private Button selectButton;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }

        imageView = (ImageView) this.findViewById(R.id.imageView1);
        setCaptureButton();
        setSelectButton();
    }

    private void setCaptureButton() {
        selectButton = (Button) this.findViewById(R.id.button1);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });
    }

    private void setSelectButton() {
        captureButton = (Button) this.findViewById(R.id.button2);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///storage/self/primary/Android/data/lt.pusnis.multibarcodereader/files/Pictures/JPEG_20220413_195912_1042563541446792019.jpg
                writeLog("Select button clicked");

                Intent intent = new Intent();
                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture", PICK_IMAGE);
                intent.setAction(Intent.ACTION_GET_CONTENT);


                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                writeLog("Select picture intent opened.");
            }
        });
    }


    private void galleryAddPic() {
        writeLog("galleryAddPic() - start; filePAth: " + currentPhotoPath);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
        writeLog("galleryAddPic() - finsh");
    }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

//        try {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        } catch (ActivityNotFoundException e) {
//            Log.e("REQUEST_IMAGE_CAPTURE", e.toString());
//        }
        writeLog("captureImage() - Start");
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("REQUEST_IMAGE_CAPTURE", ex.toString());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
            //writeLog("captureImage() - Finish");
            galleryAddPic();
        }


    }

    private File createImageFile() throws IOException {

        writeLog("createImageFile() - start");
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); //
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        writeLog("createImageFile() - finish; filePath: " + currentPhotoPath);
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        writeLog("onActivityResult() - start");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(photo);
            Uri selectedImage = data.getData();

            if ( currentPhotoPath == null){
//                String prefix = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
                currentPhotoPath = selectedImage.toString();
            }

            setPic();
            writeLog("onActivityResult() - finish. currentPhotoPath : "+ currentPhotoPath);
        }
    }

    private void setPic() {
        writeLog("setPic() - start");
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        //imageView = null;
        imageView.setImageBitmap(bitmap);
        writeLog("setPic() - finish");
    }

    private void writeLog(String txt) {
        //Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
        Log.i(TAG, txt);
    }

}