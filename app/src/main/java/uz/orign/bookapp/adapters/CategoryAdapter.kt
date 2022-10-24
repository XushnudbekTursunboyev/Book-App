package uz.orign.bookapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.orign.bookapp.R
import uz.orign.bookapp.databinding.CategoriesItemLayoutBinding
import uz.orign.bookapp.model.classes.CategoryClass.Result

class CategoryAdapter(val onClick: (model: uz.orign.bookapp.model.classes.CategoryClass.Result) -> Unit) : ListAdapter<Result, CategoryAdapter.ViewHolder>(
    ITEM_DIFF
) {

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result) = oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoriesItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(private val binding: CategoriesItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bind(d: Result) {
            binding.apply {
                root.setOnClickListener {
                    onClick.invoke(d)
                }
                tvCategories.text = d.display_name
            }
        }
    }
}