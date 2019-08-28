package com.example.newsapi
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapi.ActivitiesAndFragments.FullArticle
import com.example.newsapi.DataClasses.Article


class NewsAdapter(private var newsArticleArray: ArrayList<Article>, private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false), context)
    }

    override fun getItemCount(): Int {
        return newsArticleArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel: Article = newsArticleArray[position]
        holder.bind(dataModel)

        holder.itemView.setOnClickListener()
        {
            val fullArticleIntent = Intent(context,FullArticle::class.java)
            fullArticleIntent.putExtra("title",dataModel.title)
            fullArticleIntent.putExtra("source",dataModel.source.name)
            fullArticleIntent.putExtra("publishDate",dataModel.publishedAt)
            fullArticleIntent.putExtra("content",dataModel.content)
            fullArticleIntent.putExtra("image",dataModel.urlToImage)
            fullArticleIntent.putExtra("description",dataModel.description)

            fullArticleIntent.putExtra("position",holder.adapterPosition.toString())

            context.startActivity(fullArticleIntent)

        }
    }


    class ViewHolder(itemview : View, context: Context) : RecyclerView.ViewHolder(itemview) {
        private var mTitleView: TextView? = null
        private var mPublishDate:TextView?=null
        private var mImageView : ImageView?=null
        private var mContext : Context?=null

        init {
            mTitleView = itemview.findViewById(R.id.news_item_title)
            mPublishDate=itemview.findViewById(R.id.news_item_publish_date)
            mImageView=itemview.findViewById(R.id.news_item_image)

            mContext = context
        }

        fun bind(dataModel : Article) {
            mTitleView?.text = dataModel.title
            mPublishDate?.text=dataModel.publishedAt

            val options = RequestOptions()
                .centerCrop()
                .apply(RequestOptions.fitCenterTransform())
                //.placeholder(R.mipmap.ic_launcher)
                //.error(R.mipmap.ic_launcher)


            Glide.with(mContext!!).load(dataModel.urlToImage).apply(options).into(mImageView!!)
        }

    }
}