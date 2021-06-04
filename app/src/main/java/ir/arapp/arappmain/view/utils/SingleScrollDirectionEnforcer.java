package ir.arapp.arappmain.view.utils;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import static java.lang.Math.abs;

public class SingleScrollDirectionEnforcer extends RecyclerView.OnScrollListener implements RecyclerView.OnItemTouchListener {

    private int scrollState = RecyclerView.SCROLL_STATE_IDLE;
    private int scrollPointerId = -1;
    private int initialTouchX = 0;
    private int initialTouchY = 0;
    private int dx = 0;
    private int dy = 0;


    @Override
    public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int oldState = scrollState;
        scrollState = newState;
        if (oldState == RecyclerView.SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager != null) {
                boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
                boolean canScrollVertically = layoutManager.canScrollVertically();
                if (canScrollHorizontally != canScrollVertically) {
                    if ((canScrollHorizontally && abs(dy) > abs(dx))
                            || (canScrollVertically && abs(dx) > abs(dy))) {
                        recyclerView.stopScroll();
                    }
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
        if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
            scrollPointerId = e.getPointerId(0);
            initialTouchX = (int) (e.getX() + 0.5f);
            initialTouchY = (int) (e.getY() + 0.5f);
        } else if (e.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            int actionIndex = e.getActionIndex();
            scrollPointerId = e.getPointerId(actionIndex);
            initialTouchX = (int) (e.getX(actionIndex) + 0.5f);
            initialTouchY = (int) (e.getY(actionIndex) + 0.5f);
        } else if (e.getActionMasked() == MotionEvent.ACTION_UP) {
            int index = e.findPointerIndex(scrollPointerId);
            if (index >= 0 && scrollState != RecyclerView.SCROLL_STATE_DRAGGING) {
                int x = (int) (e.getX(index) + 0.5f);
                int y = (int) (e.getY(index) + 0.5f);
                dx = x - initialTouchX;
                dy = y - initialTouchY;
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
