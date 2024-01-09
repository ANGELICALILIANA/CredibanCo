package com.example.credibanco.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.credibanco.id.ICancelItem
import com.example.credibanco.data.StatusAuthorizationVO
import com.example.credibanco.viewmodel.TransactionViewModel
import com.example.credibanco.databinding.HistoricalFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoricalFragment : Fragment(), ICancelItem<StatusAuthorizationVO> {

    private var _binding: HistoricalFragmentBinding? = null
    private val binding get() = _binding!!
    private val transactionViewModel by viewModels<TransactionViewModel>()
    private var statusAuthorizationVO: MutableList<StatusAuthorizationVO> = mutableListOf<StatusAuthorizationVO>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = HistoricalFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionViewModel.getTransactionStatus()
        observeData()
        filter()
    }

    private fun observeData(){
        transactionViewModel.getAllTransactions().observe(viewLifecycleOwner) {
            statusAuthorizationVO = it.toMutableList()
            binding.historicalRecyclerView.adapter = TransactionAdapter(statusAuthorizationVO, this)
        }
    }

    /**
     * Filtra información por receiptId, transacciones autorizadas y transacciones anuladas
     * @param receiptId: Id de recepcción, retorna cuando al momento de autorizar una transacción
     */
    private fun filter() {

        var filter : List<StatusAuthorizationVO> = statusAuthorizationVO
        binding.receiptEditText.addTextChangedListener {
            filter = statusAuthorizationVO.filter { data ->
                data.receiptId.uppercase().contains(it.toString().uppercase())
            }
            binding.historicalRecyclerView.adapter = TransactionAdapter(filter, this)
        }

        binding.authorizedTransactionButton.setOnClickListener {
            filter = statusAuthorizationVO.filter { it.statusDescription == "Aprobada" }
            binding.historicalRecyclerView.adapter = TransactionAdapter(filter, this)
        }

        binding.annulmentTransactionButton.setOnClickListener {
            filter = statusAuthorizationVO.filter { it.statusDescription == "Anulada" }
            binding.historicalRecyclerView.adapter = TransactionAdapter(filter, this)
        }

    }

    /**
     * Anula la transacción cuando es oprimido
     */
    override fun annulmentTransaction(item: StatusAuthorizationVO) {
        transactionViewModel.cancelTransaction(item)
        item.statusDescription = "Anulada"
        binding.historicalRecyclerView.adapter = TransactionAdapter(statusAuthorizationVO, this)
        Toast.makeText(context, "LA TRANSACCIÓN ${item.id} HA SIDO ANULADA", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}