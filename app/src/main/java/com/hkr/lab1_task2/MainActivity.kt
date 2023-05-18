package com.hkr.lab1_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.hkr.lab1_task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var ingredientAdapter: ListAdapter
    private lateinit var methodAdapter: ListAdapter

    private var recipeExpanded = View.GONE

    private val ingredients = listOf(
        R.string.ingredient_1,
        R.string.ingredient_2,
        R.string.ingredient_3,
        R.string.ingredient_4,
        R.string.ingredient_5,
        R.string.ingredient_6,
        R.string.ingredient_7,
        R.string.ingredient_8,
        R.string.ingredient_9
    )

    private val method = listOf(
        R.string.method_1,
        R.string.method_2,
        R.string.method_3,
        R.string.method_4,
        R.string.method_5,
        R.string.method_6,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            with(savedInstanceState) {
                recipeExpanded = getInt(RECIPE_VISIBILITY)
            }
        }

        ingredientAdapter = ListAdapter(ingredients)
        methodAdapter = ListAdapter(method)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.rvIngredientsList.adapter = ingredientAdapter
        binding.rvIngredientsList.layoutManager = LinearLayoutManager(this)

        binding.rvMethodList.adapter = methodAdapter
        binding.rvMethodList.layoutManager = LinearLayoutManager(this)

        binding.btnShowRecipe.setOnClickListener {
            if(binding.recipeContainer.isVisible){
                binding.recipeContainer.visibility = View.GONE
                binding.btnShowText.setText(R.string.show_more_button_text)
                binding.btnShowRecipe.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else{
                binding.recipeContainer.visibility = View.VISIBLE
                binding.btnShowText.setText(R.string.show_less_button_text)
                binding.btnShowText.setText(R.string.show_less_button_text)
                binding.btnShowRecipe.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }

        Log.i("Lifecycle task2", "onCreate was called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Lifecycle task2", "onRestart was called")
    }

    override fun onStart() {
        super.onStart()
        binding.recipeContainer.visibility = recipeExpanded
        binding.btnShowText.setText(
            if(binding.recipeContainer.isVisible)
                R.string.show_less_button_text
            else R.string.show_more_button_text
        )
        binding.btnShowRecipe.setImageResource(
            if(binding.recipeContainer.isVisible)
                R.drawable.ic_baseline_expand_less_24
            else
                R.drawable.ic_baseline_expand_more_24
        )
        Log.i("Lifecycle task2", "onStart was called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle task2", "onResume was called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle task2", "onPause was called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle task2", "onStop was called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle task2", "onDestroy was called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(RECIPE_VISIBILITY, binding.recipeContainer.visibility)
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val RECIPE_VISIBILITY = "recipeVisibility"
    }
}