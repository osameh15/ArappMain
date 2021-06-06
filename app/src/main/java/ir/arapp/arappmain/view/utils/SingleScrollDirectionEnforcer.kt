package ir.arapp.arappmain.view.utils

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener

class SingleScrollDirectionEnforcer : RecyclerView.OnScrollListener(), OnItemTouchListener {
    private var scrollState = RecyclerView.SCROLL_STATE_IDLE
    private var scrollPointerId = -1
    private var initialTouchX = 0
    private var initialTouchY = 0
    private var dx = 0
    private var dy = 0
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val oldState = scrollState
        scrollState = newState
        if (oldState == RecyclerView.SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            val layoutManager = recyclerView.layoutManager
            if (layoutManager != null) {
                val canScrollHorizontally = layoutManager.canScrollHorizontally()
                val canScrollVertically = layoutManager.canScrollVertically()
                if (canScrollHorizontally != canScrollVertically) {
                    if (canScrollHorizontally && Math.abs(dy) > Math.abs(dx)
                        || canScrollVertically && Math.abs(dx) > Math.abs(dy)
                    ) {
                        recyclerView.stopScroll()
                    }
                }
            }
        }
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        if (e.actionMasked == MotionEvent.ACTION_DOWN) {
            scrollPointerId = e.getPointerId(0)
            initialTouchX = (e.x + 5f).toInt()
            initialTouchY = (e.y + 5f).toInt()
        } else if (e.actionMasked == MotionEvent.ACTION_POINTER_DOWN) {
            val actionIndex = e.actionIndex
            scrollPointerId = e.getPointerId(actionIndex)
            initialTouchX = (e.getX(actionIndex) + 5f).toInt()
            initialTouchY = (e.getY(actionIndex) + 5f).toInt()
        } else if (e.actionMasked == MotionEvent.ACTION_UP) {
            val index = e.findPointerIndex(scrollPointerId)
            if (index >= 0 && scrollState != RecyclerView.SCROLL_STATE_DRAGGING) {
                val x = (e.getX(index) + 5f).toInt()
                val y = (e.getY(index) + 5f).toInt()
                dx = x - initialTouchX
                dy = y - initialTouchY
            }
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}