package com.example.test_valery.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_valery.R
import com.example.test_valery.data.Resp.Responce

class Adapter() : RecyclerView.Adapter<FactHolder>() {
     var list: Responce = Responce()

    lateinit var listener: OnitemClickListner

    interface OnitemClickListner{

        fun OnClickItem(position: Int)
    }

    fun setOnClickLisner( newListner: OnitemClickListner){
        listener = newListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        return FactHolder(listener,LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
       holder.text.setText(list.get(position).text)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class FactHolder(listener: Adapter.OnitemClickListner, item: View) : RecyclerView.ViewHolder(item){
     var text : TextView = item.findViewById(R.id.item_text)
        init {
            item.setOnClickListener {
                listener.OnClickItem(adapterPosition)
            }
        }

    }

