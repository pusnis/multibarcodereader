package lt.pusnis.multibarcodereader.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lt.pusnis.multibarcodereader.model.MbrFormats;

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
        return "API response Formtas [data=" + formatData+ "]";
    }
}

