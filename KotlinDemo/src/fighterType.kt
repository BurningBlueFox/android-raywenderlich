sealed class FighterType {
    object Basic : FighterType()
    class Warrior(val fighterData: FighterData) : FighterType() {
        fun takeHit(damage: Int) {
            fighterData.hp -= damage
            if (fighterData.hp <= 0)
                die()
        }

        fun attack() {
            println("Character ${fighterData.name} attacks with ${fighterData.str} damage.")
        }

        private fun die() {
            println("Character ${fighterData.name} is dead.")
        }
    }
    class NPC(val name: String):FighterType(){
        fun speak(){
            println("Character $name speaks: Hi!")
        }
    }
}

data class FighterData(val name: String, var hp: Int, var str: Int)