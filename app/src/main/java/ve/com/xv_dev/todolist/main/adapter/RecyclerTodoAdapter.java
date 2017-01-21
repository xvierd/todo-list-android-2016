package ve.com.xv_dev.todolist.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ve.com.xv_dev.todolist.R;
import ve.com.xv_dev.todolist.main.MainActivityPresenter;
import ve.com.xv_dev.todolist.models.Todo;
import ve.com.xv_dev.todolist.networking.NetworkError;
import ve.com.xv_dev.todolist.networking.Service;

/**
 * @author Xavier Guti&#233;rrez
 *         20/01/17
 */
public class RecyclerTodoAdapter extends RecyclerView.Adapter<RecyclerTodoAdapter.RecyclerTodoHolder> {
    public ArrayList<Todo> items;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    public Service service;



    public class RecyclerTodoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public CheckBox is_done;
        private Context context;

        public RecyclerTodoHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            is_done = (CheckBox)v.findViewById(R.id.is_done);
            is_done.setOnClickListener(this);
            this.context = itemView.getContext();

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.is_done:
                    Map<String, Object> data = new HashMap<>();
                    data.put("_id", items.get(getAdapterPosition()).get_id());
                    data.put("executed", items.get(getAdapterPosition()).getExecuted());
                    Subscription subscription = service.editTodo(new Service.EditTodoCallback() {

                        @Override
                        public void onSuccess(String response) {
                        }

                        @Override
                        public void onError(NetworkError networkError) {
                            // HttpException httpException = (HttpException) networkError.getError();
                        }

                    }, data);

                    subscriptions.add(subscription);


            }
        }

        /*@Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.detail:
                    Intent intent = new Intent(context, DetailService.class);
                    context.startActivity(intent);
            }
        }*/
    }

    public RecyclerTodoAdapter(Service service, ArrayList<Todo> items) {
        this.items = items;
        this.service = service;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerTodoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_item, viewGroup, false);
        return new RecyclerTodoHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerTodoHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getNote());
        viewHolder.is_done.setChecked(items.get(i).getExecuted());
    }
}
