package ir.arapp.arappmain.Util.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.ArrayList;
import ir.arapp.arappmain.Model.CategoryItem;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.SnackBarToast;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>
{
//    region Variable
    private final View view;
    private ArrayList<CategoryItem> categoryItems = new ArrayList<>();
    private SnackBarToast snackBarToast;
//    endregion

//    region Methods
//    Constructor
    public CategoryItemAdapter(View view)
    {
        this.view = view;
    }
    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
//        Inflate Layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_item_container, parent, false);
        snackBarToast = new SnackBarToast(view);
        return new CategoryItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position)
    {
//        Bind View holder
        CategoryItem currentItem = categoryItems.get(position);
        holder.roundedImageView.setImageResource(currentItem.getPicture());
        holder.cardView.setOnClickListener(view ->
        {
            snackBarToast.snackBarShortTime(currentItem.getName());
        });
    }
    @Override
    public int getItemCount()
    {
        return categoryItems.size();
    }
//    Notify Data changed
    public void setCategoryItems(ArrayList<CategoryItem> categoryItems)
    {
        this.categoryItems = categoryItems;
        notifyDataSetChanged();
    }
    //    View holder class
    static class CategoryItemViewHolder extends RecyclerView.ViewHolder
    {
//        region Variable
            CardView cardView;
            RoundedImageView roundedImageView;
//            endregion
//        Constructor to View holder
        public CategoryItemViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewCategoryItemContainer);
            roundedImageView = itemView.findViewById(R.id.categoryItemContainer);
        }
    }
//    endregion
}
