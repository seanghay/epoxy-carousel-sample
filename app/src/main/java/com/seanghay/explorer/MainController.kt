package com.seanghay.explorer

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group

class MainController(
    private val fragment: Fragment,
    private val viewModel: MainViewModel,
) : AsyncEpoxyController() {

    var itemClick: (() -> Unit)? = null


    init {
        viewModel.loading.observe(fragment.viewLifecycleOwner) {
            requestModelBuild()
        }
    }

    override fun buildModels() {
        if (viewModel.loading.value == true) {
            chip {
                id("loading")
                title("Loading.......")
            }
        } else {
            carousel {
                id("ajsdkasd")
                models(this@MainController.createModels())
            }

            for (item in viewModel.items) {
                chip {
                    id("item:${item.id}")
                    title(item.title)

                    onBind { _, view, _ ->
                        view.binding.root.setOnClickListener { _ ->
                            this@MainController.itemClick?.invoke()
                        }
                    }
                }
            }
        }


    }

    private fun createModels(): MutableList<EpoxyModel<*>> {
        return viewModel.items.map {
            ChipModel_().id(it.id).title(it.title)
        }.toMutableList()
    }


}