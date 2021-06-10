package com.celestialinterface.food_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.celestialinterface.food_movie.Fragments.Food_Details;
import com.celestialinterface.food_movie.Fragments.Movie_Details;
import com.celestialinterface.food_movie.databinding.ActivityDetailsBinding;

public class Details extends AppCompatActivity {

    ActivityDetailsBinding binding;
    Fragment fragment;

    public Details()
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_details);
        int fragmentPosition = getIntent().getIntExtra("FRAGMENT_POSITION", 0);
        switch (fragmentPosition)
        {
            case 1: fragment = new Food_Details(); break;
            case 2: fragment = new Movie_Details(); break;
        }
        getSupportFragmentManager().beginTransaction().add(R.id.showDetails, fragment).commit();
    }
}