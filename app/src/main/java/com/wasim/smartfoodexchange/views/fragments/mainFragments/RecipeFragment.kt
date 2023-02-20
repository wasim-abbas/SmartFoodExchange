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
import com.wasim.smartfoodexchange.utils.getString
import com.wasim.smartfoodexchange.utils.isEmpty
import com.wasim.smartfoodexchange.utils.singleClick
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

    override fun observeLiveData() {
    }

    private lateinit var recipeAapter: RecipeAdapter
    private lateinit var foodList: List<FoodExchange>
    private var recipeList: ArrayList<String> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun init() {

        recipeAapter =
            RecipeAdapter(currentActivity()) { cat, addReccipe, calorie, protein, fat, carbogydarate, foodExchnage ->
                recipeList?.add(addReccipe)

                bundle.putString("category", cat)
                bundle.putString("foodName", addReccipe)
                bundle.putString("calorie", calorie)
                bundle.putString("protein", protein)
                bundle.putString("fats", fat)
                bundle.putString("carbohydrate", carbogydarate)
                bundle.putString("foodExchange", foodExchnage)
            }
        binding.RvFood.let {
            it.adapter = recipeAapter
        }

        binding.btnNext.singleClick {
            if (binding.EdRecipeName.isEmpty()) {
                showToast("Please Add Recipe Name ")
            } else {

                bundle.putString("name", binding.EdRecipeName.getString())
                bundle.putSerializable("key", recipeList as Serializable)
                currentActivity().replaceMainFragment(
                    R.id.action_recipeFragment_to_recipeDetailFragment,
                    bundle
                )
            }


            //                val bundle = Bundle()
//                bundle.putString("category", cat)
//                bundle.putString("foodName", addReccipe)
//                bundle.putString("calorie", calorie)
//                bundle.putString("protein", protein)
//                bundle.putString("fats", fat)
//                bundle.putString("carbohydrate", carbogydarate)
//                bundle.putString("foodExchange", foodExchnage)


        }
        val bufferReader =
            BufferedReader(currentActivity().assets.open("food_exchange.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT
        )

        foodList = mutableListOf<FoodExchange>()
        csvParser.forEach {
            it?.let {
                val cities = FoodExchange(
                    category = it.get(0),
                    food_name = it.get(1),
                    calories = it.get(2),
                    protein = it.get(3),
                    fat = it.get(4),
                    carbohydrate = it.get(5),
                    Food_Exchange = it.get(6),

                    )
                (foodList as MutableList<FoodExchange>).add(cities)
            }
        }
        recipeAapter.updateData(foodList as ArrayList<FoodExchange>)

        binding.SearcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }

    private fun filterList(query: String?) {
        if (query != null) {
            val myFilserListe = ArrayList<FoodExchange>()
            for (i in foodList) {
                if (i.food_name.lowercase(Locale.ROOT).contains(query)) {
                    myFilserListe.add(i)
                }
            }
            if (myFilserListe.isEmpty()) {
                showToast("No Data Found")
            } else {
                recipeAapter.updateData(myFilserListe)
            }
        }

    }
}