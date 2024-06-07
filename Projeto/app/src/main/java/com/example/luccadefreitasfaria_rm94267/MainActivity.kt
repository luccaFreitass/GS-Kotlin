package com.example.luccadefreitasfaria_rm94267

import android.os.Bundle
import android.view.View
import android.widget.Button


import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luccadefreitasfaria_rm94267.adapter.PraiaAdapter
import com.example.luccadefreitasfaria_rm94267.model.Praia
import com.example.luccadefreitasfaria_rm94267.ui.theme.Luccadefreitasfaria_rm94267Theme

class MainActivity : ComponentActivity(), PraiaAdapter.OnPraiaClickListener {

    private lateinit var praiaAdapter: PraiaAdapter

    private lateinit var praiaList: MutableList<Praia>

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
             Luccadefreitasfaria_rm94267Theme {
                AndroidView(


                    modifier = Modifier.fillMaxSize(),

                    factory = { context ->
                        val view = layoutInflater.inflate(R.layout.activity_main, null)
                        setupViews(view)
                        return@AndroidView view


                    }
                )
            }
        }
    }

    private fun setupViews(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        praiaList = mutableListOf()
        praiaAdapter = PraiaAdapter(praiaList)
        recyclerView.adapter = praiaAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        praiaAdapter.setOnPraiaClickListener(this)

        val buttonIncluir = view.findViewById<Button>(R.id.btnIncluir)
        buttonIncluir.setOnClickListener {
            adicionarPraia(view)
        }
    }

    private fun adicionarPraia(view: View) {
        val editPraia = view.findViewById<EditText>(R.id.etNomePraia)

        val editCidade = view.findViewById<EditText>(R.id.etCidade)

        val editEstado = view.findViewById<EditText>(R.id.etEstado)

        val nome = editPraia.text.toString().trim()
        val cidade = editCidade.text.toString().trim()
        val estado = editEstado.text.toString().trim()

        if (nome.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val novaPraia = Praia(nome, cidade, estado){
        }

        praiaAdapter.adicionarPraia(novaPraia)

        editPraia.text.clear()
        editCidade.text.clear()

        editEstado.text.clear()
    }

    override fun onExcluirClick(praia: Praia) {

        praiaAdapter.removerPraia(praia)


    }


}