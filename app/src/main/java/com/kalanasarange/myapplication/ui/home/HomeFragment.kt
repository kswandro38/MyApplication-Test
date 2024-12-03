package com.kalanasarange.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.google.android.material.snackbar.Snackbar
import com.kalanasarange.myapplication.R
import com.kalanasarange.myapplication.api.ResponseState
import com.kalanasarange.myapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        // Initialize User list
        adapter = UserAdapter()
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        // Observe User list from view-model
        observeData()
    }

    /**
     * Observe the data flow from User response which has fetched from API.
     * Data flow has wrapped with responseState object with represent the data flow
     * current status.
     */
    private fun observeData() {
        viewModel.userResponse.asLiveData().observe(viewLifecycleOwner) { responseState ->
            when (responseState) {
                // Idle state
                is ResponseState.Idle -> {}

                // Loading state
                is ResponseState.Loading -> {
                    setDataState(responseState)
                }

                // Success state
                is ResponseState.Success -> {
                    setDataState(responseState)
                    adapter.setUserList(responseState.data.users)
                }

                // Error state
                is ResponseState.Error -> {
                    Log.e(TAG, "Error: ${responseState.errorMessage}")

                    // Update UI
                    setDataState(responseState)

                    // Show error message on Snack bar
                    Snackbar
                        .make(
                            binding.root,
                            resources.getString(R.string.error_msg_fail_to_load_users),
                            Snackbar.LENGTH_INDEFINITE
                        )
                        .setAction(resources.getString(R.string.error_btn_retry)) {
                            viewModel.loadUsers()
                        }
                        .show()
                }
            }
        }
    }

    /**
     * Updating the UI's
     * Progress bar - visible when data is in loading state
     * Show Recycler view with data when data has loaded
     * Error message - When there is an error loading data
     */
    private fun <T> setDataState(responseState: ResponseState<T>) {
        binding.apply {
            when (responseState) {
                is ResponseState.Idle -> {
                    // Nothing on idle state
                }

                // Show loading progress bar & hide message and recycle view
                is ResponseState.Loading -> {
                    recyclerView.isVisible = false
                    progressBar.isVisible = true
                    imgInfo.isVisible = false
                    txtLoadingError.isVisible = false
                }

                // Hide progress bar & show data
                is ResponseState.Success -> {
                    recyclerView.isVisible = true
                    progressBar.isVisible = false
                    imgInfo.isVisible = false
                    txtLoadingError.isVisible = false
                }

                // Hide progress bar, recycle view & show error message
                is ResponseState.Error -> {
                    recyclerView.isVisible = false
                    progressBar.isVisible = false
                    imgInfo.isVisible = true
                    txtLoadingError.isVisible = true
                    txtLoadingError.text = responseState.errorTitle
                }
            }
        }
    }
}