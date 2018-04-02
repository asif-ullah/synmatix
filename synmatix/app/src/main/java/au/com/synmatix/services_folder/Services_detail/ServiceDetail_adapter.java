package au.com.synmatix.services_folder.Services_detail;

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

class ServiceDetail_adapter extends RecyclerView.Adapter<ServiceDetail_adapter.MyViewHolder> {
    Context context;
    List<String_sdetail> list=new ArrayList<>();

    public ServiceDetail_adapter(List<String_sdetail> sdetails, Context context) {
        this.context=context;
        this.list=sdetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_aboutus, parent, false);

        return new MyViewHolder(itemView, context, list);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String_sdetail stringSdetail = list.get(position);
        holder.name.setText(stringSdetail.getName());
        holder.detail.setText(stringSdetail.getDetail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView detail;
        Context context;


        List<String_sdetail> sdetails = new ArrayList<>();

        public MyViewHolder(View itemView, Context context, List<String_sdetail> list) {
            super(itemView);
            this.context = this.context;

            sdetails = ServiceDetail_adapter.this.list;
            name = (TextView) itemView.findViewById(R.id.name);
            detail = (TextView) itemView.findViewById(R.id.detail);

        }
    }
}
