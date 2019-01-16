package com.example.volk1.workwithdatabase.helper;

public interface ItemTouchHelperAdapter {
    boolean onItemMoved(int from, int to);
    void onItemDismiss(int position);
}
