package lt.pusnis.multibarcodereader.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lt.pusnis.multibarcodereader.common.Constants;
import lt.pusnis.multibarcodereader.common.network.MbrDataService;
import lt.pusnis.multibarcodereader.common.network.MbrServiceClient;
import lt.pusnis.multibarcodereader.common.network.RemoteRepository;
import lt.pusnis.multibarcodereader.model.MbrFormats;
import lt.pusnis.multibarcodereader.response.FormatsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private LiveData<List<MbrFormats>> formatsFromRepository;
    private RemoteRepository remoteRepository;

    public MainViewModel() {
        this.remoteRepository = new RemoteRepository();
        this.formatsFromRepository = remoteRepository.getAllFormats();
    }

    public LiveData<List<MbrFormats>> getAllFormatsObservable() {
        return formatsFromRepository;
    }

    public void getAllFormats() {
        remoteRepository.getAllFormats();
    }

}
