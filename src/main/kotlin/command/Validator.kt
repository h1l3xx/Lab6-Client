package command

import manager
import scanner
import uPrinter
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Messages {
    const val MESSAGE = "Некорректное количество передаваемых аргументов. Точнее можно узнать в команде help."
    const val errorValue = "Указано некорректное значение. Введите команду повторно."
    const val errorType = "Ошибка в типе данных"
    const val errorField = "Ошибка. Указанного поля не существует."
}

class Validator {

    fun manege(command: String, arguments: List<String>){
        if (checkArguments(command, arguments)){
            manager.continueManage(command, arguments)
        }
    }

    fun checkCommand(command: String) : Boolean{
        val list = listOf(commandList.keys.toString())
        for (i in list){
            val commands = i.drop(1).dropLast(1).split(", ")
            for (comm in commands){
                if (comm == command){
                    return true
                }
            }
        }
        return false
    }
    fun checkArguments(command: String, arguments : List<String>) : Boolean{
        val info = commandList[command]!!
        val size = arguments.size
        if (size > info[Var.max]!!.toInt() || size < info[Var.min]!!.toInt()){
            uPrinter.print { Messages.MESSAGE }
            return false}
        else if (size < info[Var.max]!!.toInt() && size > info[Var.min]!!.toInt() && info[Var.between]!!.toString() == Var.False) {
            uPrinter.print { Messages.MESSAGE }
            return false
        }else if ((info[Var.max] == info[Var.min]) && info[Var.max]!!.toInt() != size){
            uPrinter.print {Messages.MESSAGE }
            return false
        }
        return true
    }
    fun validateOneArgument(argument : String, type : String) : Any{
        return when (type) {
            Var.str -> argument
            Var.long -> {
                val value = setToLong(argument)
                if(value == Messages.errorValue) {
                    return Messages.errorValue
                }else{
                    return value
                }
            }
            Var.float -> {
                val value = setToFloat(argument)
                if(value == Messages.errorValue) {
                    return Messages.errorValue
                }else{
                    return value
                }
            }
            Var.integer -> {
                val value = setToInt(argument)
                if(value == Messages.errorValue) {
                    return Messages.errorValue
                }else{
                    return value
                }
            }
            Var.double -> {
                val value = setToDouble(argument)
                if(value == Messages.errorValue) {
                    return Messages.errorValue
                }else{
                    return value
                }
            }
            Var.birthday -> {
                val value = setBirthday(argument)
                if(value == Messages.errorValue){
                    return Messages.errorValue
                }else return value
            }
            else ->{
                if (type.contains(Var.choose)) {
                    val choose = type.drop(8)
                    return setChoose(argument, choose)
                }else{
                    return Messages.errorType
                }
            }
        }
    }
    fun setChoose(argument: String, choose : String) : String{
        val listForChoose = choose.split(" ")
        var checker = false
        var returnValue = ""
        for (line in listForChoose){
            if (argument.uppercase() == line) {
                checker = true
                returnValue = argument.uppercase()
            }
        }
        return if (checker){
            returnValue
        } else{
            Messages.errorValue
        }
    }
    fun setBirthday(birthday : String): String{
        return if (checkBirthday(birthday)) {
            return birthday
        } else {
            Messages.errorValue
        }
    }
    fun checkBirthday(birthday : String) : Boolean{
        return try {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val date: LocalDate = LocalDate.parse(birthday, formatter)
            val result: ZonedDateTime = date.atStartOfDay(ZoneId.systemDefault())
            true
        } catch (e: Exception) {
            false
        }
    }


    fun validateMoreArg(arguments: List<String>) : Any {
        val checker = CheckFiled()
        return if(checker.check(arguments[0])){
            val type = checker.getType(arguments[0])
            validateOneArgument(arguments[1], type)
        }else{
            Messages.errorField
        }
    }

    fun setToLong(x : String):Any {
        return if (checkToLong(x)) {
            x.toLong()
        } else {
            Messages.errorValue
        }
    }

    fun checkToLong(x : String): Boolean{
        return try {
            x.toLong()
            true
        } catch (e: Exception) {
            false
        }
    }
    fun setToFloat(y : String): Any {
        return if (checkToFloat(y)) {
            return y.toFloat()
        } else {
            Messages.errorValue
        }
    }

    fun checkToFloat(y : String) : Boolean{
        return try {
            y.toFloat()
            true
        } catch (e: Exception){
            false
        }
    }
    fun setToInt(filed : String): Any{
        return if (checkToInt(filed)) {
            filed.toInt()
        } else {
            Messages.errorValue
        }
    }
    fun checkToInt(field : String): Boolean{
        return try {
            field.toInt()
            field.toInt() >= 0
        }catch (e : Exception){
            false
        }
    }
    fun setToDouble(arg : String) : Any{
        return if (checkToDouble(arg)) {
            return arg.toDouble()
        } else {
            Messages.errorValue
        }
    }

    fun checkToDouble(agl : String) : Boolean{
        return try {
            agl.toDouble()
            true
        } catch (e: Exception) {
            false
        }
    }
    fun allFields(arguments: List<String>) : String{
        var returnValue = ""
        if (arguments.isNotEmpty() && checkToInt(arguments[0])){
            returnValue += arguments[0] + " "
            for (i in 1 until arguments.size){
                if (!CheckFiled().check(arguments[i])){
                    return Messages.errorField
                } else{
                    returnValue += "${arguments[i]} "
                }
            }
        }
        else{
            returnValue = if (arguments.isEmpty()){
                setAllValues()
            }else{
                Messages.errorValue
            }
        }
        return returnValue
    }
    private fun setAllValues() : String{
        val regex = Regex(pattern = "\\((.*?)\\)")
        val messages = commandList[Var.add]!![Var.description]!!.split("; ").drop(1)
        val fields = fieldMap.keys.toString().split(", ")
        var arguments = ""
        for (i in 0..11){
            var field = ""
            val afterRegex = regex.find(messages[i])
            if (afterRegex != null){
                field = afterRegex.value
                for (f in fields){
                    if ("($f)" == field){
                        var returnValue = ""
                        while (returnValue.equals("")){
                            uPrinter.print { messages[i] }
                            val arg = scanner.nextLine()
                             returnValue = validateOneArgument(arg, fieldMap[f].toString()).toString()
                            if (returnValue == Messages.errorValue || returnValue == Messages.errorType || returnValue == Messages.errorField){
                                uPrinter.print { returnValue }
                                returnValue = ""
                            }
                            else{
                                arguments += "$returnValue "
                            }
                        }
                    }
                }
            }
        }
        return arguments
    }
    fun workWithFile(a: List<String>) : List<String> {
        return FileManager().file(a[0]).split(" ,")
    }
}

