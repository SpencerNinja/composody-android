package com.example.composody.ui.faqs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.composody.databinding.FragmentFaqsBinding
import com.example.composody.faqsdatabase.FAQsDatabase

class FaqsFragment : Fragment() {

    private lateinit var faqsViewModel: FaqsViewModel
    private var _binding: FragmentFaqsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFaqsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val application = requireNotNull(this.activity).application

        val dataSource = FAQsDatabase.getInstance(application).faqsDatabaseDao

//        val viewModelFactory = FaqsViewModelFactory(dataSource, application)

        faqsViewModel =
            ViewModelProvider(
                this).get(FaqsViewModel::class.java)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}