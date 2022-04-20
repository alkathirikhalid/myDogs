package com.alkathirikhalid.dogs.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkathirikhalid.dogs.R
import com.alkathirikhalid.dogs.databinding.FragmentListBinding
import com.alkathirikhalid.dogs.model.DogBreed
import com.alkathirikhalid.dogs.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    private val viewModel: ListViewModel by activityViewModels()
    private val dogsListAdapter = DogsListAdapter(arrayListOf()) // Empty

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refresh()
        binding.dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.dogsList.visibility = View.GONE
            binding.listError.visibility = View.GONE
            binding.loadingView.visibility = View.VISIBLE
            viewModel.fetchFromRemote()
            binding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogs.observe(viewLifecycleOwner) { dogs: List<DogBreed> ->
            dogs.let {
                binding.dogsList.visibility = View.VISIBLE
                dogsListAdapter.refreshClear() // For Test / Demo
                dogsListAdapter.updateDogList(dogs as ArrayList<DogBreed>)
            }
        }
        viewModel.dogsLoadError.observe(viewLifecycleOwner) { isError: Boolean ->
            isError.let {
                binding.listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading: Boolean ->
            isLoading.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.dogsList.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_listFragment_to_settingsFragment -> {
                view?.findNavController()
                    ?.navigate(ListFragmentDirections.actionListFragmentToSettingsFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}