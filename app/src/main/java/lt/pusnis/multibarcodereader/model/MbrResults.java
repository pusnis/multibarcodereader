package lt.pusnis.multibarcodereader.model;

import com.google.gson.annotations.SerializedName;

public class MbrResults {
    @SerializedName("id")
    private int id;
    @SerializedName("device_id")
    private String device_id;
    @SerializedName("code_format")
    private int code_format;
    @SerializedName("code_type")
    private int code_type;
    @SerializedName("result")
    private String result;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;

    // constructor, getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getCode_format() {
        return code_format;
    }

    public void setCode_format(int code_format) {
        this.code_format = code_format;
    }

    public int getCode_type() {
        return code_type;
    }

    public void setCode_type(int code_type) {
        this.code_type = code_type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "mbrResult [id=" + id + ", "
                + "device_id=" + device_id
                + "code_format=" + code_format
                + "code_type=" + code_type
                + "result=" + result
                + "createdAt=" + createdAt
                + "]";
    }
}
