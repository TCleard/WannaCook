package com.tcleard.wannacook.scene.recipe.step

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.recipe.ARecipeFragment
import com.tcleard.wannacook.scene.recipe.step.adapter.StepAdapter
import com.tcleard.wannacook.scene.recipe.step.adapter.vm.StepViewModel
import kotlinx.android.synthetic.main.fragment_step.*
import javax.inject.Inject

class StepFragment : ARecipeFragment<StepPresenter>(), StepPresenter.StepView {

    @Inject
    lateinit var adapter: StepAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerStepComponent.builder()
                .appComponent(appComponent)
                .stepModule(StepModule())
                .build()
                .inject(this)

        presenter.attach(this)

        presenter.setRecipe(recipe)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_step, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stepList.layoutManager = LinearLayoutManager(requireContext())
        stepList.adapter = adapter

    }

    /** StepView **/

    override fun showSteps(steps: List<StepViewModel>) {
        adapter.setItems(steps)
    }

}