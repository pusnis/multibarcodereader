package lt.pusnis.multibarcodereader.common.network;


import lt.pusnis.multibarcodereader.model.MbrResults;
import lt.pusnis.multibarcodereader.response.FormatsResponse;
import lt.pusnis.multibarcodereader.response.ResultsResponse;
import lt.pusnis.multibarcodereader.response.TypesResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MbrDataService {
//    @GET("/api/formatlist")
//    Call<FormatsResponse> getFormatList();
//
//    @GET("/api/typelist")
//    Call<TypesResponse> getTypeList();

    //f823e692a7f7a008"
    @GET("/api/results/{device_id}")
    Call<ResultsResponse> getAllResults(
            @Path("device_id") String device_id
    );

    @POST("api/result")
    Call<MbrResults> createMbrResult(
             @Body MbrResults mbrResults
    );


//    @GET("/api/users")
//    Call<UsersResponse> getAllUsers(
//            @Query(value = "page") int page,
//            @Query(value = "per_page") int per_page
//    );
}
