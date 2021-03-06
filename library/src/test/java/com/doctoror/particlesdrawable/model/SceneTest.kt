/*
 * Copyright (C) 2017 Yaroslav Mytkalyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.doctoror.particlesdrawable.model

import android.graphics.Color
import com.doctoror.particlesdrawable.ASSERT_DELTA
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class SceneTest {

    private val underTest = Scene()

    @Test
    fun addTwoParticles() {
        // When
        underTest.setParticleData(
            0,
            1f,
            2f,
            3f,
            4f,
            5f,
            6f
        )

        underTest.setParticleData(
            1,
            7f,
            8f,
            9f,
            10f,
            11f,
            12f
        )

        // Then
        assertEquals(1f, underTest.getParticleX(0))
        assertEquals(2f, underTest.getParticleY(0))
        assertEquals(3f, underTest.getParticleDirectionCos(0))
        assertEquals(4f, underTest.getParticleDirectionSin(0))
        assertEquals(5f, underTest.radiuses.get(0))
        assertEquals(6f, underTest.getParticleSpeedFactor(0))

        assertEquals(7f, underTest.getParticleX(1))
        assertEquals(8f, underTest.getParticleY(1))
        assertEquals(9f, underTest.getParticleDirectionCos(1))
        assertEquals(10f, underTest.getParticleDirectionSin(1))
        assertEquals(11f, underTest.radiuses.get(1))
        assertEquals(12f, underTest.getParticleSpeedFactor(1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetFrameDelayToNegative() {
        underTest.frameDelay = -1
    }

    @Test
    fun setsFrameDelay() {
        underTest.frameDelay = 1
        assertEquals(1, underTest.frameDelay.toLong())
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetSpeedFactorToNegative() {
        underTest.speedFactor = -0.01f
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetMultiplierNAN() {
        underTest.speedFactor = java.lang.Float.NaN
    }

    @Test
    fun setsSpeedFactor() {
        underTest.speedFactor = 1f
        assertEquals(1f, underTest.speedFactor, ASSERT_DELTA)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetParticleRadiusRangeInvalidFirstArgument() {
        underTest.setParticleRadiusRange(0.49f, 1f)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetParticleRadiusRangeInvalidSecondArgument() {
        underTest.setParticleRadiusRange(2f, 0f)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetParticleRadiusRangeInvalidBothArguments() {
        underTest.setParticleRadiusRange(0.1f, -2f)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetParticleRadiusRangeFirstArgumentNAN() {
        underTest.setParticleRadiusRange(java.lang.Float.NaN, 1f)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetParticleRadiusRangeSecondArgumentNAN() {
        underTest.setParticleRadiusRange(1f, java.lang.Float.NaN)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetParticleRadiusRangeBothArgumentsNAN() {
        underTest.setParticleRadiusRange(java.lang.Float.NaN, java.lang.Float.NaN)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetParticleRadiusRangeMaxLessThanMin() {
        underTest.setParticleRadiusRange(0.7f, 0.6f)
    }

    @Test
    fun setsParticleRadiusRange() {
        underTest.setParticleRadiusRange(0.5f, 0.6f)
        assertEquals(0.5f, underTest.particleRadiusMin, ASSERT_DELTA)
        assertEquals(0.6f, underTest.particleRadiusMax, ASSERT_DELTA)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetLineThicknessInvalidArgument() {
        underTest.lineThickness = 0.99f
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetLineThicknessNAN() {
        underTest.lineThickness = java.lang.Float.NaN
    }

    @Test
    fun setsLineThickness() {
        underTest.lineThickness = 1f
        assertEquals(1f, underTest.lineThickness, ASSERT_DELTA)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetLineLengthInvalidArgument() {
        underTest.lineLength = java.lang.Float.NEGATIVE_INFINITY
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetLineLengthNAN() {
        underTest.lineLength = java.lang.Float.NaN
    }

    @Test
    fun setsLineLength() {
        underTest.lineLength = 0f
        assertEquals(0f, underTest.lineLength, ASSERT_DELTA)
    }

    @Test(expected = IllegalArgumentException::class)
    fun crashesWhenSetDensityInvalidArgument() {
        underTest.density = -1
    }

    @Test
    fun setsDensity() {
        underTest.density = 2
        assertEquals(2, underTest.density)
    }

    @Test
    fun setsLineColor() {
        underTest.lineColor = 2
        assertEquals(2, underTest.lineColor)
    }

    @Test
    fun setsAlpha() {
        val alpha = 128
        underTest.alpha = alpha
        assertEquals(alpha, underTest.alpha)
    }

    @Test
    fun setsWidth() {
        val width = 4
        underTest.width = width
        assertEquals(width, underTest.width)
    }

    @Test
    fun setsHeight() {
        val height = 1024
        underTest.height = height
        assertEquals(height, underTest.height)
    }

    @Test
    fun setsParticleColor() {
        val color = Color.CYAN
        underTest.particleColor = color
        assertEquals(color, underTest.particleColor)
    }

    @Test
    fun setsParticleX() {
        val x = 2056f
        val position = 13
        underTest.setParticleX(position, x)
        assertEquals(x, underTest.getParticleX(position))
    }

    @Test
    fun setsParticleY() {
        val y = 2056f
        val position = 13
        underTest.setParticleY(position, y)
        assertEquals(y, underTest.getParticleY(position))
    }

    @Test
    fun adjustsBuffersOnDensityChangeToLarger() {
        val density = 120
        underTest.density = density

        // Assert available buffer sizes
        assertEquals(density * 2, underTest.coordinates.capacity())
        assertEquals(density, underTest.radiuses.capacity())

        // Asset can query the new limit
        assertEquals(0f, underTest.getParticleX(density - 1))
        assertEquals(0f, underTest.getParticleY(density - 1))
        assertEquals(0f, underTest.getParticleDirectionCos(density - 1))
        assertEquals(0f, underTest.getParticleDirectionSin(density - 1))
        assertEquals(0f, underTest.getParticleSpeedFactor(density - 1))
    }

    @Test
    fun adjustsBuffersOnDensityChangeToSmaller() {
        val density = 2
        underTest.density = density

        // Assert available buffer sizes
        assertEquals(density * 2, underTest.coordinates.capacity())
        assertEquals(density, underTest.radiuses.capacity())

        // Asset can query the new limit
        assertEquals(0f, underTest.getParticleX(density - 1))
        assertEquals(0f, underTest.getParticleY(density - 1))
        assertEquals(0f, underTest.getParticleDirectionCos(density - 1))
        assertEquals(0f, underTest.getParticleDirectionSin(density - 1))
        assertEquals(0f, underTest.getParticleSpeedFactor(density - 1))
    }
}
