package com.wasim.smartfoodexchange.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentFoddDeatailBinding
import com.wasim.smartfoodexchange.databinding.FragmentNutritionInfoBinding
import com.wasim.smartfoodexchange.viewModels.BaseViewModel

/**
 *  @author Wasim Abbas
 */

class FoodDetailFragment : BaseFragments<BaseViewModel>() {
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java
    private lateinit var binding: FragmentFoddDeatailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoddDeatailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeLiveData() {
    }

    override fun init() {
        val category = getStringArgument("category")
        val foodName = getStringArgument("foodName")
        val calories = getStringArgument("calorie")
        val protein = getStringArgument("protein")
        val fats = getStringArgument("fats")
        val carbohydrate = getStringArgument("carbohydrate")
        val foodExchange = getStringArgument("foodExchange")

        binding.txtFoodCategory.text = category
        binding.txtFoodName.text = foodName
        binding.txtCalorie.text = calories
        binding.txtProtein.text = protein
        binding.txtFats.text = fats
        binding.txtCarbohydrate.text = carbohydrate
        binding.txtFoodExchange.text = foodExchange
    }

}