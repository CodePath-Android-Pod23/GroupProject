import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfav.R
import com.google.android.material.card.MaterialCardView


class RecyclerGenreAdapter (private val onItemClicked: (genre: String) -> Unit): RecyclerView.Adapter<RecyclerGenreAdapter.ViewHolder>() {

    //sample array of genre for testing genre recyclerView
    private var genre = arrayOf("Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "History",
        "Horror", "Music", "Mystery", "Romance", "Science Fiction", "Thriller", "War", "Western")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerGenreAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.genre_post, parent, false)

        return ViewHolder(v, onItemClicked)
    }

    override fun onBindViewHolder(holder: RecyclerGenreAdapter.ViewHolder, position: Int) {
        holder.genreName.text = genre[position]

    }

    override fun getItemCount(): Int {
        return genre.size
    }

    inner class ViewHolder(itemView: View, private val onItemClicked: (genre: String) -> Unit): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var genreName: TextView
        var genreCard: MaterialCardView


        init {
            genreName = itemView.findViewById(R.id.tv_genreName)
            genreCard = itemView.findViewById(R.id.genreCard)


            //have to make custom checked function for cards
            //Pijamo: Not sure if it shud exist here
//            genreCard.setOnClickListener{
//                genreCard.setChecked(!genreCard.isChecked)
//                true
//            }

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            genreCard.setChecked(!genreCard.isChecked)
            val genre = genreName.text.toString()
            onItemClicked(genre)
        }

    }

}