package com.codewithandro.apitest

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProdAdapter(var context: Activity,var  proList: List<Product>?):
RecyclerView.Adapter<ProdAdapter.MyViewHolder>()
{
    //1 -> featch the xml views
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val prodImg = itemView.findViewById<ImageView>(R.id.proImg)
        val prodName = itemView.findViewById<TextView>(R.id.prodTitle)
        val prodRateCard = itemView.findViewById<TextView>(R.id.ratingCard)
        val prodRateBar = itemView.findViewById<RatingBar>(R.id.ratingBar)

    }

    //2 -> list.size
    override fun getItemCount(): Int {

        return proList?.size!!
    }
    //3 -> layoutInflater the eachRow the layoutManager pass
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layInflater = LayoutInflater.from(context).inflate(R.layout.eachcart,parent,false)
        return MyViewHolder(layInflater)
    }

    //4 -> finnaly set the data in view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //currentItem -> list traverse
        val curItem = proList?.get(position)

        Picasso.get().load(curItem?.thumbnail).into(holder.prodImg)
        holder.prodName.text = curItem?.title
        holder.prodRateCard.text = curItem?.rating.toString()
        holder.prodRateBar.rating = curItem?.rating?.toFloat()!!
    }
}