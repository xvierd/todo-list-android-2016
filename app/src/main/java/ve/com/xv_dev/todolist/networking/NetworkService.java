package ve.com.xv_dev.todolist.networking;



import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.*;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import rx.Observable;
import ve.com.xv_dev.todolist.models.Todo;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
public interface NetworkService {

    @GET("api/todo")
    Observable<ArrayList<Todo>> getTodoList();

    @FormUrlEncoded
    @PUT("api/todo")
    Observable<Response<String>> editTodo(@FieldMap Map<String, Object> data);
    /*@POST("app/users")
    Observable<List<User>> getCityList();

    @FormUrlEncoded
    @POST("api/v1/user/login")
    Observable<User> loginUser(@FieldMap Map<String, String> fields);*/

}
