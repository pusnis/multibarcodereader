package lt.pusnis.multibarcodereader.common.network;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.model.MbrResults;
import lt.pusnis.multibarcodereader.response.ResultsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private final MbrDataService service;
    private MutableLiveData<List<MbrResults>> data = new MutableLiveData<>();

    public RemoteRepository() {
        this.service = MbrServiceClient.getUserInstance().create(MbrDataService.class);
    }

    public LiveData<List<MbrResults>> getAllResults(String device_id) {

        service.getAllResults(device_id)
                .enqueue(new Callback<ResultsResponse>() {
                    @Override
                    public void onResponse(
                            @NonNull Call<ResultsResponse> call,
                            @NonNull Response<ResultsResponse> response
                    ) {
                        if (response.isSuccessful()) {
                            data.postValue(response.body().getResultData());
                            call.cancel();
                        }
                    }

                    @Override
                    public void onFailure(
                            @NonNull Call<ResultsResponse> call,
                            @NonNull Throwable t
                    ) {
                        Log.i(Constants.LOG_TAG, "ERROR getAllResults: " + t.getMessage());
                        call.cancel();
                    }
                });
        Log.i(Constants.LOG_TAG, "Data retrieved from api.");
        return data;
    }

    public void postResult(MbrResults mbrResult) {
        service.createMbrResult(mbrResult).
                enqueue(new Callback<MbrResults>() {
                    @Override
                    public void onResponse(
                            @NonNull Call<MbrResults> call,
                            @NonNull Response<MbrResults> response
                    ) {
                        Log.i(Constants.LOG_TAG, response.message());
                        call.cancel();
                    }

                    @Override
                    public void onFailure(
                            @NonNull Call<MbrResults> call,
                            @NonNull Throwable t
                    ) {
                        Log.i(Constants.LOG_TAG, "Error on postResult: " + t.getMessage());
                        call.cancel();
                    }
                });
    }
}
