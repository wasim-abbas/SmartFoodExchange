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
        //recipeList = getParcelableArgument("key")
        //  recipeList = arguments?.getSerializable("key") as List<FoodExchange>
        val recipeName = getStringArgument("name")
        recipeList = arguments?.getSerializable("key") as ArrayList<FoodExchange>

        binding.txtRecipeName.text = recipeName


        recipeListAdapter =
            RecipeListAdapter(currentActivity()) {
  //              cattegor, foddName, fats,carbohydrate,protein,calorie,foodExchange ->
//                val bundle = Bundle()
//                bundle.putString("category", cattegor)
//                bundle.putString("foodName", foddName)
//                bundle.putString("calorie", calorie)
//                bundle.putString("protein", protein)
//                bundle.putString("fats", fats)
//                bundle.putString("carbohydrate", carbohydrate)
//                bundle.putString("foodExchange", foodExchange)
//                currentActivity().replaceMainFragment(
//                    R.id.action_nutritionInfoFragment_to_foodDetailFragment,
//                    bundle
//                )

            }

        binding.RvRecipeList.let {
            it.adapter = recipeListAdapter
        }
        recipeListAdapter.updateData(recipeList as ArrayList<String>)

    }


}