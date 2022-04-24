package lt.pusnis.multibarcodereader.common.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.model.MbrFormats;
import lt.pusnis.multibarcodereader.model.MbrResults;
import lt.pusnis.multibarcodereader.model.MbrTypes;
import lt.pusnis.multibarcodereader.response.FormatsResponse;
import lt.pusnis.multibarcodereader.response.ResultsResponse;
import lt.pusnis.multibarcodereader.response.TypesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    MbrDataService service;

    public RemoteRepository() {
        this.service = MbrServiceClient.getUserInstance().create(MbrDataService.class);
    }

    public LiveData<List<MbrResults>> getAllResults(String device_id) {
        final MutableLiveData<List<MbrResults>> data = new MutableLiveData<>();
        service.getAllResults(device_id)
                .enqueue(new Callback<ResultsResponse>() {
                    @Override
                    public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                        if (response.isSuccessful()){
                            data.postValue(response.body().getResultData());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultsResponse> call, Throwable t) {
                        Log.i(Constants.LOG_TAG,"ERROR getAllResults: "+ t.getMessage());
                    }
                });
        return data;
    }

    public void postResult(MbrResults mbrResult){
        service.createMbrResult(mbrResult).
                enqueue(new Callback<MbrResults>() {
                    @Override
                    public void onResponse(Call<MbrResults> call, Response<MbrResults> response) {
                        Log.i(Constants.LOG_TAG,response.message());
                    }

                    @Override
                    public void onFailure(Call<MbrResults> call, Throwable t) {
                        Log.i(Constants.LOG_TAG,"Error on postResult: "+t.getMessage());
                    }
                });

    }

//    public LiveData<List<MbrFormats>> getFormatList() {
//
//        MutableLiveData<List<MbrFormats>> data = new MutableLiveData<>();
//
//        service.getFormatList()
//                .enqueue(new Callback<FormatsResponse>() {
//                    @Override
//                    public void onResponse(Call<FormatsResponse> call, Response<FormatsResponse> response) {
//                        if (response.isSuccessful()) {
//                            data.setValue(response.body().getFormatData());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<FormatsResponse> call, Throwable t) {
//                        Log.i(Constants.LOG_TAG, "Failed to retrieve data" + t.getMessage());
//                        call.cancel();
//                    }
//                });
//
//        return data;
//    }
//
//    public LiveData<List<MbrTypes>> getTypeList() {
//
//        MutableLiveData<List<MbrTypes>> data = new MutableLiveData<>();
//
//        service.getTypeList()
//                .enqueue(new Callback<TypesResponse>() {
//                    @Override
//                    public void onResponse(Call<TypesResponse> call, Response<TypesResponse> response) {
//                        if (response.isSuccessful()) {
//                            data.setValue(response.body().getTypeData());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<TypesResponse> call, Throwable t) {
//                        Log.i(Constants.LOG_TAG, "Failed to retrieve data" + t.getMessage());
//                        call.cancel();
//                    }
//                });
//
//        return data;
//    }
//
//    public LiveData<List<MbrTypes>> getAllTypes(){
//        final MutableLiveData<List<MbrTypes>> data = new MutableLiveData<>();
//        service.getTypeList().enqueue(new Callback<TypesResponse>() {
//            @Override
//            public void onResponse(Call<TypesResponse> call, Response<TypesResponse> response) {
//                if (response.isSuccessful()) {
//                    data.postValue(response.body().getTypeData());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TypesResponse> call, Throwable t) {
//
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<MbrFormats>> getAllFormats() {
//        final MutableLiveData<List<MbrFormats>> data = new MutableLiveData<>();
//        service.getFormatList()
//                .enqueue(new Callback<FormatsResponse>() {
//                    @Override
//                    public void onResponse(Call<FormatsResponse> call, Response<FormatsResponse> response) {
//                        if (response.isSuccessful()){
//                            data.postValue(response.body().getFormatData());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<FormatsResponse> call, Throwable t) {
//
//                    }
//                });
//        return data;
//    }

}
