package com.wasim.smartfoodexchange.views.fragments.mainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentLoginBinding
import com.wasim.smartfoodexchange.databinding.FragmentNutritionInfoBinding
import com.wasim.smartfoodexchange.models.FoodExchange
import com.wasim.smartfoodexchange.viewModels.BaseViewModel
import com.wasim.smartfoodexchange.views.adapters.FoodAapter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.util.*
import kotlin.collections.ArrayList

class NutritionInfoFragment : BaseFragments<BaseViewModel>() {
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

    lateinit var binding: FragmentNutritionInfoBinding

    private lateinit var foodAapter: FoodAapter
    private lateinit var foodList: List<FoodExchange>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNutritionInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeLiveData() {
    }

    override fun init() {
        foodAapter =
            FoodAapter(currentActivity()) { cattegor, foddName, calorie, protein, fats, carbohydrate, foodExchange ->
                val bundle = Bundle()
                bundle.putString("category", cattegor)
                bundle.putString("foodName", foddName)
                bundle.putString("calorie", calorie)
                bundle.putString("protein", protein)
                bundle.putString("fats", fats)
                bundle.putString("carbohydrate", carbohydrate)
                bundle.putString("foodExchange", foodExchange)

                currentActivity().replaceMainFragment(
                    R.id.action_nutritionInfoFragment_to_foodDetailFragment,
                    bundle
                )

            }
        binding.RvFood.let {
            it.adapter = foodAapter
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
        foodAapter.updateData(foodList as ArrayList<FoodExchange>)

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
                foodAapter.updateData(myFilserListe)
            }
        }

    }

}