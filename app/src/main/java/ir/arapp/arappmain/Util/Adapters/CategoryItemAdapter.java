package ir.arapp.arappmain.Util.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.ArrayList;
import ir.arapp.arappmain.Model.Category;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.ItemClickListener;
import ir.arapp.arappmain.Util.Services.SnackBarToast;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>
{
//    region Variable
    private final View view;
    private ArrayList<Category> categories = new ArrayList<>();
    public ItemClickListener itemClickListener;
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
        return new CategoryItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position)
    {
//        get current item in category
        Category currentItem = categories.get(position);
//        Set data and update xml
        holder.roundedImageView.setImageResource(currentItem.getPicture());
        holder.cardView.setOnClickListener(view -> itemClickListener.onItemClickListener(this.view, currentItem.getId(), currentItem.getName()));
    }
    @Override
    public int getItemCount()
    {
        return categories.size();
    }
//    Notify Data changed
    public void setCategories(ArrayList<Category> categories)
    {
        this.categories = categories;
        notifyDataSetChanged();
    }
//    endregion
//    View holder class
    static class CategoryItemViewHolder extends RecyclerView.ViewHolder
    {
//        region Variable
            MaterialCardView cardView;
            RoundedImageView roundedImageView;
//            endregion
//        Constructor to View holder
        public CategoryItemViewHolder(@NonNull View itemView)
        {
            super(itemView);
//            Hooks
            cardView = itemView.findViewById(R.id.cardViewCategoryItemContainer);
            roundedImageView = itemView.findViewById(R.id.categoryItemContainer);
        }
    }
}
