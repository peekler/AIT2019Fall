package hu.ait.aitfourmfall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import hu.ait.aitfourmfall.R
import hu.ait.aitfourmfall.data.Post
import kotlinx.android.synthetic.main.post_row.view.*

class PostsAdapter(
    private val context: Context, private val uid: String
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>()
{

    private var postsList = mutableListOf<Post>()
    private var postKeys = mutableListOf<String>()

    private var lastIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.post_row, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var post = postsList[position]

        holder.tvAuthor.setText(post.author)
        holder.tvTitle.setText(post.title)
        holder.tvBody.setText(post.body)

        setAnimation(holder.itemView, position)
    }

    fun addPost(post: Post, key: String) {
        postsList.add(post)
        postKeys.add(key)

        notifyDataSetChanged()
    }


    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastIndex) {
            val animation = AnimationUtils.loadAnimation(context,
                android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastIndex = position
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor = itemView.tvAuthor
        val tvTitle = itemView.tvTitle
        val tvBody = itemView.tvBody
    }
}