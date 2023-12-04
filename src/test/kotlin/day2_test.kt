import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day2Tests {

    val TEST_INPUT = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
    )

    @Test
    fun testParseGame() {
        val game = Game.parse(TEST_INPUT[0])
        assertEquals(game.draws.size, 3)
        assertEquals(game.draws, listOf(
            mapOf(Color.blue to 3 ,Color.red to 4),
            mapOf(Color.red to 1,Color.green to 2, Color.blue to 6),
            mapOf(Color.green to 2),
        ))
    }

    @Test
    fun testGameMin() {
        val game = Game.parse(TEST_INPUT[0])
        assertEquals(game.min, mapOf(
            Color.red to 4, Color.green to 2, Color.blue to 6
        ))
    }

    @Test
    fun testPossible() {
        val bag = mapOf(Color.red to 12, Color.green to 13, Color.blue to 14)
        val games = TEST_INPUT.map(Game::parse)
        assertEquals(findPossible(bag, games).map { it.n }, listOf(1, 2, 5))
    }

    @Test
    fun testPower() {
        val games = TEST_INPUT.map(Game::parse)
        assertEquals(games.sumOf(Game::power), 2286)
    }
}
