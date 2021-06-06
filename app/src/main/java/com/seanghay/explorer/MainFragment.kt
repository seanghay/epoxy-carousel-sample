package com.seanghay.explorer

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlin.contracts.contract
import com.seanghay.explorer.databinding.FragmentMainBinding as Binding

class MainFragment : CoreFragment<Binding>(Binding::inflate) {

    private lateinit var viewModel: MainViewModel
    private lateinit var controller: MainController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        controller = MainController(this, viewModel)
        controller.onRestoreInstanceState(savedInstanceState)

        binding.epoxyRecyclerView.setController(controller)
        controller.requestModelBuild()
        controller.itemClick = {
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }

        viewModel.fetch(controller)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        controller.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

}