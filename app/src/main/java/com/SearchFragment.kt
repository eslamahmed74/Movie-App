package com

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.bottomNav.listfragment.MyListAdapter
import com.example.movieapp.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment(), TextWatcher {

    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding
    lateinit var adapter: MyListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this
        binding.searchViewModel = viewModel

        adapter = MyListAdapter(emptyList())
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.movieListRecyclyerSearch.layoutManager = layoutManager
        binding.movieListRecyclyerSearch.adapter = adapter

        binding.imgBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_searchFragment_to_listFragment)
        }
        binding.edSearch.addTextChangedListener(this)
        return binding.root
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val data = viewModel.searchFunctionality()
        adapter.updateItems(data.value.toData()!!)
    }
}
