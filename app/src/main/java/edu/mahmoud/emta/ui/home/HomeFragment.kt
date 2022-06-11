package edu.mahmoud.emta.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.mahmoud.emta.R
import edu.mahmoud.emta.data.EmtaDataBase
import edu.mahmoud.emta.ui.emtaControl.EmtaControlFragment


class HomeFragment : Fragment() {

    private val dto by lazy {
        EmtaDataBase.createDb(requireContext()).emtaDto()
    }

    private val adapter by lazy { EmtaListAdapter(onCLick = ::onItemClicked) }


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
        rv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        val tvNonContent = view.findViewById<View>(R.id.tvNonContent)
        dto.listAll().observe(viewLifecycleOwner) {
            adapter.updateList(it)
            tvNonContent.isVisible = it.isEmpty()
        }

        view.findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            onItemClicked(-1)
        }

    }

    private fun onItemClicked(id: Long) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, EmtaControlFragment.createInstance(id))
            .addToBackStack("")
            .commit()
    }


}