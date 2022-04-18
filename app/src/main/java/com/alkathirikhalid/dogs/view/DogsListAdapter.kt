package com.alkathirikhalid.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alkathirikhalid.dogs.R
import com.alkathirikhalid.dogs.databinding.ItemDogBinding
import com.alkathirikhalid.dogs.model.DogBreed
import com.alkathirikhalid.dogs.util.getProgressDrawable
import com.alkathirikhalid.dogs.util.loadImage

class DogsListAdapter(private val dogsList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.binding.dogBreed = dogsList[position]
        holder.binding.root.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(position)
            it.findNavController().navigate(action)
        }
        holder.binding.imageView.loadImage(
            dogsList[position].imageUrl,
            getProgressDrawable(holder.binding.imageView.context)
        )

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return dogsList.size
    }

    fun updateDogList(newDogsList: ArrayList<DogBreed>) {
        dogsList.addAll(itemCount, newDogsList)
        notifyItemRangeChanged(itemCount, newDogsList.size)
    }

    fun refreshClear() {
        dogsList.clear()
        notifyDataSetChanged()
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemDogBinding = ItemDogBinding.bind(view)
    }
}