package lt.pusnis.multibarcodereader.model;

import com.google.gson.annotations.SerializedName;

public class MbrFormats {
    @SerializedName("code_id")
    private int id;
    @SerializedName("description")
    private String description;

    // constructor, getters and setters


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "mbrFormat [id=" + id + ", "
                + "description=" + description +
                "]";
    }
}
