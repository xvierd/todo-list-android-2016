package ve.com.xv_dev.todolist.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ve.com.xv_dev.todolist.models.Todo;
import ve.com.xv_dev.todolist.networking.NetworkError;
import ve.com.xv_dev.todolist.networking.Response;
import ve.com.xv_dev.todolist.networking.Service;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
public class MainActivityPresenter {

    private final Service service;
    private final MainActivityView view;
    private CompositeSubscription subscriptions;

    public MainActivityPresenter(Service service, MainActivityView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getTodoList() {
        view.showWait();
        view.initRecycler();
        Subscription subscription = service.getTodoList(new Service.GetTodoListCallback() {
            @Override
            public void onSuccess(ArrayList<Todo> todoList) {
                view.removeWait();
                view.todoListResponse(todoList);
            }

            @Override
            public void onError(NetworkError networkError) {
                // HttpException httpException = (HttpException) networkError.getError();
                view.onFailure(networkError.getMessage());
                view.removeWait();
            }

        });

        subscriptions.add(subscription);
    }

    public void editTodo(Map<String, Object> data) {


        view.showWait();
        Subscription subscription = service.editTodo(new Service.EditTodoCallback() {

            @Override
            public void onSuccess(retrofit2.Response response) {
                view.removeWait();
            }

            @Override
            public void onError(NetworkError networkError) {
                // HttpException httpException = (HttpException) networkError.getError();
                view.onFailure(networkError.getMessage());
                view.removeWait();
            }

        }, data);

        subscriptions.add(subscription);
    }

    public void saveTodo(Map<String, Object> data) {


        view.showWait();
        Subscription subscription = service.saveTodo(new Service.SaveTodoCallback() {

            @Override
            public void onSuccess(Todo todo) {
                view.removeWait();
                view.toastMessage("Save success");
            }

            @Override
            public void onError(NetworkError networkError) {
                // HttpException httpException = (HttpException) networkError.getError();
                view.onFailure(networkError.getMessage());
                view.removeWait();
            }

        }, data);

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
