package ir.arapp.arappmain.util.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.List;

import ir.arapp.arappmain.R;

public class SpinnerAdapter extends ArrayAdapter<String>
{
//    region Variables
    private final Typeface typeface;
//    endregion

//    Constructor
    public SpinnerAdapter(@NonNull Context context, int resource)
    {
        super(context, resource);
        typeface = ResourcesCompat.getFont(context, R.font.iransans_bold);
    }

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects)
    {
        super(context, resource, 0, objects);
        typeface = ResourcesCompat.getFont(context, R.font.iransans_bold);
    }

    //    region Methods
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = super.getView(position, convertView, parent);
        ((TextView) view).setTypeface(typeface);
        return view;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = super.getDropDownView(position, convertView, parent);
        ((TextView) view).setTypeface(typeface);
        return view;
    }
    @Override
    public void setDropDownViewResource(int resource)
    {
        super.setDropDownViewResource(resource);
    }

    @Override
    public int getPosition(@Nullable String item)
    {
        return super.getPosition(item);
    }
    //    endregion
}
