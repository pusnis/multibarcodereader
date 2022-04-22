package lt.pusnis.multibarcodereader;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.common.network.MbrDataService;
import lt.pusnis.multibarcodereader.common.network.MbrServiceClient;
import lt.pusnis.multibarcodereader.response.FormatsResponse;
import lt.pusnis.multibarcodereader.response.TypesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    MaterialButton mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpBtnTakePhoto();

        Toast.makeText(this, getDeviceId(this), Toast.LENGTH_LONG).show();

        //prisijungiam prie API
        Log.i(Constants.LOG_TAG,"Laukiame duomenų.");
        MbrDataService service =
                MbrServiceClient.getUserInstance().create(MbrDataService.class);

//        Log.i(Constants.LOG_TAG,"Formatų duomenys.\n");
//        Call<FormatsResponse> call = service.getFormatList();
//
//
//        Callback<FormatsResponse> callback = new Callback<FormatsResponse>() {
//            @Override
//            public void onResponse(Call<FormatsResponse> call, Response<FormatsResponse> response) {
//                Log.i(Constants.LOG_TAG,""+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<FormatsResponse> call, Throwable t) {
//                Log.i(Constants.LOG_TAG,"Failed on retrieve data: "+ t.getMessage());
//                call.cancel();
//            }
//        };
//
//        call.enqueue(callback);

        Log.i(Constants.LOG_TAG,"Tipų duomenys. \n");
        Call<TypesResponse> call = service.getTypeList();


        Callback<TypesResponse> callback = new Callback<TypesResponse>() {
            @Override
            public void onResponse(Call<TypesResponse> call, Response<TypesResponse> response) {
                Log.i(Constants.LOG_TAG,""+response.body());
            }

            @Override
            public void onFailure(Call<TypesResponse> call, Throwable t) {
                Log.i(Constants.LOG_TAG,"Failed on retrieve data: "+ t.getMessage());
            }
        };

        call.enqueue(callback);

        Log.i(Constants.LOG_TAG,"Pabaiga.");
    }

    private void setUpBtnTakePhoto() {
        mBtn = findViewById(R.id.mBtn);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyCameraActivity.class);
                startActivity(intent);

            }
        };
        mBtn.setOnClickListener(listener);
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}