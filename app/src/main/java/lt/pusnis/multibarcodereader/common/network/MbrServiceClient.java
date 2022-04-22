package lt.pusnis.multibarcodereader.common.network;

import lt.pusnis.multibarcodereader.common.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MbrServiceClient {
    private static Retrofit retrofit = null;

    public static synchronized Retrofit getUserInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
