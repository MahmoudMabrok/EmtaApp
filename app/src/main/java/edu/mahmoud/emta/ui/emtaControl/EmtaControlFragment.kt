package edu.mahmoud.emta.ui.emtaControl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import edu.mahmoud.emta.DateOperation
import edu.mahmoud.emta.R
import edu.mahmoud.emta.data.EmtaDataBase
import edu.mahmoud.emta.data.EmtaItem
import java.util.concurrent.Executors


class EmtaControlFragment private constructor() : Fragment() {

    private var emta: EmtaItem? = null

    private val dto by lazy {
        EmtaDataBase.createDb(requireContext()).emtaDto()
    }

    private val executor = Executors.newSingleThreadExecutor()

    private val id by lazy { requireArguments()[ARG_EMTA_ID] as Long }

    private val edTitle: EditText by lazy { requireView().findViewById(R.id.edTitle) }
    private val edDate: EditText by lazy { requireView().findViewById(R.id.edDate) }
    private val btnAdd: Button by lazy { requireView().findViewById(R.id.btnAdd) }
    private val btnUpdate: Button by lazy { requireView().findViewById(R.id.btnUpdate) }
    private val btnDelete: Button by lazy { requireView().findViewById(R.id.btnDelete) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emta_control, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // show add if id is -1 otherwise it is non-add operation
        btnAdd.isVisible = (id == -1L)
        btnUpdate.isVisible = btnAdd.isVisible.not()
        btnDelete.isVisible = btnAdd.isVisible.not()

        executor.execute {
            emta = dto.getEmtaById(id)
            fillUI()
        }

        btnAdd.setOnClickListener {
            val title = edTitle.text.toString()

            if (title.isBlank()) {
                edTitle.error = "Please add title"
                return@setOnClickListener
            }

            val date = edDate.text.toString()
            if (date.isBlank()) {
                edDate.error = "Please add Date"
                return@setOnClickListener
            }

            val dateObject = DateOperation.strToDate(date)?.time ?: 0

            if (dateObject == 0L) {
                edDate.error = "Please add a valid date"
                return@setOnClickListener
            }

            executor.execute {
                dto.insert(EmtaItem(title, dateObject))
                goBack()
            }
        }

        btnUpdate.setOnClickListener {

            val title = edTitle.text.toString()
            if (title.isBlank()) {
                edTitle.error = "Please add title"
                return@setOnClickListener
            }

            val date = edDate.text.toString()
            if (date.isBlank()) {
                edDate.error = "Please add Date"
                return@setOnClickListener
            }

            val dateObject = DateOperation.strToDate(date)?.time ?: 0

            if (dateObject == 0L) {
                edDate.error = "Please add a valid date"
                return@setOnClickListener
            }


            emta?.let {
                it.title = title
                it.dateMillis = dateObject
                executor.execute {
                    dto.update(it)
                    goBack()
                }
            }
        }

        btnDelete.setOnClickListener {
            emta?.let {
                executor.execute {
                    dto.deleteEmta(it)
                    goBack()
                }
            }
        }
    }

    private fun goBack() {
        requireActivity().runOnUiThread {
            requireActivity().onBackPressed()
        }
    }

    private fun fillUI() {
        requireActivity().runOnUiThread {
            emta?.let {
                edTitle.setText(it.title)
                edDate.setText(DateOperation.dateToString(DateOperation.getDateFromMillis(it.dateMillis)))
            }
        }
    }

    companion object {

        private const val ARG_EMTA_ID = "id"

        fun createInstance(id: Long = -1): EmtaControlFragment {
            val fragment = EmtaControlFragment()
            val bundle = bundleOf(
                ARG_EMTA_ID to id
            )
            fragment.arguments = bundle
            return fragment
        }
    }


}