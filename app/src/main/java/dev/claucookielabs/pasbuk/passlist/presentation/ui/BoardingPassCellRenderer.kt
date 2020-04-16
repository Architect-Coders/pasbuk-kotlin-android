package dev.claucookielabs.pasbuk.passlist.presentation.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import dev.claucookielabs.pasbuk.common.domain.model.Passbook
import dev.claucookielabs.pasbuk.common.presentation.utils.loadImage
import dev.claucookielabs.pasbuk.common.presentation.utils.show
import kotlinx.android.synthetic.main.item_view_pass.view.*

class BoardingPassCellRenderer : PassbookCellRenderer() {
    override fun renderBackground(passbook: Passbook, backgroundView: View) {

    }

    override fun renderTitle(passbook: Passbook, titleView: TextView) {
        titleView.text = passbook.logoText ?: ""
    }

    override fun renderImage(passbook: Passbook, imageView: ImageView) {
        if (passbook.hasNoLogos()) {
            imageView.show(false)
        } else {
            imageView.loadImage(passbook.logoImage ?: passbook.iconImage ?: "")
        }
    }

    override fun renderFields(passbook: Passbook, itemView: View) {
        passbook.headerFields.forEach { header ->
            val layoutParams = createHeaderLayoutParams(itemView.context)
            val headerText = createHeaderTextView(itemView.context, header)
            itemView.pass_row_headers.addView(
                headerText,
                layoutParams
            )
        }
    }

}
