package lt.pusnis.multibarcodereader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.common.network.MbrDataService;
import lt.pusnis.multibarcodereader.common.network.MbrServiceClient;
import lt.pusnis.multibarcodereader.common.network.RemoteRepository;
import lt.pusnis.multibarcodereader.model.MbrFormats;
import lt.pusnis.multibarcodereader.response.FormatsResponse;
import lt.pusnis.multibarcodereader.response.TypesResponse;
import lt.pusnis.multibarcodereader.viewmodels.MainViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    MaterialButton mBtn;
    MainViewModel mainViewModel;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUi();


        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpObserve(mainViewModel);


        setUpBtnTakePhoto();

        Toast.makeText(this, getDeviceId(this), Toast.LENGTH_LONG).show();
        Log.i(Constants.LOG_TAG, "Pabaiga.");
    }

    private void setUpUi() {
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setUpObserve(MainViewModel viewModel) {
        viewModel.getAllFormatsObservable().observe(
                this, mbrFormats ->
                        Log.i(Constants.LOG_TAG, "___" + mbrFormats));

        viewModel.getAllTypesObservable().observe(
                this, mbrTypes ->
                        Log.i(Constants.LOG_TAG, "___" + mbrTypes));

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