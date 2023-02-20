package com.wasim.smartfoodexchange.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.views.adapters.FoodAapter
import com.wasim.smartfoodexchange.views.adapters.RecipeAdapter
import com.wasim.smartfoodexchange.views.adapters.RecipeListAdapter


abstract class BaseAdapter<VH : BaseViewHolder, D> : RecyclerView.Adapter<VH>() {
    abstract val layoutId: Int
    abstract fun setData(holder: VH, model: D, position: Int)

    private var list = ArrayList<D>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when (layoutId) {

            R.layout.view_food_search ->
                FoodAapter.FoodViewHolder(
                    LayoutInflater.from(
                        parent.context
                    ).inflate(layoutId, parent, false)
                ) as VH

            R.layout.view_recipe ->
                RecipeAdapter.RecipeViewHolder(
                    LayoutInflater.from(
                        parent.context
                    ).inflate(layoutId, parent, false)
                ) as VH
            R.layout.view_recipe_list ->
                RecipeListAdapter.RecipeListViewHolder(
                    LayoutInflater.from(
                        parent.context
                    ).inflate(layoutId, parent, false)
                ) as VH
            else -> FoodAapter.FoodViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(layoutId, parent, false)
            ) as VH


        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (list.size > 0)
            setData(holder, list[position], position)
    }

    override fun getItemCount() = list.size

    fun updateData(newList: ArrayList<D>) {
        list = newList
        notifyDataSetChanged()
    }

    fun clearData() {
        list.clear()
        notifyDataSetChanged()
    }

}