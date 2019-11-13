package com.example.lab7.addfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.lab7.R
import com.example.lab7.database.NoteDatabase
import com.example.lab7.databinding.FragmentAddDataBinding


class AddDataFragment : Fragment() {

    private lateinit var binding: FragmentAddDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddDataBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDAO

        val modelFactory =
            AddRecordViewModelFactory(dataSource, application)

        binding.singleRecordVM =
            ViewModelProviders.of(this, modelFactory).get(AddRecordViewModel::class.java)
                .also { view ->
                    view.shouldNavigateBack.observe(this, Observer { goBack ->
                        if (goBack) {
                            this.findNavController()
                                .navigate(R.id.action_addDataFragment_to_homeFragment)
                            view.shouldNavigateBack.value = false
                        }
                    })
                }
        // Inflate the layout for this fragment
        return binding.root
    }


}
