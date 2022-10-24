package uz.orign.bookapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.orign.bookapp.R
import uz.orign.bookapp.databinding.BooksItemLayoutBinding
import uz.orign.bookapp.model.classes.byCategory.BookCt

class BooksbyCategoryAdapter(val onClick: (model: BookCt) -> Unit) : ListAdapter<BookCt, BooksbyCategoryAdapter.ViewHolder>(ITEM_DIFF) {

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<BookCt>() {
            override fun areItemsTheSame(oldItem: BookCt, newItem: BookCt) = oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: BookCt, newItem: BookCt): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BooksItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(private val binding: BooksItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bind(d: BookCt) {
            binding.apply {
                root.setOnClickListener {
                    onClick.invoke(d)
                }
                tvBookName.text = d.title
                Glide
                    .with(itemView.context)
                    .load( d.book_image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imBook)
            }
        }
    }
}