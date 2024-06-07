package com.example.luccadefreitasfaria_rm94267.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luccadefreitasfaria_rm94267.R
import com.example.luccadefreitasfaria_rm94267.model.Praia

class PraiaAdapter(
    private val praiaList: MutableList<Praia>

) : RecyclerView.Adapter<PraiaAdapter.PraiaViewHolder>() {

    interface OnPraiaClickListener {

        fun onExcluirClick(praia: Praia)

    }

    private var onPraiaClickListener: OnPraiaClickListener? = null

    fun setOnPraiaClickListener(listener: OnPraiaClickListener) {

        this.onPraiaClickListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PraiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_praia, parent, false)
        return PraiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PraiaViewHolder, position: Int) {
        val currentPraia = praiaList[position]
        holder.tvPraiaInfo.text = "${currentPraia.nome}, ${currentPraia.cidade}, ${currentPraia.estado}"
        holder.btnExcluir.setOnClickListener {
            onPraiaClickListener?.onExcluirClick(currentPraia)


        }
    }

    override fun getItemCount() = praiaList.size

    fun adicionarPraia(praia: Praia) {

        praiaList.add(praia)
        notifyItemInserted(praiaList.size - 1)


    }

    fun removerPraia(praia: Praia) {

        val position = praiaList.indexOf(praia)
        if (position != -1) {

            praiaList.removeAt(position)
            notifyItemRemoved(position)

        }

    }

    class PraiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvPraiaInfo: TextView = itemView.findViewById(R.id.tvPraiaInfo)
        val btnExcluir: Button = itemView.findViewById(R.id.btnExcluir)

    }

}