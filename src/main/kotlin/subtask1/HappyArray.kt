package subtask1

class HappyArray {

    fun convertToHappy(sadArray: IntArray): IntArray {
        // будем возвращать, то что получилось
        return sadArray.filterIndexed { i, it -> !sadArray.isBad(i, it) }.toIntArray()
        //throw NotImplementedError("Not implemented")
    }

    private fun IntArray.isBad(i: Int, curr: Int): Boolean {
        fun getNextNotBad(_i: Int): Int {
            val iNext = _i + 1
            return if ( isBad(iNext ) )
                getNextNotBad(iNext)
            else
                this.getOrElse(iNext) { curr }
        }

        val prev = this.getOrElse(i-1) { curr }
        val next = getNextNotBad(i)
        return prev + next < curr
    }

    private fun IntArray.isBad(i: Int): Boolean {
        return if ( i in this.indices )
            isBad(i, this[i])
        else
            false
    }
}