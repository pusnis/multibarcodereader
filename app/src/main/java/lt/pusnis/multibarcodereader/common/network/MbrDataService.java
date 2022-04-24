package lt.pusnis.multibarcodereader.common.network;


import lt.pusnis.multibarcodereader.model.MbrResults;
import lt.pusnis.multibarcodereader.response.ResultsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MbrDataService {
    @GET("/api/results/{device_id}")
    Call<ResultsResponse> getAllResults(
            @Path("device_id") String device_id
    );

    @POST("api/result")
    Call<MbrResults> createMbrResult(
             @Body MbrResults mbrResults
    );

}
