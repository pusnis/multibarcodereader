package lt.pusnis.multibarcodereader.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lt.pusnis.multibarcodereader.model.MbrResults;

public class ResultsResponse {
    @SerializedName("data")
    private List<MbrResults> resultData;

    // constructor, getters and setters

    public List<MbrResults> getResultData() {
        return resultData;
    }

    public void setResultData(List<MbrResults> resultData) {
        this.resultData = resultData;
    }

    public String toString() {
        return "Result API response [data=" + resultData + "]";
    }

}
