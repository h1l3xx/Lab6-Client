package printers

class UPrinter : Printer{
    fun printValues(key: String, value : String) {
        print {
            "$key --- $value"
        }
    }
}