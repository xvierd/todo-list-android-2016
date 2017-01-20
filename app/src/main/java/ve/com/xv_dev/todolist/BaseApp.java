package ve.com.xv_dev.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import ve.com.xv_dev.todolist.TodoList.DaggerTodoList;
import ve.com.xv_dev.todolist.TodoList.TodoList;
import ve.com.xv_dev.todolist.networking.NetworkModule;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
public class BaseApp  extends AppCompatActivity {
    TodoList todoList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        todoList = DaggerTodoList.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public TodoList getTodoList() {
        return todoList;
    }
}
