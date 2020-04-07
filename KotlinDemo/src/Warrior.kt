class Warrior(name: String, str: Int, lif: Int) {
    private val name = name
    private var str = str
        get() = field
        set(value) {
            field = value
        }

    private var lif = lif

    constructor(_name: String) : this(_name, 5, 5) {

    }

    fun takeDamage(damage: Int) {
        lif -= damage
        if (lif <= 0)
            death()
    }

    fun attack() {
        println("$name attacked with $str power")
    }

    private fun death() {
        lif = 0
        println("Character $name is dead")
    }
}