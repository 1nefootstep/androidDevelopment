package com.example.todolist

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException

class NewTaskDialogFragment : DialogFragment() {
    interface NewTaskDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, task: String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    lateinit var newTaskDialogListener: NewTaskDialogListener

    companion object {
        fun newInstance(title: Int) : NewTaskDialogFragment {
            val newTaskDialogFragment = NewTaskDialogFragment()
            val args = Bundle()
            args.putInt("dialog_title", title)
            newTaskDialogFragment.arguments = args
            return newTaskDialogFragment
        }
    }

    override fun onCreateDialog(saveInstanceState: Bundle?): Dialog {
        val title = requireArguments().getInt("dialog_title")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        val dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_new_task, null)
        val task = dialogView.findViewById<EditText>(R.id.task)

        builder.setView(dialogView)
                .setPositiveButton(R.string.save) { _, _ ->
                    newTaskDialogListener?.onDialogPositiveClick(this, task.text.toString())
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    newTaskDialogListener?.onDialogNegativeClick((this))
                }
        return builder.create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            newTaskDialogListener = activity as NewTaskDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement NewTaskDialogListener.")
        }
    }
}