package com.example.composody.ui.faqs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.composody.data.CurrentFAQs
import com.example.composody.databinding.FragmentFaqsBinding
import com.example.composody.faqsdatabase.FAQsDatabase

class FAQsFragment : Fragment() {

    private lateinit var viewModel: FAQsViewModel

    private var _binding: FragmentFaqsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val faqsList = CurrentFAQs.listOfFAQs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFaqsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val application = requireNotNull(this.activity).application
        val dataSource = FAQsDatabase.getInstance(application).faqsDatabaseDao

        val viewModelFactory = FAQsViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FAQsViewModel::class.java)

        val adapter = FAQsAdapter(viewModel)
        binding.faqsList.adapter = adapter
        adapter.submitList(faqsList)

//        viewModel.selectedFAQ.observe(viewLifecycleOwner, Observer { faq ->
//            adapter.submitList(faq)
//        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}