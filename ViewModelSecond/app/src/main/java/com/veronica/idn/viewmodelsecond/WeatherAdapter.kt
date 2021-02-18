package com.veronica.idn.viewmodelsecond

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veronica.idn.viewmodelsecond.databinding.WeatherItemBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val mData = ArrayList<WeatherItems>()

    fun setData(items: ArrayList<WeatherItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = WeatherItemBinding.bind(itemView)
        fun bind(weatherItems: WeatherItems) {
            with(itemView) {
                binding.textCity.text = weatherItems.name
                binding.tvTemp.text = weatherItems.temperature
                binding.tvDesc.text = weatherItems.description
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.WeatherViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size
}