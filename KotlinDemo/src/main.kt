fun main(args: Array<String>) {
    println("Hello world")
    printName()
    printName("João")

    var name = null
    printName(name)

    var myArray = ArrayList<String>()
    myArray.add("John")
    myArray.add("Mike")
    myArray.add("Jake")
    myArray.add("Earl")

    myArray.remove("Mike")

    for (person in myArray)
        println("Person is: $person")

    var classes = mutableMapOf<String, Int>("Fighter" to 1, "Mage" to 2)
    classes.put("Healer", 3)

    for (rpgClass in classes)
        println("This class number is ${rpgClass.value} and the class is ${rpgClass.key}")

    fun applyOperationOnInt(myInt: Int, me: (Int, String) -> Unit) = me(myInt, "Hello")

    applyOperationOnInt(2) { myInt: Int, myString: String ->
        println("Calling from lambda values: $myInt and $myString")
    }

    var warrior = Warrior("John", 5, 10).also {
        it.attack()
        it.takeDamage(15)
    }

    val craig: FighterType = FighterType.Warrior(FighterData("Craig", 5, 5))
    val samantha: FighterType = FighterType.NPC("Samantha")
    val ba: FighterType = FighterType.Basic

    println("-----------------------------------------------------")
    doActionOnFighter(craig)
    doActionOnFighter(samantha)
    doActionOnFighter(ba)

}

fun doActionOnFighter(fighter: FighterType): Unit = when(fighter){
    is FighterType.Basic -> {
        println("Basic Warrior")}
    is FighterType.Warrior -> {
        fighter.run {
            attack()
            takeHit(15)
        }
    }
    is FighterType.NPC -> {
        fighter.run { speak() }
    }
}

fun printName() {
    println("Thiago Gorgulho")
}

fun printName(name: String?) {
    println(name)
}

fun createVector(x: Float, y: Float): Pair<Float, Float> = Pair(x, y)

var myFunc: (Float, Float) -> Pair<Float, Float> = ::createVector
var myPair = myFunc(2.5F, 3.5F)