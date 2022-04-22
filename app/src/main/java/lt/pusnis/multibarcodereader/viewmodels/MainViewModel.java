package lt.pusnis.multibarcodereader.viewmodels;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.common.network.MbrDataService;
import lt.pusnis.multibarcodereader.common.network.MbrServiceClient;
import lt.pusnis.multibarcodereader.model.MbrFormats;
import lt.pusnis.multibarcodereader.response.FormatsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<MbrFormats>> formats;


    public void fetchAllFormats(){
        Log.i(Constants.LOG_TAG,"Formatų duomenys.");
        MbrDataService service = MbrServiceClient.getUserInstance().create(MbrDataService.class);

        Call<FormatsResponse> call = service.getFormatList();
        Callback<FormatsResponse> callback = new Callback<FormatsResponse>() {
            @Override
            public void onResponse(Call<FormatsResponse> call, Response<FormatsResponse> response) {
                Log.i(Constants.LOG_TAG, "" + response.body());
                formats.postValue(response.body().getFormatData());
            }

            @Override
            public void onFailure(Call<FormatsResponse> call, Throwable t) {
                Log.i(Constants.LOG_TAG, "Failed on retrieve data: " + t.getMessage());
                call.cancel();
            }
        };
        call.enqueue(callback);
    }

//    public void fetchAllTypes() {
////        remoteRepository.getAllTypes();
//        Log.i(Constants.LOG_TAG,"Tipų duomenys. \n");
//        Call<TypesResponse> call = service.getTypeList();
//
//
//        Callback<TypesResponse> callback = new Callback<TypesResponse>() {
//            @Override
//            public void onResponse(Call<TypesResponse> call, Response<TypesResponse> response) {
//                Log.i(Constants.LOG_TAG,""+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<TypesResponse> call, Throwable t) {
//                Log.i(Constants.LOG_TAG,"Failed on retrieve data: "+ t.getMessage());
//            }
//        };
//
//        call.enqueue(callback);
//    }
}
