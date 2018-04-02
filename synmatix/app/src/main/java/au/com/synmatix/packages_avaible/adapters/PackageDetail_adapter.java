package au.com.synmatix.packages_avaible.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.packages_avaible.String_pkgDetail;

/**
 * Created by user on 3/29/18.
 */

public class PackageDetail_adapter extends RecyclerView.Adapter<PackageDetail_adapter.MyViewHolder> {
    Context context;
    List<String_pkgDetail> list = new ArrayList<>();

    public PackageDetail_adapter(List<String_pkgDetail> sdetails, Context context) {
        this.context = context;
        this.list = sdetails;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_package_details, parent, false);

        return new MyViewHolder(itemView, context, list);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String_pkgDetail string_pkgDetail = list.get(position);
        holder.name.setText(string_pkgDetail.getTopicname());
        holder.detail.setText(string_pkgDetail.getDetail());

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView detail;
        Context context;


        List<String_pkgDetail> pkgDetails = new ArrayList<>();

        public MyViewHolder(View itemView, Context context, List<String_pkgDetail> list) {
            super(itemView);
            this.context = context;

            pkgDetails = list;
            name = (TextView) itemView.findViewById(R.id.title);
            detail = (TextView) itemView.findViewById(R.id.des);

        }

    }
}
