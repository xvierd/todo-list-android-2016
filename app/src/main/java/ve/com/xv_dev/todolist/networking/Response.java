package ve.com.xv_dev.todolist.networking;

import com.google.gson.annotations.SerializedName;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
public class Response {
    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }
}
