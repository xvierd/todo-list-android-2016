package ve.com.xv_dev.todolist.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ve.com.xv_dev.todolist.models.Todo;
import ve.com.xv_dev.todolist.networking.NetworkError;
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
        Subscription subscription = service.getTodoList(new Service.GetTodoListCallback() {
            @Override
            public void onSuccess(List<Todo> todoList) {
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

    /*public void getTodoList() {
        view.showWait();
        Map<String, String> data = new HashMap<>();
        data.put("email", "joanny@k.com");
        data.put("password", "123456");
        Subscription subscription = service.getTodoList(new Service.GetTodoListCallback() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                view.removeWait();
                view.todoListResponse(todoList);
            }


            @Override
            public void onError(NetworkError networkError) {
                // HttpException httpException = (HttpException) networkError.getError();
                view.onFailure(networkError.getMessage());
                view.removeWait();
            }

        }, data);*/

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
