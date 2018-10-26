package com.tcleard.wannacook.scene.ingredient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.model.Ingredient
import kotlinx.android.synthetic.main.itemview_unit.view.*

class IngredientUnitAdapter(
        context: Context
) : ArrayAdapter<IngredientUnitItem>(context, R.layout.itemview_unit, R.id.unitName, Ingredient.Unit.values().map { IngredientUnitItem(it) }) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
            getView(convertView, parent, position)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
            getView(convertView, parent, position)

    private fun getView(convertView: View?, parent: ViewGroup, position: Int): View {
        val item = getItem(position)
        val view = convertView ?: layoutInflater.inflate(R.layout.itemview_unit, parent, false)
        view.unitName.setText(item.getNameRes())
        return view
    }

}