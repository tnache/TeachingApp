package edu.tnache.teachingapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edu.tnache.teachingapp.R
import edu.tnache.teachingapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    private val gameListener = View.OnClickListener { view ->
        var row: Int = -1
        var column: Int = -1
        when(view.id) {
            R.id.button_0_0 -> {
                row = 0
                column = 0
            }
            R.id.button_0_1 -> {
                row = 0
                column = 1
            }
            R.id.button_0_2 -> {
                row = 0
                column = 2
            }
            R.id.button_1_0 -> {
                row = 1
                column = 0
            }
            R.id.button_1_1 -> {
                row = 1
                column = 1
            }
            R.id.button_1_2 -> {
                row = 1
                column = 2
            }
            R.id.button_2_0 -> {
                row = 2
                column = 0
            }
            R.id.button_2_1 -> {
                row = 2
                column = 1
            }
            R.id.button_2_2 -> {
                row = 2
                column = 2
            }
            R.id.reset -> {
                viewModel.startNewGame()
            }
        }
        if (row != -1 && column != -1) {
            viewModel.makeTurn(row, column)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.clicker = gameListener
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getGame().observe(viewLifecycleOwner, Observer {
            binding.game = it
        })
    }

}
