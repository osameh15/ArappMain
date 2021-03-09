package ir.arapp.arappmain.Util.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.ArrayList;
import ir.arapp.arappmain.Model.Profile;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.ItemClickListener;
import ir.arapp.arappmain.Util.Services.SessionManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;

public class ProfileItemAdapter extends RecyclerView.Adapter<ProfileItemAdapter.ProfileItemViewHolder>
{

//    region Variables
    private final View view;
    public ItemClickListener itemClickListener;
    ArrayList<Profile> profileArrayList;
//    endregion

//    region Methods
//    Constructor
    public ProfileItemAdapter(View view)
    {
        this.view = view;
    }
    @NonNull
    @Override
    public ProfileItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
//        Inflate Layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_profile_container, parent, false);
//        Hooks
//        Return view
        return new ProfileItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ProfileItemViewHolder holder, int position)
    {
//        get current item in category
        Profile currentItem = profileArrayList.get(position);
//        Set data and update xml
        holder.roundedImageView.setImageResource(currentItem.getImage());
        holder.name.setText(currentItem.getName());
        holder.cardView.setOnClickListener(view -> itemClickListener.onItemClickListener(this.view, currentItem.getId(), currentItem.getName()));
    }
    @Override
    public int getItemCount()
    {
        return profileArrayList.size();
    }
//    Notify Data changed
    public void setProfileArrayList(ArrayList<Profile> profileArrayList)
    {
        this.profileArrayList = profileArrayList;
        notifyDataSetChanged();
    }
//    endregion

//    View holder class
    static class ProfileItemViewHolder extends RecyclerView.ViewHolder
    {
//        region Variables
        MaterialCardView cardView;
        RoundedImageView roundedImageView;
        TextView name;
//        endregion
//        Constructor
        public ProfileItemViewHolder(@NonNull View itemView)
        {
            super(itemView);
//            Hooks
            cardView = itemView.findViewById(R.id.profileCardView);
            roundedImageView = itemView.findViewById(R.id.profileImageContainer);
            name = itemView.findViewById(R.id.profileNameContainer);
        }
    }
}
