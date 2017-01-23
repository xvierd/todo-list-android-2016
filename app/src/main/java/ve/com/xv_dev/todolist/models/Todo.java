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

    private Map<String, String> _id;
    private Map<String, Long> date;
    private String note;
    private Boolean executed;

    public Todo(Map<String, String> _id, Map<String, Long> date, String note, Boolean executed) {
        this._id = _id;
        this.date = date;
        this.note = note;
        this.executed = executed;
    }

    public Todo() {
    }

    public Map<String, String> get_id() {
        return _id;
    }

    public void set_id(Map<String, String> _id) {
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
