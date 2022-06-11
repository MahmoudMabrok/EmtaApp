package edu.mahmoud.emta.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import edu.mahmoud.emta.R
import edu.mahmoud.emta.data.EmtaDataBase
import edu.mahmoud.emta.data.EmtaItem
import java.util.concurrent.Executors


class HomeFragment : Fragment() {


    private val dto by lazy {
        EmtaDataBase.createDb(requireContext()).emtaDto()
    }

    private val exceuter = Executors.newSingleThreadExecutor()

    private val adapter by lazy { EmtaListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rvList)
        rv.adapter = adapter

        exceuter.execute {
            dto.insert(EmtaItem("A"))
        }

        exceuter.execute {
            dto.listAll().observe(viewLifecycleOwner) {
                adapter.updateWordsCountList(it)
            }
        }

    }


}