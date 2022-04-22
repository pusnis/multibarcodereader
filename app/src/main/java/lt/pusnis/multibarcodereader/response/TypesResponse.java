package lt.pusnis.multibarcodereader.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lt.pusnis.multibarcodereader.model.MbrTypes;

public class TypesResponse {
    @SerializedName("data")
    private List<MbrTypes> typeData;

    // constructor, getters and setters

    public List<MbrTypes> getTypeData() {
        return typeData;
    }

    public void setTypeData(List<MbrTypes> typeData) {
        this.typeData = typeData;
    }

    public String toString() {
        return "API response Types [data=" + typeData + "]";
    }

}
