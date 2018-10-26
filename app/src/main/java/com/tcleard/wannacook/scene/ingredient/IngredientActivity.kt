package com.tcleard.wannacook.scene.ingredient

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.setHomeIcon
import com.tcleard.wannacook.core.extension.watch
import com.tcleard.wannacook.core.model.Ingredient
import com.tcleard.wannacook.scene.ingredient.adapter.IngredientUnitAdapter
import com.tcleard.wannacook.scene.ingredient.adapter.IngredientUnitItem
import com.tcleard.wannacook.ui.controller.AActivity
import com.tcleard.wannacook.ui.controller.IController
import kotlinx.android.synthetic.main.activity_ingredient.*
import kotlinx.android.synthetic.main.include_toolbar.view.*
import javax.inject.Inject

class IngredientActivity : AActivity<IngredientPresenter>(), IngredientPresenter.IngredientView, AdapterView.OnItemSelectedListener {

    companion object {

        private val EXTRA_INGREDIENT = "ingredient"
        private val EXTRA_COUNT = "count"

        fun builder(controller: IController): Builder = Builder(controller)

        class Builder(controller: IController) : AActivity.Builder(controller) {

            override val activityClass = IngredientActivity::class.java

            fun with(ingredient: Ingredient): Builder {
                intent.putExtra(EXTRA_INGREDIENT, ingredient)
                return this
            }

            fun with(count: Int): Builder {
                intent.putExtra(EXTRA_COUNT, count)
                return this
            }

        }

        fun retrieveIngredient(intent: Intent?): Ingredient? =
                intent?.getSerializableExtra(EXTRA_INGREDIENT) as? Ingredient

    }

    @Inject
    lateinit var adapter: IngredientUnitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient)

        DaggerIngredientComponent.builder()
                .appComponent(appComponent)
                .ingredientModule(IngredientModule())
                .build()
                .inject(this)

        presenter.attach(this)

        setSupportActionBar(ingredientToolbarLayout.toolbar)
        supportActionBar?.setHomeIcon(R.mipmap.close)

        ingredientName.watch(afterTextChanged = {
            presenter.onNameChanged(ingredientName.text.toString())
        })
        ingredientQuantity.watch(afterTextChanged = {
            presenter.onQuantityChanged(ingredientQuantity.text.toString().toDoubleOrNull() ?: 0.0)
        })

        ingredientUnitSpinner.adapter = adapter
        ingredientUnitSpinner.onItemSelectedListener = this

        val ingredient = intent.getSerializableExtra(EXTRA_INGREDIENT) as? Ingredient
        val count = intent.getIntExtra(EXTRA_COUNT, 1)

        presenter.init(count, ingredient)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ingredient, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.menuIngredientValidate -> presenter.onValidateClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    /** IngredientView **/

    override fun showTitle(titleRes: Int) {
        supportActionBar?.setTitle(titleRes)
    }

    override fun showName(name: String) {
        ingredientName.setText(name)
    }

    override fun showUnit(unitItem: IngredientUnitItem) {
        ingredientUnitSpinner.setSelection(adapter.getPosition(unitItem))
    }

    override fun showQuantity(quantity: String) {
        ingredientQuantity.setText(quantity)
    }

    override fun showCount(count: Int) {
        ingredientQuantityInfo.text = resources.getQuantityString(R.plurals.ingredientQuantityCount, count, count)
    }

    override fun showQuitPopup() {
        dialogManager.builder(this)
                .setTitle(R.string.ingredientQuitTitle)
                .setMessage(R.string.ingredientQuitMessage)
                .setPositiveButton(R.string.ingredientQuitPositive) { dialog ->
                    dialog.dismiss()
                    finish()
                }
                .setNegativeButton(R.string.ingredientQuitNegative) { dialog ->
                    dialog.dismiss()
                }
                .build().show()
    }

    override fun quitWithIngredient(ingredient: Ingredient) {
        val data = Intent()
        data.putExtra(EXTRA_INGREDIENT, ingredient)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    /** Listeners **/

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.onUnitSelected(adapter.getItem(position))
    }

}