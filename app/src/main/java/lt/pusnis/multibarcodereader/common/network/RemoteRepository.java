package lt.pusnis.multibarcodereader.common.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.model.MbrFormats;
import lt.pusnis.multibarcodereader.model.MbrTypes;
import lt.pusnis.multibarcodereader.response.FormatsResponse;
import lt.pusnis.multibarcodereader.response.TypesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    MbrDataService service;

    public RemoteRepository() {
        this.service = MbrServiceClient.getUserInstance().create(MbrDataService.class);
    }

    public LiveData<List<MbrFormats>> getFormatList(){

        MutableLiveData<List<MbrFormats>> data = new MutableLiveData<>();

        service.getFormatList()
                .enqueue (new Callback<FormatsResponse>() {
                    @Override
                    public void onResponse(Call<FormatsResponse> call, Response<FormatsResponse> response) {
                        if (response.isSuccessful()){
                            data.setValue(response.body().getFormatData());
                        }
                    }

                    @Override
                    public void onFailure(Call<FormatsResponse> call, Throwable t) {
                        Log.i(Constants.LOG_TAG, "Failed to retrieve data" + t.getMessage());
                        call.cancel();
                    }
                });

        return data;
    }

    public LiveData<List<MbrTypes>> getTypeList(){

        MutableLiveData<List<MbrTypes>> data = new MutableLiveData<>();

        service.getTypeList()
                .enqueue (new Callback<TypesResponse>() {
                    @Override
                    public void onResponse(Call<TypesResponse> call, Response<TypesResponse> response) {
                        if (response.isSuccessful()){
                            data.setValue(response.body().getTypeData());
                        }
                    }

                    @Override
                    public void onFailure(Call<TypesResponse> call, Throwable t) {
                        Log.i(Constants.LOG_TAG, "Failed to retrieve data" + t.getMessage());
                        call.cancel();
                    }
                });

        return data;
    }

    public void getAllFormats(){
        Log.i(Constants.LOG_TAG,"Formatų duomenys.");
        Call<FormatsResponse> call = service.getFormatList();


        Callback<FormatsResponse> callback = new Callback<FormatsResponse>() {
            @Override
            public void onResponse(Call<FormatsResponse> call, Response<FormatsResponse> response) {
                Log.i(Constants.LOG_TAG,""+response.body());
            }

            @Override
            public void onFailure(Call<FormatsResponse> call, Throwable t) {
                Log.i(Constants.LOG_TAG,"Failed on retrieve data: "+ t.getMessage());
                call.cancel();
            }
        };

        call.enqueue(callback);
    }

    public void getAllTypes(){
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
    }
}
