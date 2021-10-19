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
        faqsViewModel =
            ViewModelProvider(this).get(FaqsViewModel::class.java)

        _binding = FragmentFaqsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFaqs
        faqsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}