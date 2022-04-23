package lt.pusnis.multibarcodereader.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lt.pusnis.multibarcodereader.common.network.RemoteRepository;
import lt.pusnis.multibarcodereader.model.MbrFormats;
import lt.pusnis.multibarcodereader.model.MbrResults;
import lt.pusnis.multibarcodereader.model.MbrTypes;

public class MainViewModel extends ViewModel {

//    private LiveData<List<MbrFormats>> allFormats;
//    private LiveData<List<MbrTypes> >allTypes;

    private RemoteRepository remoteRepository;
    private LiveData<List<MbrResults>> allResults;



//    public MainViewModel() {
//        this.remoteRepository = new RemoteRepository();
//        this.allResults = remoteRepository.getAllResults(device_id);
//        this.allFormats = remoteRepository.getAllFormats();
//        this.allTypes = remoteRepository.getAllTypes();
//    }

    public void setDevice_id(String device_id) {
        this.remoteRepository = new RemoteRepository();
        this.allResults = remoteRepository.getAllResults(device_id);
    }

    public LiveData<List<MbrResults>> getAllResultsObservable() {
        return allResults;
    }


    //    public LiveData<List<MbrFormats>> getAllFormatsObservable() {
//        return allFormats;
//    }
//    public LiveData<List<MbrTypes>> getAllTypesObservable() {
//        return allTypes;
//    }
//
//    public void getAllFormats() {
//        remoteRepository.getAllFormats();
//    }
//    public void getAllTypes() {
//        remoteRepository.getAllTypes();
//    }

}
