package ir.arapp.arappmain.Util.Adapters;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class SingleDirectionRecyclerView extends RecyclerView {
    SingleScrollDirectionEnforcer enforcer = new SingleScrollDirectionEnforcer();
    public SingleDirectionRecyclerView(@NonNull @NotNull Context context) {
        super(context);
        init();
    }

    public SingleDirectionRecyclerView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleDirectionRecyclerView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){
        addOnScrollListener(enforcer);
        addOnItemTouchListener(enforcer);
    }
}
