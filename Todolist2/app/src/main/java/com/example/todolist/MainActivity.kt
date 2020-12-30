package com.example.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {

    lateinit var coordinatorLayout : CoordinatorLayout

    private val todoListItems = ArrayList<String>()
    private var listAdapter : ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { _ ->
            showNewTaskUI()
        }
    }

    private fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_task_dialog_title)
        newFragment.show(supportFragmentManager, "newtask")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        todoListItems.add(task)
        listAdapter?.notifyDataSetChanged()
        Snackbar.make(coordinatorLayout,
                        "Task added successfully.",
                        Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        // do nothing
    }
}