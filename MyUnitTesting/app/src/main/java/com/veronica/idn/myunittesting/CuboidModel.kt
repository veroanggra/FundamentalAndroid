package com.veronica.idn.myunittesting

class CuboidModel {
    private var length: Double = 0.0
    private var width: Double = 0.0
    private var height: Double = 0.0

    fun getVolume(): Double = length * width * height

    fun getSurfaceArea(): Double = 2 * ((width * length) + (width * height) + (height * length))

    fun getCircumference(): Double = 4 * (width + length + height)

    fun save(width: Double, length: Double, height: Double) {
        this.width = width
        this.length = length
        this.height = height
    }
}