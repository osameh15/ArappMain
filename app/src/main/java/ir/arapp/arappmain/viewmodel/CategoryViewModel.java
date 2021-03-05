package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import ir.arapp.arappmain.Model.CategoryItem;
import ir.arapp.arappmain.R;

public class CategoryViewModel extends AndroidViewModel
{
//    region Variable
    ArrayList<CategoryItem> categoryItems;
    MutableLiveData<ArrayList<CategoryItem>> allCategoryItems;
//    endregion
//    Constructor
    public CategoryViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        allCategoryItems =  new MutableLiveData<>();
        categoryItems = new ArrayList<>();
//        Initialize
        init();
    }
//    region Methods
//    Initialize function and variables
    private void init()
    {
        populateList();
        allCategoryItems.setValue(categoryItems);
    }
//    Set data in category array list
    private void populateList()
    {
        categoryItems.add(new CategoryItem("1", "رستوران", R.drawable.restaurant));
        categoryItems.add(new CategoryItem("2", "فست فود", R.drawable.fast_food));
        categoryItems.add(new CategoryItem("3", "کافه", R.drawable.cafe));
        categoryItems.add(new CategoryItem("4", "هتل", R.drawable.hotels));
        categoryItems.add(new CategoryItem("5", "مسافرخانه", R.drawable.rest_room));
        categoryItems.add(new CategoryItem("6", "مراکز خرید", R.drawable.shops));
    }
    //    Get all Category items
    public MutableLiveData<ArrayList<CategoryItem>> getAllCategoryItems()
    {
        return allCategoryItems;
    }
//    endregion
}
