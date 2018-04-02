package au.com.synmatix.packages_avaible.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.Quote.Quote;
import au.com.synmatix.R;
import au.com.synmatix.packages_avaible.Packages_detail;
import au.com.synmatix.packages_avaible.Strinh_subpkges;

/**
 * Created by user on 3/29/18.
 */

public class Web_adapter extends RecyclerView.Adapter<Web_adapter.MyViewHolder> {
    Context context;
    List<Strinh_subpkges> list = new ArrayList<>();

    public Web_adapter(List<Strinh_subpkges> strinh_subpkges, Context context) {
        this.context = context;
        this.list = strinh_subpkges;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pkg_list, parent, false);

        return new MyViewHolder(itemView, context, list);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Strinh_subpkges subpkges = list.get(position);
        holder.name.setText(subpkges.getTopic());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        Context context;


        List<Strinh_subpkges> strinh_subpkges = new ArrayList<>();

        public MyViewHolder(View itemView, Context context, List<Strinh_subpkges> list) {
            super(itemView);
            this.context = context;

            strinh_subpkges = list;
            name = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
//            int position = getAdapterPosition();
//            final Strinh_subpkges subpkges = strinh_subpkges.get(position);
            Intent intent = new Intent(context, Quote.class);
//            intent.putExtra("name", subpkges.getTopic());
//            intent.putExtra("id", subpkges.getId());

            context.startActivity(intent);
        }

    }
}
