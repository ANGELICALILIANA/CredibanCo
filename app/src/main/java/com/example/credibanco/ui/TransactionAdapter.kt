package com.example.credibanco.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.credibanco.id.ICancelItem
import com.example.credibanco.data.StatusAuthorizationVO
import com.example.credibanco.databinding.TransactionItemBinding

class TransactionAdapter(
    private var statusAuthorizationVO: List<StatusAuthorizationVO>,
    val calcelItem: ICancelItem<StatusAuthorizationVO>
    ) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(statusAuthorizationVO[position])
    }

    override fun getItemCount(): Int {
        return statusAuthorizationVO.size
    }

    inner class ViewHolder(private val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(statusAuthorizationVO: StatusAuthorizationVO) {

            binding.idTransactionTextView.text = statusAuthorizationVO.id
            binding.statusTransactionTextView.text = statusAuthorizationVO.statusDescription
            binding.idReceiptTextView.text = statusAuthorizationVO.receiptId

            binding.transactiontCardView.setOnClickListener {
                calcelItem.annulmentTransaction(statusAuthorizationVO)
            }

        }

    }

}