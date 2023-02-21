package com.wasim.smartfoodexchange.views.fragments.mainFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentHomeBinding
import com.wasim.smartfoodexchange.utils.gone
import com.wasim.smartfoodexchange.utils.singleClick
import com.wasim.smartfoodexchange.utils.visible
import com.wasim.smartfoodexchange.viewModels.BaseViewModel


class HomeFragment : BaseFragments<BaseViewModel>() {

    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

    private lateinit var binding: FragmentHomeBinding

    private var clicked = false

    private val rotationOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            currentActivity(),
            R.anim.rotate_open_anim
        )
    }
    private val rotationClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            currentActivity(),
            R.anim.roate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            currentActivity(),
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            currentActivity(),
            R.anim.to_bottom_anim
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeLiveData() {}

    override fun init() {
        binding.floatActionBtn.singleClick {
            onFloatingActionButton()
        }

        binding.floatActionRecipe.singleClick {
            currentActivity().replaceMainFragment(R.id.action_homeFragment_to_recipeFragment)
            clicked = false
        }
        binding.floatActionMealPlan.singleClick {
            currentActivity().replaceMainFragment(R.id.action_homeFragment_to_mealPlanFragment)
            clicked = false
        }

        binding.floatActionNutritionInfo.singleClick {
            currentActivity().replaceMainFragment(R.id.action_homeFragment_to_nutritionInfoFragment)
            clicked = false
        }
    }

    private fun onFloatingActionButton() {
        setvisibility(clicked)
        setAnimation(clicked)
        clickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.floatActionRecipe.startAnimation(fromBottom)
            binding.floatActionNutritionInfo.startAnimation(fromBottom)
            binding.floatActionMealPlan.startAnimation(fromBottom)
            binding.floatActionBtn.startAnimation(rotationOpen)

        } else {
            binding.floatActionRecipe.startAnimation(toBottom)
            binding.floatActionNutritionInfo.startAnimation(toBottom)
            binding.floatActionMealPlan.startAnimation(toBottom)
            binding.floatActionBtn.startAnimation(rotationClose)
        }
    }

    private fun setvisibility(clicked: Boolean) {
        if (!clicked) {
            binding.floatActionRecipe.visible()
            binding.floatActionNutritionInfo.visible()
            binding.floatActionMealPlan.visible()

        } else {
            binding.floatActionRecipe.gone()
            binding.floatActionNutritionInfo.gone()
            binding.floatActionMealPlan.gone()

        }
    }

    private fun clickable(clicked: Boolean) {
        if (!clicked) {
            binding.floatActionRecipe.isClickable = true
            binding.floatActionNutritionInfo.isClickable = true
            binding.floatActionMealPlan.isClickable = true
        } else {
            binding.floatActionRecipe.isClickable = false
            binding.floatActionNutritionInfo.isClickable = false
            binding.floatActionMealPlan.isClickable = false
        }
    }


}