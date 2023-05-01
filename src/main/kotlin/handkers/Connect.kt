package handkers

import main
import manager
import java.util.*
import kotlin.concurrent.schedule

class Connect {
    fun tryAgain(){
        Timer().schedule(10000) {
            manager.process()
        }
    }
}