package com.celestialinterface.food_movie.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.celestialinterface.food_movie.Models.Meal;
import com.celestialinterface.food_movie.R;
import com.celestialinterface.food_movie.TinyDB;
import com.celestialinterface.food_movie.databinding.FragmentFoodDetailsBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;


public class Food_Details extends Fragment {

    FragmentFoodDetailsBinding fragmentFoodDetailsBinding;
    SharedPreferences sharedPreferences;
    ArrayList<Meal> mealList;
    ArrayList<String> ingredientsList;
    ArrayAdapter<String> adapter;
    ImageView thumb;
    TextView detailTitle, Recipe, ingredientsShow;
    ListView ingredients;
    HashMap<String, String> itemList;
    Meal meal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFoodDetailsBinding = FragmentFoodDetailsBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences("FOOD_DATA", Context.MODE_PRIVATE);
        int position = sharedPreferences.getInt("POSITION", 0);
        itemList = new HashMap<String, String>();
        Gson gson = new Gson();
        String JSONmeallist = sharedPreferences.getString("MEAL_LIST", "");
        Type type = new TypeToken<ArrayList<Meal>>(){}.getType();
        mealList = gson.fromJson(JSONmeallist, type);
        thumb = fragmentFoodDetailsBinding.thumb;
        detailTitle = fragmentFoodDetailsBinding.detailTitle;
        Recipe = fragmentFoodDetailsBinding.recipe;
        ingredientsShow = fragmentFoodDetailsBinding.ingredientsBtn;
        meal = mealList.get(position);
        Picasso.get().load(meal.getStrMealThumb()).into(thumb);
        detailTitle.setText(meal.getStrMeal());
        Recipe.setText(meal.getStrInstructions());
        ingredientsList = new ArrayList<String>();
//        itemList.put(meal.getStrIngredient1(), meal.getStrMeasure1());
//        itemList.put(meal.getStrIngredient2(), meal.getStrMeasure2());
//        itemList.put(meal.getStrIngredient3(), meal.getStrMeasure3());
//        itemList.put(meal.getStrIngredient4(), meal.getStrMeasure4());
//        itemList.put(meal.getStrIngredient5(), meal.getStrMeasure5());
//        itemList.put(meal.getStrIngredient6(), meal.getStrMeasure6());
//        itemList.put(meal.getStrIngredient7(), meal.getStrMeasure7());
//        itemList.put(meal.getStrIngredient8(), meal.getStrMeasure8());
//        itemList.put(meal.getStrIngredient9(), meal.getStrMeasure9());
//        itemList.put(meal.getStrIngredient10(), meal.getStrMeasure10());
//        itemList.put(meal.getStrIngredient11(), meal.getStrMeasure11());
//        itemList.put(meal.getStrIngredient12(), meal.getStrMeasure12());
//        itemList.put(meal.getStrIngredient13(), meal.getStrMeasure13());
//        itemList.put(meal.getStrIngredient14(), meal.getStrMeasure14());
//        itemList.put(meal.getStrIngredient15(), meal.getStrMeasure15());
//        itemList.put(meal.getStrIngredient16(), meal.getStrMeasure16());
//        itemList.put(meal.getStrIngredient17(), meal.getStrMeasure17());
//        itemList.put(meal.getStrIngredient18(), meal.getStrMeasure18());
//        itemList.put(meal.getStrIngredient19(), meal.getStrMeasure19());
//        itemList.put(meal.getStrIngredient20(), meal.getStrMeasure20());
        ingredientsList.add(meal.getStrIngredient1()+" - "+meal.getStrMeasure1());
        ingredientsList.add(meal.getStrIngredient2()+" - "+meal.getStrMeasure2());
        ingredientsList.add(meal.getStrIngredient3()+" - "+meal.getStrMeasure3());
        ingredientsList.add(meal.getStrIngredient4()+" - "+meal.getStrMeasure4());
        ingredientsList.add(meal.getStrIngredient5()+" - "+meal.getStrMeasure5());
        ingredientsList.add(meal.getStrIngredient6()+" - "+meal.getStrMeasure6());
        ingredientsList.add(meal.getStrIngredient7()+" - "+meal.getStrMeasure7());
        ingredientsList.add(meal.getStrIngredient8()+" - "+meal.getStrMeasure8());
        ingredientsList.add(meal.getStrIngredient9()+" - "+meal.getStrMeasure9());
        ingredientsList.add(meal.getStrIngredient10()+" - "+meal.getStrMeasure10());
        ingredientsList.add(meal.getStrIngredient11()+" - "+meal.getStrMeasure11());
        ingredientsList.add(meal.getStrIngredient12()+" - "+meal.getStrMeasure12());
        ingredientsList.add(meal.getStrIngredient13()+" - "+meal.getStrMeasure13());
        ingredientsList.add(meal.getStrIngredient14()+" - "+meal.getStrMeasure14());
        ingredientsList.add(meal.getStrIngredient15()+" - "+meal.getStrMeasure15());
        ingredientsList.add(meal.getStrIngredient16()+" - "+meal.getStrMeasure16());
        ingredientsList.add(meal.getStrIngredient17()+" - "+meal.getStrMeasure17());
        ingredientsList.add(meal.getStrIngredient18()+" - "+meal.getStrMeasure18());
        ingredientsList.add(meal.getStrIngredient19()+" - "+meal.getStrMeasure19());
        ingredientsList.add(meal.getStrIngredient20()+" - "+meal.getStrMeasure20());
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ingredientsList);
        adapter.notifyDataSetChanged();
        String list = "\t";
        for(String item : ingredientsList)
        {
            list += item + "\n \t";
        }
        ingredientsShow.setText(list);
        return fragmentFoodDetailsBinding.getRoot();
    }
}