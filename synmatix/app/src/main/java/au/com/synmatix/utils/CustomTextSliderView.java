package au.com.synmatix.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import au.com.synmatix.R;

/**
 * Created by apple on 2/28/18.
 */

public class CustomTextSliderView extends BaseSliderView {
    private static Typeface font = null;
    private Context context ;
    public CustomTextSliderView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_text,null);
        ImageView target = (ImageView)v.findViewById(R.id.daimajia_slider_image);
        TextView description = (TextView)v.findViewById(R.id.description);
        description.setText(getDescription());
        if(font == null){
            font = Typeface.createFromAsset(context.getAssets(), "fonts/fff.ttf");
        }
        description.setTypeface(font);
        bindEventAndShow(v, target);
        return v;
    }
}