package com.example.composody.ui.faqs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.composody.databinding.ListItemFaqBinding
import com.example.composody.faqsdatabase.FAQ

class FAQsAdapter (
    val viewModel: FAQsViewModel
): ListAdapter<FAQ, FAQsAdapter.FAQsViewHolder>(FAQListDiffCallback()) {

    override fun onCreateViewHolder (
        parent: ViewGroup,
        viewType: Int
    ): FAQsAdapter.FAQsViewHolder {
        val view = ListItemFaqBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FAQsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FAQsAdapter.FAQsViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    inner class FAQsViewHolder(val binding: ListItemFaqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            var item = getItem(position)

            binding.mainLayout.setOnClickListener {
                viewModel.setFAQ(item)
            }

            var question = binding.faqQuestion
            var answer = binding.faqAnswer

            question.text = item.question
            answer.text = item.answer

        }
    }

}

class FAQListDiffCallback : DiffUtil.ItemCallback<FAQ>() {

    override fun areItemsTheSame(oldItem: FAQ, newItem: FAQ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FAQ, newItem: FAQ): Boolean {
        return oldItem.faqsId == newItem.faqsId
    }

}
