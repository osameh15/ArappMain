package ir.arapp.arappmain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import ir.arapp.arappmain.model.Category;
import ir.arapp.arappmain.R;

public class CategoryViewModel extends AndroidViewModel
{
//    region Variable
    ArrayList<Category> categories;
    MutableLiveData<ArrayList<Category>> allCategoryItems;
//    endregion

//    Constructor
    public CategoryViewModel(@NonNull Application application)
    {
        super(application);
//        Hooks
        categories = new ArrayList<>();
        allCategoryItems =  new MutableLiveData<>();
//        Initialize
        init();
    }

//    region Methods
//    Initialize function and variables
    private void init()
    {
        populateList();
        allCategoryItems.setValue(categories);
    }
//    Set data to category array list
    private void populateList()
    {
//        TODO connect to server
        categories.add(new Category(1, "رستوران", R.drawable.restaurant));
        categories.add(new Category(2, "فست فود", R.drawable.fast_food));
        categories.add(new Category(3, "کافه", R.drawable.cafe));
        categories.add(new Category(4, "هتل", R.drawable.hotels));
        categories.add(new Category(5, "مسافرخانه", R.drawable.rest_room));
        categories.add(new Category(6, "مراکز خرید", R.drawable.shops));
    }
    //    Get all Category items
    public MutableLiveData<ArrayList<Category>> getAllCategoryItems()
    {
        return allCategoryItems;
    }
//    endregion
}
