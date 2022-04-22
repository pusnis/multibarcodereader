package lt.pusnis.multibarcodereader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
        return "Users API response [data=" + typeData + "]";
    }

}
