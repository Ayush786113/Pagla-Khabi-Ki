package com.celestialinterface.food_movie;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.celestialinterface.food_movie.Fragments.Food;
import com.celestialinterface.food_movie.Fragments.Movie;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
            {
                fragment = new Food();
                break;
            }
            case 1:
            {
                fragment = new Movie();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
