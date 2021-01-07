package com.kiljae.mylistcollection.view.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import com.kiljae.mylistcollection.R
import kotlinx.android.synthetic.main.dialog_custom_yesno.view.*

class MyCustomDialog {
    data class Builder(
            var context: Context? = null,
            var textTitle: String = "",
            var textMessage: String = "",
            var textNo: String = "",
            var textYes: String = "",
            var onFinished: (()->Unit)? = null,
            var onClickNo: ((AlertDialog)->Unit)? = null,
            var onClickYes: ((AlertDialog)->Unit)? = null
    ){
        lateinit var dialog: AlertDialog
        fun context(context: Context) = apply { this.context = context }
        fun setTitle(textTitle: String) = apply { this.textTitle = textTitle }
        fun setMessage(textMessage: String) = apply { this.textMessage = textMessage }
        fun setOnFinished(onFinished: (() -> Unit)) = apply { this.onFinished = onFinished }
        fun setOnClickNo(textNo: String, onClickNo: ((AlertDialog) -> Unit)) = apply {
            this.textNo = textNo
            this.onClickNo = onClickNo
        }
        fun setOnClickYes(textYes: String, onClickYes: ((AlertDialog) -> Unit)) = apply {
            this.textYes = textYes
            this.onClickYes = onClickYes
        }
        fun build(): AlertDialog{
            context?.run {
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogView = inflater.inflate(R.layout.dialog_custom_yesno, null)
                dialog = AlertDialog.Builder(this)
                        .setView(dialogView)
                        .create()
                dialog?.window?.let {
                    val windowLayoutParam = it.attributes
                    windowLayoutParam.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
                    it.attributes = windowLayoutParam
                    it.setBackgroundDrawableResource(R.drawable.dra_round_white)
                }

                dialogView.tvTitle.text = textTitle
                dialogView.tvMessage.text = textMessage
                if(textNo.isNullOrEmpty()){
                    dialogView.btnNo.visibility = View.GONE
                }else {
                    dialogView.btnNo.visibility = View.VISIBLE
                    dialogView.btnNo.text = textNo
                    dialogView.btnNo.setOnClickListener {
                        onClickNo?.invoke(dialog)
                    }
                }
                if(textYes.isNullOrEmpty()){
                    dialogView.btnYes.visibility = View.GONE
                }else {
                    dialogView.btnYes.visibility = View.VISIBLE
                    dialogView.btnYes.text = textYes
                    dialogView.btnYes.setOnClickListener {
                        onClickYes?.invoke(dialog)
                    }
                }

                dialog?.setOnDismissListener {
                    onFinished?.invoke()
                }
            }

            return dialog
        }

    }

}