package lt.pusnis.multibarcodereader.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import lt.pusnis.multibarcodereader.common.network.RemoteRepository;
import lt.pusnis.multibarcodereader.model.MbrResults;


public class MainViewModel extends ViewModel {

    private LiveData<List<MbrResults>> allResults;
    private final RemoteRepository remoteRepository;


    public MainViewModel() {
        this.remoteRepository = new RemoteRepository();
    }

    public void setDevice_id(String device_id) {
        this.allResults = remoteRepository.getAllResults(device_id);
    }

    public LiveData<List<MbrResults>> getAllResultsObservable() {
        return allResults;
    }

    public void getAllResults(String device_id){
        remoteRepository.getAllResults(device_id);
    }

}
