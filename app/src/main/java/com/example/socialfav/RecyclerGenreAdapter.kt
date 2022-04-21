import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfav.R
import com.google.android.material.card.MaterialCardView


class RecyclerGenreAdapter: RecyclerView.Adapter<RecyclerGenreAdapter.ViewHolder>() {

    //sample array of genre for testing genre recyclerView
    private var genre = arrayOf("Horror", "Comedy", "Action", "Adventure", "Drama", "Science Fiction", "Thriller", "Western", "Romance", "Musical", "Fantasy")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerGenreAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.genre_post, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerGenreAdapter.ViewHolder, position: Int) {
        holder.genreName.text = genre[position]

    }

    override fun getItemCount(): Int {
        return genre.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var genreName: TextView
        var genreCard: MaterialCardView


        init {
            genreName = itemView.findViewById(R.id.tv_genreName)
            genreCard = itemView.findViewById(R.id.genreCard)


            //have to make custom checked function for cards
            //Pijamo: Not sure if it shud exist here
            genreCard.setOnClickListener{
                genreCard.setChecked(!genreCard.isChecked)
                true
            }
        }

    }

}