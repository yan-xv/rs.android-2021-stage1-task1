package subtask3

class StringParser {

    fun getResult(inputString: String): Array<String> {
        val result: MutableList<String> = mutableListOf()

        val brackets = arrayOf('[' to ']', '<' to '>', '(' to ')')

        val bracketsOpen: MutableList<String> = mutableListOf()
        brackets.forEach { bracketsOpen.add(it.first.toString()) }

        fun addToResult (s: String) {
            if ( s !in result) {
                result.add(s)
            }
        }

        fun findSubStr(i: Int, openBracket: Char, closeBracket: Char): Int {
            var iNextOpen = inputString.indexOf(openBracket, i)
            var iClose = inputString.indexOf(closeBracket, i)

            // перед закр. скобкой нет еще одной откр.
            if ( iNextOpen > iClose || iNextOpen == - 1) {
                addToResult(inputString.substring(i, iClose))
                return iClose
            }
            // перед закр. есть еще одна откр.
            else {
                // найдем подстроку со след открыв. и
                // получим индекс последнй использованной закрывающей скобки
                iClose = findSubStr(iNextOpen + 1, openBracket, closeBracket)

                // найдем след закрыв. и получим подстрку между ней и текущей открыв.
                if (iClose > 0) {
                    val iNextClose = inputString.indexOf(closeBracket, iClose + 1)
                    if (iNextClose > 0) {
                        addToResult(inputString.substring(i, iNextClose))
                    }
                }
                return -1
            }
        }
        //
        var i = 0
        while (true)
        {
            i = inputString.indexOfAny(bracketsOpen,i+1)
            if (i < 0)
                break

            val pairBrackets = brackets.find { it.first == inputString[i] }
            if ( pairBrackets != null )
                findSubStr(i + 1, pairBrackets.first, pairBrackets.second)
        }

        return result.toTypedArray()

    }
}