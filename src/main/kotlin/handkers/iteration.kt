package handkers

import kotlinx.serialization.json.Json
import printers.UPrinter

val send = SenderProgram()
val answer = ReceiverProgram()
val receiver = answer.startReceiver()
val uPrinter = UPrinter()
var checkFirstIteration = true
var checkSecondIteration = true
class Iteration {
    private var firstData: String = ""
    private var secondData: String = ""
    fun setFirstData(value:String) {
        this.firstData = value
    }
    fun getFirstData(): String {
        return this.firstData
    }

    fun getSecondData(): String {
        return this.secondData
    }

    fun setSecondData(value:String) {
        this.secondData = value
    }



    fun oneIteration(mess: String) {
        send.mailing(mess)
        while (checkFirstIteration) {
            setFirstData(answer.main(receiver))
            if (!firstData.isBlank()) {
                    break
                }
            }
        }

    fun othersIteration(mess: String) {
        send.mailing(mess)
        while (checkSecondIteration) {
            setSecondData(answer.main(receiver))
            if (!secondData.isBlank()) {
                uPrinter.print { getSecondData() }
                break
            }
        }
    }
}


