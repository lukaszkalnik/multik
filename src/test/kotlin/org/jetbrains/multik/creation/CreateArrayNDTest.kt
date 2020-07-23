package org.jetbrains.multik.creation

import org.jetbrains.multik.api.mk
import org.jetbrains.multik.api.ndarray
import org.jetbrains.multik.core.DN
import org.jetbrains.multik.core.Ndarray
import org.jetbrains.multik.core.toList
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CreateArrayNDTest {

    @BeforeTest
    fun loadLibrary() {
        System.load("/Users/pavel.gorgulov/Projects/main_project/multik/src/jni_multik/cmake-build-debug/libjni_multik.dylib")
    }

    private val shape = intArrayOf(2, 5, 3, 2, 1)
    private val dim = 5

    @Test
    fun createByteArrayNDTest() {
        val inputArray = ByteArray(60) { it.toByte() }
        val a = mk.ndarray<DN>(inputArray, *shape)

        assertEquals(dim, a.dim.d)

        assertEquals(inputArray.asList(), a.toList())
    }

    @Test
    fun createShortArrayNDTest() {
        val inputArray = ShortArray(60) { it.toShort() }
        val a = mk.ndarray<DN>(inputArray, *shape)

        assertEquals(dim, a.dim.d)

        assertEquals(inputArray.asList(), a.toList())
    }

    @Test
    fun createIntArrayNDTest() {
        val inputArray = IntArray(60) { it }
        val a = mk.ndarray<DN>(inputArray, *shape)

        assertEquals(dim, a.dim.d)

        assertEquals(inputArray.asList(), a.toList())
    }

    @Test
    fun createLongArrayNDTest() {
        val inputArray = LongArray(60) { it.toLong() }
        val a = mk.ndarray<DN>(inputArray, *shape)

        assertEquals(dim, a.dim.d)

        assertEquals(inputArray.asList(), a.toList())
    }

    @Test
    fun createFloatArrayNDTest() {
        val inputArray = FloatArray(60) { it.toFloat() }
        val a = mk.ndarray<DN>(inputArray, *shape)

        assertEquals(dim, a.dim.d)

        assertEquals(inputArray.asList(), a.toList())
    }

    @Test
    fun createDoubleArrayNDTest() {
        val inputArray = DoubleArray(60) { it.toDouble() }
        val a = mk.ndarray<DN>(inputArray, *shape)

        assertEquals(dim, a.dim.d)

        assertEquals(inputArray.asList(), a.toList())
    }

    @Test
    fun createDslArrayTest() {
        val inputArr = IntArray(60) { it }
        val a = mk.ndarray(2, 5, 3, 2, 1) { it }
        val check = mk.ndarray<DN>(inputArr, *shape)
        assertEquals(check, a)

        val b = mk.ndarray(2, 5, 3, 2, 1) { it * it }
        assertEquals(mk.ndarray(inputArr.map { it * it }, shape), b)
    }

    @Test
    fun createArrayFromCollectionTest() {
        val shapeCol = intArrayOf(2, 3, 3, 2, 1)
        val l = Array(36) { it }.toList()
        val size_ = HashSet(l)
        val a: Ndarray<Int, DN> = mk.ndarray(size_, shapeCol)
        val b: Ndarray<Int, DN> = mk.ndarray(l, shapeCol)
        assertEquals(a, b)
    }
}