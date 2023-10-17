package dev.alibek.uz_rulugat.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.alibek.uz_rulugat.R
import dev.alibek.uz_rulugat.adapter.WordListAdapter
import dev.alibek.uz_rulugat.data.repository.ListRepository
import dev.alibek.uz_rulugat.model.Word

class LugatFragment() : Fragment(R.layout.fragment_lugat) {

    private lateinit var repository: ListRepository
    private lateinit var words: ArrayList<Word>
    private lateinit var adapter: WordListAdapter
    private lateinit var tv:TextView
    lateinit var rvList:RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(view: View) {


        repository = ListRepository(requireActivity().application)


        val llClick = view.findViewById<LinearLayout>(R.id.ll_transform)
        rvList = view.findViewById(R.id.rv_list)
        Log.d("@@@@@", "initViews: rv inits")
        val etSearch = view.findViewById<EditText>(R.id.et_search)

        adapter= WordListAdapter()
        loadWord()
        rvList.adapter=adapter


        etSearch.addTextChangedListener {
            adapter.filter.filter(it)
            adapter.submitList(words)
        }
    }

    private fun loadWord() {
        words = ArrayList()
        words = repository.loadWords() as ArrayList<Word>
        adapter.submitList(words)
        Log.d("@@@@@", "loadWord: ")
    }

}

