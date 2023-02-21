package com.wasim.smartfoodexchange.views.fragments.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentNutritionInfoBinding
import com.wasim.smartfoodexchange.databinding.FragmentRecipeDetailBinding
import com.wasim.smartfoodexchange.models.FoodExchange
import com.wasim.smartfoodexchange.utils.*
import com.wasim.smartfoodexchange.viewModels.BaseViewModel
import com.wasim.smartfoodexchange.views.adapters.FoodAapter
import com.wasim.smartfoodexchange.views.adapters.RecipeListAdapter
import io.paperdb.Paper


class RecipeDetailFragment : BaseFragments<BaseViewModel>() {
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java


    lateinit var binding: FragmentRecipeDetailBinding

    private lateinit var recipeListAdapter: RecipeListAdapter

    //private lateinit var recipeList:ArrayList<FoodExchange>
    private var recipeList: List<FoodExchange> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeLiveData() {

    }

    override fun init() {
        val recipeName = getStringArgument("name")
        recipeList = arguments?.getSerializable("key") as ArrayList<FoodExchange>
        val cal = getStringArgument(CALORIE)
        val protein = getStringArgument(PROTEIN)
        val fats = getStringArgument(FATS)
        val carbohydrate = getStringArgument(CARBOHYDRATE)
        val foodExchange = getStringArgument(FOODEXCHANGE)


        binding.txtRecipeName.text = recipeName

        binding.txtCalorie.text = cal
        binding.txtProtein.text = protein
        binding.txtFats.text = fats
        binding.txtCarbohydrate.text = carbohydrate
        binding.txtFoodExchange.text = foodExchange


        recipeListAdapter =
            RecipeListAdapter(currentActivity()) {}

        binding.RvRecipeList.let {
            it.adapter = recipeListAdapter
        }
        recipeListAdapter.updateData(recipeList as ArrayList<String>)

    }


}