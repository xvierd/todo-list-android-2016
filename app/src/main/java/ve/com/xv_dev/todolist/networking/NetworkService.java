package ve.com.xv_dev.todolist.networking;



import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import ve.com.xv_dev.todolist.models.Todo;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
public interface NetworkService {

    @GET("api/todo")
    Observable<List<Todo>> getTodoList();
    /*@POST("app/users")
    Observable<List<User>> getCityList();

    @FormUrlEncoded
    @POST("api/v1/user/login")
    Observable<User> loginUser(@FieldMap Map<String, String> fields);*/

}
