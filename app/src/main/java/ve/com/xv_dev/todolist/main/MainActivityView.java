package ve.com.xv_dev.todolist.main;

import java.util.ArrayList;
import java.util.List;

import ve.com.xv_dev.todolist.TodoList.TodoList;
import ve.com.xv_dev.todolist.models.Todo;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
public interface MainActivityView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void todoListResponse(ArrayList<Todo> todoList);

    void initRecycler();

    void toastMessage(String message);

    void reloadList(Todo todo);

}
