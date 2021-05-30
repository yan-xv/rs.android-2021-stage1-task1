package subtask2

class BillCounter {
    private val fairlySplit = "bon appetit"

    fun calculateFairlySplit(bill: IntArray, k: Int, b: Int): String {
        val sumCharged = (bill.sum() - bill.getOrElse(k) {0}) / 2
        return if ( sumCharged  == b )
            fairlySplit
        else
            (b - sumCharged).toString()
    }
}
