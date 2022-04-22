package lt.pusnis.multibarcodereader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FormatsResponse {
    @SerializedName("data")
    private List<MbrFormats> formatData;

    // constructor, getters and setters

    public List<MbrFormats> getFormatData() {
        return formatData;
    }

    public void setFormatData(List<MbrFormats> formatData) {
        this.formatData = formatData;
    }

    public String toString() {
        return "Users API response [data=" + formatData+ "]";
    }
}

