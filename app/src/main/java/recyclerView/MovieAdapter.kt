package recyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.find_my_movies.R
import com.example.find_my_movies.activites.MovieDetailsActivity
import helper.Constants
import helper.loadImage
import model.Movie
import retrofit2.http.Tag

class MovieAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = ArrayList<Movie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(movieList: ArrayList<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var movieImage: ImageView = view.findViewById(R.id.movie_img)
        val movieTitle: TextView = view.findViewById(R.id.movie_name_tv)
        val movieReleaseText: TextView = view.findViewById(R.id.release_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        val imageUrl = movie.titlePosterImageModel?.url
        holder.movieImage.loadImage(imageUrl)
        holder.movieTitle.text = movie.titleNameText
        holder.movieReleaseText.text = movie.titleReleaseText
        holder.movieImage.tag = movie
        holder.movieTitle.tag = movie
        // Set on click listener
        holder.movieImage.setOnClickListener { view ->
            val titleId = getTitleId(view.tag)
            showMovieDetails(titleId)
        }
        holder.movieTitle.setOnClickListener { view ->
            val titleId = getTitleId(view.tag)
            showMovieDetails(titleId)
        }
    }

    private fun showMovieDetails(titleId: String) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.TITLE_ID, titleId)
        context.startActivity(intent)
    }

    private fun getTitleId(tag: Any): String {
        return (tag as Movie).id
    }
}
