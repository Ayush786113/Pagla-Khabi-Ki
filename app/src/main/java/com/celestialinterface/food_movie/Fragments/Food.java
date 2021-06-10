package com.celestialinterface.food_movie.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celestialinterface.food_movie.Details;
import com.celestialinterface.food_movie.Models.Meal;
import com.celestialinterface.food_movie.R;
import com.celestialinterface.food_movie.TinyDB;
import com.celestialinterface.food_movie.databinding.FragmentFoodBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Food extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    FragmentFoodBinding binding;
    TextInputEditText search;
    ListView meal_list;
    ArrayList<Meal> mealModel;
    ArrayAdapter<Meal> mealArrayAdapter;
    String query;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String[] filter;
    ArrayAdapter<String> filter_food;
    String url;
    public Food() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(inflater, container, false);
        search = binding.mealName;
        meal_list = binding.foodList;
        binding.mealLayout.setEndIconOnClickListener(this);
        mealModel = new ArrayList<Meal>();
        if(mealModel.size()>0)
            populateList(mealModel);
        else
            result("");
        preferences = getContext().getSharedPreferences("FOOD_DATA", Context.MODE_PRIVATE);
        editor = preferences.edit();
        filter = getResources().getStringArray(R.array.food_filter);
        filter_food = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, filter);
        binding.filter.setAdapter(filter_food);
        meal_list.setOnItemClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        query = search.getText().toString();
        mealModel.clear();
        result(query.trim());
    }

    private void result(String text)
    {
        String URL = "https://www.themealdb.com/api/json/v1/1/search.php?s="+text;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray foods = response.getJSONArray("meals");
                    for(int i = 0; i< foods.length(); i++)
                    {
                        JSONObject food = foods.getJSONObject(i);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        Meal meal = gson.fromJson(String.valueOf(food), Meal.class);
                        mealModel.add(meal);
                    }
                    Gson gson = new Gson();
                    String meal_list = gson.toJson(mealModel);
                    editor.putString("MEAL_LIST", meal_list);
                    editor.apply();
                    populateList(mealModel);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Contact the Developer", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void result()
    {
        Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray foods = response.getJSONArray("meals");
                    for(int i = 0; i< foods.length(); i++)
                    {
                        JSONObject food = foods.getJSONObject(i);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        Meal meal = gson.fromJson(String.valueOf(food), Meal.class);
                        mealModel.add(meal);
                    }
                    Gson gson = new Gson();
                    String meal_list = gson.toJson(mealModel);
                    editor.putString("MEAL_LIST", meal_list);
                    editor.apply();
                    populateList(mealModel);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Contact the Developer", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void populateList(ArrayList<Meal> list)
    {
        mealArrayAdapter = new ArrayAdapter<Meal>(getContext(), R.layout.meal_model, list)
        {
            @SuppressLint("ViewHolder")
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.meal_model, null);
                ImageView thumbnail = convertView.findViewById(R.id.mealPic);
                TextView id = convertView.findViewById(R.id.meal_id);
                TextView title = convertView.findViewById(R.id.meal_title);
                TextView category_area = convertView.findViewById(R.id.meal_category_area);
                Meal currentFood = mealModel.get(position);
                String ethnicity = "";
                Picasso.get().load(currentFood.getStrMealThumb()).into(thumbnail);
                id.setText(Integer.toString(currentFood.getIdMeal()));
                title.setText(currentFood.getStrMeal());
                if(!(currentFood.getStrTags() == null))
                    ethnicity = currentFood.getStrTags()+"\b"+currentFood.getStrCategory()+"\b"+currentFood.getStrArea();
                else
                    ethnicity = currentFood.getStrCategory()+"\b"+currentFood.getStrArea();
                category_area.setText(ethnicity);
                return convertView;
            }
        };
        mealArrayAdapter.notifyDataSetChanged();
        meal_list.setAdapter(mealArrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        editor.putInt("POSITION", position);
        editor.apply();
        Intent intent = new Intent(getContext(), Details.class);
        intent.putExtra("FRAGMENT_POSITION", 1);
        startActivity(intent);
    }

}