package ir.arapp.arappmain.Util.Adapters;

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

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>
{
//    region Variable
    private ArrayList<CategoryItem> categoryItems = new ArrayList<>();
//    endregion
    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
//        Inflate Layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_item_container, parent, false);
        return new CategoryItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position)
    {
//        Bind View holder
        CategoryItem currentItem = categoryItems.get(position);
        holder.roundedImageView.setImageResource(currentItem.getPicture());
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
}
