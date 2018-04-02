package au.com.synmatix.whyus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.home_folder.Home_adapter;
import au.com.synmatix.home_folder.String_aboutus;

/**
 * Created by user on 3/27/18.
 */

public class WhyUs_adapter extends RecyclerView.Adapter<WhyUs_adapter.MyViewHolder>{
    Context context;
    List<String_yus> list=new ArrayList<>();

    public WhyUs_adapter(List<String_yus> yusList, Context context) {
        this.context=context;
        this.list=yusList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_aboutus, parent, false);

        return new MyViewHolder(itemView, context, list);     }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String_yus yus=list.get(position);
        holder.name.setText(yus.getName());
        holder.detail.setText(yus.getDetail());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView detail;
        Context context;
        List<String_yus> yusList=new ArrayList<>();

        public MyViewHolder(View itemView, Context context, List<String_yus> list) {
            super(itemView);
            this.context=context;

            yusList=list;
            name=(TextView)itemView.findViewById(R.id.name);
            detail=(TextView)itemView.findViewById(R.id.detail);


        }
    }
}
