package ve.com.xv_dev.todolist.models;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Map;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {

    private ObjectId _id;
    private Map<String, Long> date;
    private String note;
    private Boolean executed;

    public Todo(ObjectId _id, Map<String, Long> date, String note, Boolean executed) {
        this._id = _id;
        this.date = date;
        this.note = note;
        this.executed = executed;
    }

    public Todo() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Map<String, Long> getDate() {
        return date;
    }

    public void setDate(Map<String, Long> date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }
}
