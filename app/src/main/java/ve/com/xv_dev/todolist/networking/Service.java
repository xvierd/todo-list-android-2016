package ve.com.xv_dev.todolist.networking;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.*;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import ve.com.xv_dev.todolist.models.Todo;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getTodoList(final GetTodoListCallback callback) {

        return networkService.getTodoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ArrayList<Todo>>>() {
                    @Override
                    public Observable<? extends ArrayList<Todo>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ArrayList<Todo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ArrayList<Todo> todoList) {
                        callback.onSuccess(todoList);

                    }
                });
    }

    public Subscription editTodo(final EditTodoCallback callback, Map<String, Object> data) {

        return networkService.editTodo(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(String response) {
                        callback.onSuccess(response);

                    }
                });
    }

   /* public Subscription loginUser(final LoginUserCallback callback, Map<String, String> data) {

        return networkService.loginUser(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends User>>() {
                    @Override
                    public Observable<? extends User> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(User user) {
                        callback.onSuccess(user);
                    }
                });
    }

    public interface LoginUserCallback {
        void onSuccess(User user);

        void onError(NetworkError throwable);
    }*/

    public interface GetTodoListCallback{
        void onSuccess(ArrayList<Todo> todoList);
        void onError(NetworkError networkError);
    }
    public interface EditTodoCallback{
        void onSuccess(String response);
        void onError(NetworkError networkError);
    }
}
