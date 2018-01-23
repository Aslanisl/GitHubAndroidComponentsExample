package ru.mail.aslanisl.githubandroidcomponentsexample.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Ivan on 23.01.2018.
 */

public class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public interface ScrollViewListener {
        void onLoadMore();
    }

    private static final int VISIBLE_THRESHOLD = 5;
    private ScrollViewListener mScrollViewListener = null;
    private int previousTotalItemCount = 0;
    private boolean loadingMode = true;

    public EndlessRecyclerOnScrollListener(ScrollViewListener listener) {
        mScrollViewListener = listener;
    }

    public void init() {
        previousTotalItemCount = 0;
        loadingMode = true;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();

        if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
            if (loadingMode) {
                if (totalItemCount > previousTotalItemCount) {
                    loadingMode = false;
                    previousTotalItemCount = totalItemCount;
                }
            }
            if (!loadingMode && visibleItemCount + firstVisibleItemPosition >= totalItemCount - VISIBLE_THRESHOLD) {
                loadingMode = true;
                if (mScrollViewListener != null) {
                    mScrollViewListener.onLoadMore();
                }
            }
        }
    }
}
