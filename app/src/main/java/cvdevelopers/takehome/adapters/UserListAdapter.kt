package cvdevelopers.takehome.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.models.ClientViewData
import androidx.recyclerview.widget.RecyclerView as RecyclerView1

open class UserListAdapter(val myDataset: List<ClientViewData>) :
    RecyclerView1.Adapter<UserListAdapter.MyViewHolder>() {

    inner class MyViewHolder(v: View) : RecyclerView1.ViewHolder(v){
        val userPicture: ImageView = v.findViewById(R.id.userPicture)
        val userName: TextView = v.findViewById(R.id.userName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val root: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        Glide.with(holder.userPicture)
            .load(item.pictureUrl)
            .circleCrop()
            .into(holder.userPicture)
        holder.userName.text = "${item.firstName} ${item.lastName}"
    }
}