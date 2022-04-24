package lt.pusnis.multibarcodereader.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import lt.pusnis.multibarcodereader.common.network.RemoteRepository;
import lt.pusnis.multibarcodereader.model.MbrResults;


public class MainViewModel extends ViewModel {

    private final RemoteRepository remoteRepository;
    private LiveData<List<MbrResults>> allResults;

    public MainViewModel() {
        this.remoteRepository = new RemoteRepository();
    }

    public void setDevice_id(String device_id) {
        this.allResults = remoteRepository.getAllResults(device_id);
    }

    public LiveData<List<MbrResults>> getAllResultsObservable() {
        return allResults;
    }

}
