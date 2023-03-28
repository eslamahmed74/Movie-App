package com.example.movieapp.bottomNav.listfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.local.MovieEntity
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentListBinding
import com.example.movieapp.databinding.MylistItemBinding
import com.example.movieapp.utils.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var bindingItems: MylistItemBinding
    private val viewModel by activityViewModels<MyListViewModel>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        bindingItems = DataBindingUtil.inflate(inflater, R.layout.mylist_item, container, false)

        binding.lifecycleOwner = this
        binding.listViewModel = viewModel
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.movieListRecyclyer.layoutManager = layoutManager
        var adapter = MyListAdapter(emptyList())
        binding.movieListRecyclyer.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                adapter.setOnItemClickListener {
                    var dataAfterDeletion = viewModel.deleteItemFromList(it.id)
                    adapter.updateItems(dataAfterDeletion.toData()!!)
                }
            }
        }

        return binding.root
    }
}