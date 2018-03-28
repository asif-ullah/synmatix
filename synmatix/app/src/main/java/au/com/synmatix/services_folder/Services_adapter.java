package au.com.synmatix.services_folder;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.services_folder.Services_detail.Services_detail;

/**
 * Created by user on 3/27/18.
 */

public class Services_adapter extends RecyclerView.Adapter<Services_adapter.MyViewHolder> {


    Context context;
    List<String_services> list = new ArrayList<>();

    public Services_adapter(List<String_services> services, Context context) {
        this.context = context;
        this.list = services;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_services, parent, false);

        return new MyViewHolder(itemView, context, list);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String_services services = list.get(position);
        holder.name.setText(services.getName());
//        holder.detail.setText(services.ge());
        int imageresources=context.getResources().getIdentifier(services.getImage(),null,context.getPackageName());

        Picasso.with(context)
                .load(imageresources)
//                .placeholder(R.drawable.ic_business_center_black_24dp)
                .error(R.drawable.ic_business_center_black_24dp)
                .into(holder.img_service);
//        Log.d("services images " , "print my "+services.getImage());
//        Drawable drawable_res=context.getResources().getDrawable(imageresources);
//            holder.img_service.setImageDrawable(drawable_res);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView detail;
        ImageView img_service;
        Context context;


        List<String_services> string_services = new ArrayList<>();

        public MyViewHolder(View itemView, Context context, List<String_services> list) {
            super(itemView);
            this.context = context;

            string_services = list;
            name = (TextView) itemView.findViewById(R.id.name);
            img_service = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            final String_services services = string_services.get(position);
            Intent intent = new Intent(context, Services_detail.class);
            intent.putExtra("name",services.getName());
            intent.putExtra("id", services.getId());

            context.startActivity(intent);
        }
    }
}
