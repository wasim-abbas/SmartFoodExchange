package com.wasim.smartfoodexchange.views.adapters

import android.content.Context
import android.view.View
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseAdapter
import com.wasim.smartfoodexchange.base.BaseViewHolder
import com.wasim.smartfoodexchange.databinding.ViewFoodSearchBinding
import com.wasim.smartfoodexchange.databinding.ViewRecipeListBinding
import com.wasim.smartfoodexchange.models.FoodExchange
import com.wasim.smartfoodexchange.utils.singleClick

class RecipeListAdapter(
    var context: Context,
    var onClick: (String) -> Unit
) :
    BaseAdapter<RecipeListAdapter.RecipeListViewHolder, String>() {
    class RecipeListViewHolder(itemView: View) : BaseViewHolder(itemView) {}

    override val layoutId: Int
        get() = R.layout.view_recipe_list

    override fun setData(holder: RecipeListViewHolder, model: String, position: Int) {
        val view = holder.itemView

        val binding = ViewRecipeListBinding.bind(view)

        binding.foodName.text = model

        view.singleClick {
            onClick(
                model
            )
        }
    }


}
