package org.techtown.a1006_bibly;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface OnClickListener {
    public void onButtonClick(RecyclerView recyclerView, String type, String btnKind, int btnNum);
    public void onBookClick(BookInfo bookInfo);
    public void onRecommendDetailButtonClick(String type, String[] type_kinds);

}
