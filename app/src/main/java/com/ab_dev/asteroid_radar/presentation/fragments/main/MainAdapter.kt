package com.ab_dev.asteroid_radar.presentation.fragments.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ab_dev.asteroid_radar.app.AsteroidClickListener
import com.ab_dev.asteroid_radar.databinding.AsteroidItemBinding
import com.ab_dev.asteroid_radar.domain.models.Asteroid

class MainAdapter(private val clickListener : AsteroidClickListener) : ListAdapter<Asteroid, MainAdapter.MainViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.bind(clickListener, asteroid)
    }

    class MainViewHolder(private val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: AsteroidClickListener, asteroidItem: Asteroid) {
            binding.apply {
                asteroid = asteroidItem
                clickListener = listener
                executePendingBindings()
            }
        }

        companion object {
            fun form(parent: ViewGroup) : MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidItemBinding.inflate(layoutInflater, parent, false)
                return MainViewHolder(binding)
            }
        }
    }
}