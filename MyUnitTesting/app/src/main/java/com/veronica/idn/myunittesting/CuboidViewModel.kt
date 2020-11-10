package com.veronica.idn.myunittesting

class CuboidViewModel(private val cuboidModel: CuboidModel) {
    fun getVolume(): Double = cuboidModel.getVolume()
    fun getCircumference(): Double = cuboidModel.getCircumference()
    fun getSurfaceArea(): Double = cuboidModel.getSurfaceArea()
    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(l, w, h)
    }
}