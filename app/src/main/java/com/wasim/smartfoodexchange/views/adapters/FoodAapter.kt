package com.wasim.smartfoodexchange.views.adapters

import android.content.Context
import android.view.View
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseAdapter
import com.wasim.smartfoodexchange.base.BaseViewHolder
import com.wasim.smartfoodexchange.databinding.ViewFoodSearchBinding
import com.wasim.smartfoodexchange.models.FoodExchange
import com.wasim.smartfoodexchange.utils.singleClick

class FoodAapter(var context: Context, var onClick: (String,String,String,String,String,String,String) -> Unit) :
    BaseAdapter<FoodAapter.FoodViewHolder, FoodExchange>() {

    class FoodViewHolder(itemView: View) : BaseViewHolder(itemView) {}

    override val layoutId: Int
        get() = R.layout.view_food_search


    override fun setData(holder: FoodViewHolder, model: FoodExchange, position: Int) {

        val view = holder.itemView

        val binding = ViewFoodSearchBinding.bind(view)
        binding.foodNAme.text = model.food_name

        view.singleClick {
            onClick(
                model.category,
                model.food_name,
                model.calories,
                model.protein,
                model.fat,
                model.carbohydrate,
                model.Food_Exchange
            )
        }

    }


}