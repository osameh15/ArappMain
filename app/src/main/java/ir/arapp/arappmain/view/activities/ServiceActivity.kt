package ir.arapp.arappmain.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.ActivityServiceBinding
import ir.arapp.arappmain.util.adapters.services.VerticalServiceAdapter

class ServiceActivity : AppCompatActivity() {
    lateinit var binding :ActivityServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityServiceBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activityServiceRecyclerView.adapter = VerticalServiceAdapter()
        binding.activityServiceRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
}