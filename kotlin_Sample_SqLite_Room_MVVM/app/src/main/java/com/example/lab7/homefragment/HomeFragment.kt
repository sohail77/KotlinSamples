package com.example.lab7.homefragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab7.R
import com.example.lab7.database.Note
import com.example.lab7.database.NoteDatabase
import com.example.lab7.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDAO

        val modelFactory =
            NotesListViewModelFactory(dataSource, application)

        binding.list = ViewModelProviders.of(this, modelFactory).get(NoteListViewModel::class.java)
            .also { view ->
                view.noteList.observe(this,
                    Observer<List<Note>> { notes ->
                        if (notes.isNotEmpty()) {
                            view.setUpAdapter(notes)
                        }
                    })
            }

        setRecyclerViewProperties()

        binding.addRecordBtn.setOnClickListener { addRecord() }

        binding.lifecycleOwner = this

        return binding.root
    }

    fun addRecord() = findNavController().navigate(R.id.action_homeFragment_to_addDataFragment)

    fun setRecyclerViewProperties() {
        binding.noteList.setHasFixedSize(true)
        binding.noteList.layoutManager = LinearLayoutManager(context)
        binding.noteList.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }
}
