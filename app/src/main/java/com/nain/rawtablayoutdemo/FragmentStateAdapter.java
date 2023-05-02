package com.nain.rawtablayoutdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

public class FragmentStateAdapter  extends androidx.viewpager2.adapter.FragmentStateAdapter {
    public FragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new Frag1();
        else if (position == 1)
            return new Frag2();
        return new Frag3();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
