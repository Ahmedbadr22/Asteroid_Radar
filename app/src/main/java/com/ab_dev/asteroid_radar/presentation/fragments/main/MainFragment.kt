package com.ab_dev.asteroid_radar.presentation.fragments.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ab_dev.asteroid_radar.R
import com.ab_dev.asteroid_radar.app.AsteroidClickListener
import com.ab_dev.asteroid_radar.data.datasource.local.database.Database
import com.ab_dev.asteroid_radar.data.repository.AsteroidRepository
import com.ab_dev.asteroid_radar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val database = Database.getDatabaseInstance(requireContext())
        val viewModel = ViewModelProvider(
            this,
            MainViewModel.Factory(
                application = requireActivity().application,
                asteroidRepository = AsteroidRepository(database)
            )
        )[MainViewModel::class.java]

        binding.viewModel = viewModel

        viewModel.asteroids.observe(viewLifecycleOwner) { asteroids ->
            if (asteroids != null) {
                binding.statusLoadingWheel.visibility = View.GONE
            }
        }
        val adapter = MainAdapter(AsteroidClickListener { asteroid ->
            val navAction = MainFragmentDirections.actionShowDetail(asteroid)
            findNavController().navigate(navAction)
        })

        binding.asteroidRecycler.adapter = adapter

        val menuHost: MenuHost = requireActivity()
        menuInit(menuHost)

        return binding.root
    }

    private fun menuInit(menuHost: MenuHost) {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner)
    }
}
