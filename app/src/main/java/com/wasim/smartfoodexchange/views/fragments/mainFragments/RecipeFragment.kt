package com.wasim.smartfoodexchange.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentRecipeBinding
import com.wasim.smartfoodexchange.models.FoodExchange
import com.wasim.smartfoodexchange.utils.*
import com.wasim.smartfoodexchange.viewModels.BaseViewModel
import com.wasim.smartfoodexchange.views.adapters.RecipeAdapter
import io.paperdb.Paper
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


class RecipeFragment : BaseFragments<BaseViewModel>() {
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

    private lateinit var binding: FragmentRecipeBinding
    val bundle = Bundle()

    private lateinit var recipeAapter: RecipeAdapter
    private lateinit var foodList: List<FoodExchange>
    private var recipeList: ArrayList<String> = arrayListOf()
    private var mCaloreie: Int = 0
    private var mProtein: Float = 0.0f
    private var mFats: Float = 0.0f
    private var mCarboHydrate: Float = 0.0f
    private var mFoodExchange: Float = 0.0f


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeLiveData() {}

    override fun init() {


        binding.btnNext.singleClick {
            if (binding.EdRecipeName.isEmpty()) {
                showToast("Please Add Recipe Name ")
            } else {

                bundle.putString("name", binding.EdRecipeName.getString())
                bundle.putSerializable("key", recipeList as Serializable)

                bundle.putString(CALORIE, mCaloreie.toString())
                bundle.putString(PROTEIN, mProtein.toString())
                bundle.putString(FATS, mFats.toString())
                bundle.putString(CARBOHYDRATE, mCarboHydrate.toString())
                bundle.putString(FOODEXCHANGE, mFoodExchange.toString())
                currentActivity().replaceMainFragment(
                    R.id.action_recipeFragment_to_recipeDetailFragment,
                    bundle
                )
            }
            binding.EdRecipeName.text =null

        }

        val bufferReader =
            BufferedReader(currentActivity().assets.open("food_exchange.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT
        )

        foodList = csvParser.map {
            FoodExchange(
                category = it.get(0),
                food_name = it.get(1),
                calories = it.get(2),
                protein = it.get(3),
                fat = it.get(4),
                carbohydrate = it.get(5),
                Food_Exchange = it.get(6)
            )
        }
//        csvParser.forEach {
//            it?.let {
//                val food = FoodExchange(
//                    category = it.get(0),
//                    food_name = it.get(1),
//                    calories = it.get(2),
//                    protein = it.get(3),
//                    fat = it.get(4),
//                    carbohydrate = it.get(5),
//                    Food_Exchange = it.get(6),
//
//                    )
              //  (foodList as MutableList<FoodExchange>).add(food)
 //           }
        recipeAapter = RecipeAdapter(currentActivity(), foodList) { foodName, calorie, protein, fat, carbohydrate, foodExchange ->
            val newFood = FoodExchange(category = "", food_name = foodName, calories = calorie, protein = protein, fat = fat, carbohydrate = carbohydrate, Food_Exchange = foodExchange)
            (foodList as MutableList<FoodExchange>).add(newFood)

            recipeList.add(foodName)
            mCaloreie += calorie.toInt()
            mProtein += protein.toFloat()
            mFats += fat.toFloat()
            mCarboHydrate += carbohydrate.toFloat()
            mFoodExchange += foodExchange.toFloat()

            updateRecipeList(foodList)
        }



        binding.RvFood.let {
            it.adapter = recipeAapter
        }

        binding.SearcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               // filterList(newText)
                recipeAapter.filterList(newText)
                return true
            }

        })

    }

//    private fun filterList(query: String?) {
//        if (query != null) {
//            val myFilserListe = ArrayList<FoodExchange>()
//            for (i in foodList) {
//                if (i.food_name.lowercase(Locale.ROOT).contains(query)) {
//                    myFilserListe.add(i)
//                }
//            }
//            if (myFilserListe.isEmpty()) {
//                showToast("No Data Found")
//            } else {
//                updateRecipeList(myFilserListe)
//            }
//        }
//
//    }

    private fun updateRecipeList(recipeData: List<FoodExchange>) {
        recipeAapter.updateData(recipeData as ArrayList<FoodExchange>)
    }
}