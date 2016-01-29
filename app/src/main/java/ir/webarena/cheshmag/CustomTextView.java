package ir.webarena.cheshmag;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Aylin on 1/30/2016.
 * TODO: change hardcoded font name so user can select another font
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "b_roya.ttf"));
    }
}
