package com.example.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {

    lateinit var coordinatorLayout : CoordinatorLayout

    private val todoListItems = ArrayList<String>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoListItems.add("Horse")
        todoListItems.add("Cow")
        todoListItems.add("Camel")
        todoListItems.add("Sheep")
        todoListItems.add("Goat")
        todoListItems.add("I am a super long line. A super long line. yes, a long ling.")
        todoListItems.add("test testing")

        setSupportActionBar(findViewById(R.id.toolbar))
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { _ ->
            showNewTaskUI()
        }

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(todoListItems)
        recyclerView.adapter = adapter
        val dividerItemDecoration: DividerItemDecoration = DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_task_dialog_title)
        newFragment.show(supportFragmentManager, "newtask")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        todoListItems.add(task)
        adapter.notifyDataSetChanged()
        Snackbar.make(coordinatorLayout,
                "Task added successfully.",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        // do nothing
    }
}