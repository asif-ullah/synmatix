package au.com.synmatix.home_folder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;

/**
 * Created by user on 3/27/18.
 */

public class Home_adapter extends RecyclerView.Adapter<Home_adapter.MyViewHolder> {
    Context context;
    List<String_aboutus> list = new ArrayList<>();

    public Home_adapter(List<String_aboutus> about_usList, Context context) {
        this.context = context;
        this.list = about_usList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_aboutus, parent, false);

        return new MyViewHolder(itemView, context, list);
    }

    @Override
    public void onBindViewHolder(Home_adapter.MyViewHolder holder, int position) {
        String_aboutus stringDiet = list.get(position);
        holder.name.setText(stringDiet.getName());
        holder.detail.setText(stringDiet.getDetail());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView detail;
        Context context;


        List<String_aboutus> string_aboutuses = new ArrayList<>();

        public MyViewHolder(View itemView, Context context, List<String_aboutus> list) {
            super(itemView);
            this.context = context;

            string_aboutuses = list;
            name = (TextView) itemView.findViewById(R.id.name);
            detail = (TextView) itemView.findViewById(R.id.detail);


        }
    }
}
