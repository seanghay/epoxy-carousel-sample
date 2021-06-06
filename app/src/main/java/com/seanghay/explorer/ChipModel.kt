package com.seanghay.explorer

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.seanghay.explorer.databinding.ComponentChipBinding

@EpoxyModelClass
abstract class ChipModel : EpoxyModelWithHolder<ChipModel.Holder>() {

    @field:EpoxyAttribute
    var title: CharSequence? = null

    override fun getDefaultLayout(): Int = R.layout.component_chip

    override fun bind(holder: Holder) {
        holder.binding.textView.text = title
    }

    class Holder : EpoxyHolder() {

        lateinit var binding: ComponentChipBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentChipBinding.bind(itemView)
        }
    }
}