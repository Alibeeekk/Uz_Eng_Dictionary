package dev.alibek.uz_rulugat.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.alibek.uz_rulugat.R
import dev.alibek.uz_rulugat.model.Word

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.LugatViewHolder>(), Filterable {

    val list = ArrayList<Word>()
    private var itemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugatViewHolder {
        return LugatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_lugat, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Word>) {

        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LugatViewHolder, position: Int) {
        val word = list[position]
        val lotpron = word.lot_pron
        holder.apply {
            title.text = word.ru
            titleLot.text = word.lot
            titlePronoun.text = word.lot_pron
        }
    }

    class LugatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tv_title)
        val titleLot: TextView = view.findViewById(R.id.tv_titlelot)
        val titlePronoun: TextView = view.findViewById(R.id.tv_titlelotpron)

    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private var customFilter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val newList: ArrayList<Word> = ArrayList()
            if (p0.isNullOrEmpty()) {
                newList.addAll(list)
            } else {
                for (item in list) {
                    if ((item.ru?.lowercase()?.contains(p0.toString().lowercase()) == true)
                        || (item.lot?.lowercase()?.contains(p0.toString().lowercase()) == true)
                    ) {
                        Log.d("aaaaaaa","qidiruv")
                        newList.add(item)
                    }
                }
            }
            val result = FilterResults()
            result.values = newList
            return result
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            list.clear()
            list.addAll(p1?.values as ArrayList<Word>)
            notifyDataSetChanged()
        }

    }

}
