package lt.pusnis.multibarcodereader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import java.util.Collections;
import java.util.List;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.model.MbrFormats;
import lt.pusnis.multibarcodereader.viewadapters.ResultsAdapter;
import lt.pusnis.multibarcodereader.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {


    MaterialButton mBtn;
    MainViewModel mainViewModel;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    ResultsAdapter resultsAdapter;
    List<MbrFormats> list = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUi();

        setUpRecycleView();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.setDevice_id(getDeviceId(this));
        setUpObserve(mainViewModel);


        setUpBtnTakePhoto();

        Toast.makeText(this, getDeviceId(this), Toast.LENGTH_LONG).show();
        Log.i(Constants.LOG_TAG, "Pabaiga.");
    }

    private void setUpRecycleView() {
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        resultsAdapter = new ResultsAdapter(list, getApplication());
        recyclerView.setAdapter(resultsAdapter);
    }

    private void setUpUi() {
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setUpObserve(MainViewModel viewModel) {

        viewModel.getAllResultsObservable().observe(
                this, mbrResults -> {
                    Log.i(Constants.LOG_TAG, "___" + mbrResults);
                    resultsAdapter.addList(mbrResults);
                    progressBar.setVisibility(View.INVISIBLE);
                }
        );

//                viewModel.getAllFormatsObservable().observe(
//                this, mbrFormats -> {
//                    Log.i(Constants.LOG_TAG, "___" + mbrFormats);
//                    formatsAdapter.addList(mbrFormats);
//                    progressBar.setVisibility(View.INVISIBLE);
//                }
//        );
//
//
//        viewModel.getAllTypesObservable().observe(
//                this, mbrTypes ->
//                        Log.i(Constants.LOG_TAG, "___" + mbrTypes));

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