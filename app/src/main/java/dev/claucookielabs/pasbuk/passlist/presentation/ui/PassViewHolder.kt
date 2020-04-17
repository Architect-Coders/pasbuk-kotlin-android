package dev.claucookielabs.pasbuk.passlist.presentation.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dev.claucookielabs.pasbuk.common.domain.model.PassType
import dev.claucookielabs.pasbuk.common.domain.model.Passbook
import dev.claucookielabs.pasbuk.passlist.presentation.ui.renderers.*
import kotlinx.android.synthetic.main.item_view_pass.view.*


class PassViewHolder(
    itemView: View,
    private val onClickAction: (Passbook) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(passbook: Passbook) {
        val passRenderer = PassbookCellRendererFactory.create(passbook)
        passRenderer.renderBackground(passbook, itemView.pass_row_card)
        passRenderer.renderImage(passbook, itemView.pass_row_icon)
        passRenderer.renderTitle(passbook, itemView.pass_row_title)
        passRenderer.renderFields(passbook, itemView.pass_row_headers)
        itemView.setOnClickListener { onClickAction(passbook) }
    }
}

class PassbookCellRendererFactory {

    companion object {
        fun create(passbook: Passbook): PassbookRowRenderer {
            return when (passbook.pass.passType) {
                PassType.BoardingPass -> BoardingPassRowRenderer()
                PassType.EventTicket -> EventTicketRowRenderer()
                PassType.Coupon -> CouponRowRenderer()
                PassType.StoreCard -> StoreCardRowRenderer()
                PassType.Generic -> GenericRowRenderer()
            }
        }
    }
}
