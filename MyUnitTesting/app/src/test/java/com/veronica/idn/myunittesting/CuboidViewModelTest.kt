package com.veronica.idn.myunittesting

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock

class CuboidViewModelTest {

    private lateinit var cuboidModel : CuboidModel
    private lateinit var cuboidViewModel: CuboidViewModel

    private var dummyLength = 12.0
    private var dummyWidth = 7.0
    private var dummyHeigth = 6.0

    private  val dummyVolume = 504.0
    private  val dummyCircumference = 100.0
    private val dummySurfaceArea = 396.0


    @Before
    fun before(){
        cuboidModel = mock(CuboidModel::class.java)
        cuboidViewModel = CuboidViewModel(cuboidModel)
    }

    @Test
    fun getVolume() {
        cuboidModel = CuboidModel()
        cuboidViewModel = CuboidViewModel(cuboidModel)
        cuboidViewModel.save(dummyWidth, dummyLength, dummyHeigth)
        val volume = cuboidViewModel.getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }

    @Test
    fun getCircumference() {
        cuboidModel = CuboidModel()
        cuboidViewModel = CuboidViewModel(cuboidModel)
        cuboidViewModel.save(dummyHeigth, dummyLength, dummyWidth)
        val circumference = cuboidViewModel.getCircumference()
        assertEquals(dummyCircumference, circumference, 0.0001)
    }

    @Test
    fun getSurfaceArea() {
        cuboidModel = CuboidModel()
        cuboidViewModel= CuboidViewModel(cuboidModel)
        cuboidViewModel.save(dummyWidth, dummyLength, dummyHeigth)
        val surfaceArea = cuboidViewModel.getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }
}