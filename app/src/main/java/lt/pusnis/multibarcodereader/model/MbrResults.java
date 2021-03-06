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

    @SerializedName("codeformat")
    CodeFormat codeFormat;
    @SerializedName("codetype")
    CodeType  codeType;

    public class CodeFormat extends MbrResults{
        String description;

        public String getDescription() {
            return description;
        }
    }

    public class CodeType extends MbrResults{
        String description;

        public String getDescription() {
            return description;
        }
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }


    public void setCode_format(int code_format) {
        this.code_format = code_format;
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

    public CodeFormat getCodeFormat() {
        return codeFormat;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public int getCode_format() {
        return code_format;
    }

    public int getCode_type() {
        return code_type;
    }

    @Override
    public String toString() {
        return "mbrResult [id=" + id + ", "
                + "device_id=" + device_id+ ", "
                + "code_format=" + code_format+ ", "
                + "code_type=" + code_type+ ", "
                + "result=" + result+ ", "
                + "codeFormat=" + codeFormat.description+ ", "
                + "codeType=" + codeType.description+ ", "
                + "]";
    }
}
