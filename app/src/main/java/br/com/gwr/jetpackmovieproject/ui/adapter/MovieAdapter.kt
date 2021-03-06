package br.com.gwr.jetpackmovieproject.ui.adapter

import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.gwr.jetpackmovieproject.databinding.MovieItemBinding
import br.com.gwr.jetpackmovieproject.domain.model.Movie
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import br.com.gwr.jetpackmovieproject.R


/**
 * Created by WCisang on 21/06/2018.
 */
class MovieAdapter(var listener : (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var list = ArrayList<Movie>()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<MovieItemBinding>(inflater, R.layout.movie_item, parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class MovieViewHolder(private var binding: MovieItemBinding?, var listener: (Movie) -> Unit) : RecyclerView.ViewHolder(binding?.root) {

        fun bind(movie: Movie) {
            binding?.movie = movie
            binding?.root?.setOnClickListener { listener(movie) }
            binding?.executePendingBindings()
        }
    }

    fun addAll(newList: List<Movie>?) {
        if (newList != null && newList.isNotEmpty()){
            list.addAll(newList)
            notifyDataSetChanged()
        }
    }
}