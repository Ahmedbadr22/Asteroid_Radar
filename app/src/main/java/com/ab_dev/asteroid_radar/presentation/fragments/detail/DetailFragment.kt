package com.ab_dev.asteroid_radar.presentation.fragments.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ab_dev.asteroid_radar.R
import com.ab_dev.asteroid_radar.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.asteroid = args.selectedAsteroid

        binding.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }

        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
