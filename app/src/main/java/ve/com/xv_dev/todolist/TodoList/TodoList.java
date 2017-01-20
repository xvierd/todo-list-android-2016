package ve.com.xv_dev.todolist.TodoList;

import javax.inject.Singleton;

import dagger.Component;
import ve.com.xv_dev.todolist.main.MainActivity;
import ve.com.xv_dev.todolist.networking.NetworkModule;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface TodoList {
    void inject(MainActivity mainActivity);
}
