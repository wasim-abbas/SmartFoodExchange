package com.wasim.smartfoodexchange.views.adapters

import android.content.Context
import android.view.View
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseAdapter
import com.wasim.smartfoodexchange.base.BaseViewHolder
import com.wasim.smartfoodexchange.databinding.ViewFoodSearchBinding
import com.wasim.smartfoodexchange.databinding.ViewRecipeBinding
import com.wasim.smartfoodexchange.models.FoodExchange
import com.wasim.smartfoodexchange.utils.singleClick
import java.util.*
import kotlin.collections.ArrayList

//class RecipeAdapter(
//    var context: Context,
//    var addRecipeClick: (String, String, String, String, String, String) -> Unit,
//) :
//    BaseAdapter<RecipeAdapter.RecipeViewHolder, FoodExchange>() {
//
//    class RecipeViewHolder(itemView: View) : BaseViewHolder(itemView) {}
//
//    override val layoutId: Int
//        get() = R.layout.view_recipe
//
//    override fun setData(holder: RecipeViewHolder, model: FoodExchange, position: Int) {
//        val view = holder.itemView
//        val binding = ViewRecipeBinding.bind(view)
//        binding.foodNAme.text = model.food_name
//
//        binding.btnAdd.singleClick {
//            addRecipeClick(
//                model.food_name,
//                model.calories,
//                model.protein,
//                model.fat,
//                model.carbohydrate,
//                model.Food_Exchange
//            )
//        }
//
//    }
//
//}
class RecipeAdapter(
    var context: Context,
    var recipeList: List<FoodExchange>,
    var addRecipeClick: (String, String, String, String, String, String) -> Unit,
) : BaseAdapter<RecipeAdapter.RecipeViewHolder, FoodExchange>() {

    class RecipeViewHolder(itemView: View) : BaseViewHolder(itemView)

    override val layoutId: Int
        get() = R.layout.view_recipe


    override fun setData(holder: RecipeViewHolder, model: FoodExchange, position: Int) {
        val view = holder.itemView
        val binding = ViewRecipeBinding.bind(view)
        binding.foodNAme.text = model.food_name

        binding.btnAdd.singleClick {
            addRecipeClick(
                model.food_name,
                model.calories,
                model.protein,
                model.fat,
                model.carbohydrate,
                model.Food_Exchange
            )
        }
    }

    fun filterList(query: String?) {
        if (query != null) {
            val myFilteredList = ArrayList<FoodExchange>()
            for (i in recipeList) {
                if (i.food_name.lowercase(Locale.ROOT).contains(query)) {
                    myFilteredList.add(i)
                }
            }
            updateData(myFilteredList)
        } else {
            updateData(recipeList as ArrayList<FoodExchange>)
        }
    }
}
