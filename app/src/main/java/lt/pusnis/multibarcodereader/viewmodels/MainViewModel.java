package lt.pusnis.multibarcodereader.viewmodels;

import androidx.lifecycle.ViewModel;

import lt.pusnis.multibarcodereader.common.network.RemoteRepository;

public class MainViewModel extends ViewModel {

    RemoteRepository remoteRepository = new RemoteRepository();

    public void fetchAllFormats(){
        remoteRepository.getAllFormats();
    }
    public void fetchAllTypes() {
        remoteRepository.getAllTypes();
    }
}
