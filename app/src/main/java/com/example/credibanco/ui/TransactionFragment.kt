package com.example.credibanco.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.credibanco.data.AuthorizationVO
import com.example.credibanco.viewmodel.TransactionViewModel
import com.example.credibanco.databinding.TransactionFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private var _binding: TransactionFragmentBinding? = null
    private val binding get() = _binding!!
    private val transactionViewModel by viewModels<TransactionViewModel>()
    private val authorization: AuthorizationVO = AuthorizationVO()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = TransactionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
        setTextDefault()
        //transactionViewModel = ViewModelProvider(requireActivity(), FactoryViewModel(requireContext()))[TransactionViewModel::class.java]
        transactionViewModel.createDataBase()
    }

    private fun listener() {
        binding.authorizationAppCompatButton.setOnClickListener {
            if (binding.numberTransactionEditText.text.toString().isBlank()) {
                Toast.makeText(context, "NÚMERO DE TRANSACCIÓN VACÍO", Toast.LENGTH_LONG).show()
            } else {
                transactionViewModel.getAuthorizationProcessTransaction(getDataAuthorization())
                Toast.makeText(context, "LA TRANSACCIÓN HA SIDO AUTORIZADA", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Obtiene información de los componentes visuales
     */
    private fun getDataAuthorization(): AuthorizationVO {
        authorization.id = binding.numberTransactionEditText.text.toString()
        authorization.commerceCode = binding.codeCommerceEditText.text.toString()
        authorization.terminalCode = binding.terminalEditText.text.toString()
        authorization.amount = "12345"
        authorization.card = binding.carEditText.text.toString()

        return AuthorizationVO(
            id = authorization.id,
            commerceCode = authorization.commerceCode,
            terminalCode = authorization.terminalCode,
            amount = authorization.amount,
            card = authorization.card
        )
    }

    /**
     * Setea información constante necesaria
     */
    private fun setTextDefault(){
        binding.codeCommerceEditText.setText("000123")
        binding.terminalEditText.setText("000ABC")
        binding.amountEditText.setText("12345".formatNumber())
        binding.carEditText.setText("1234567890123456")
    }

    /**
     * Devuelve un string número en una cadena de 2 decimales
     */
    private fun String.formatNumber(): String {
        val fraction = this.toInt() / 100.0
        return "%.2f".format(fraction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}