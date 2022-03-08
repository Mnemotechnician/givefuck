package com.github.mnemotechnician.givefuck

import java.io.*
import java.util.concurrent.TimeUnit.*
import kotlin.math.*

val fuckTime = 5000L

var progress = 0
val progressSymbols = arrayOf('─', '╲', '│', '╱')

fun main(vararg args: String) {
	if (args.size.let { it < 1 || it > 2} || args[0].toIntOrNull() == null || args.getOrNull(1)?.let { it.toIntOrNull() == null } ?: false) {
		println("""
			Usage:
			- givefuck TARGET GIVEN — give [given] out of [target] fucks
			- givefuck TARGET       — give [target] out of [target] fucks
		""".trimIndent())
		return;
	}
	
	val maxFucks = max(args[0].toInt(), 1)
	val givenFucks = (args.getOrNull(1)?.toInt() ?: maxFucks).coerceIn(0, maxFucks)
	
	boxprint("Giving a fuck, please stand by...")
	MILLISECONDS.sleep(750)
	
	var endAt = System.currentTimeMillis() + fuckTime
	
	while(System.currentTimeMillis() < endAt) {
		val fucks = if (givenFucks == 0) 0 else (fuckTime - (endAt - System.currentTimeMillis())) / (fuckTime / (givenFucks + 1))
		
		undoBox()
		boxprint("Giving a fuck ($fucks/$maxFucks)... ${nextProgressSymbol()}")
		MILLISECONDS.sleep(200)
	}
	
	val finalMessage = when {
		givenFucks == 0 -> "No fucks were given! (0/1)"
		givenFucks == maxFucks -> "All $maxFucks fucks were given! ($maxFucks/$maxFucks)"
		else -> "$givenFucks out of $maxFucks fucks were given! ($givenFucks/$maxFucks)"
	}
	
	undoBox()
	boxprint(finalMessage)
	println() //add a trailing newline
}

fun nextProgressSymbol(): Char = progressSymbols[progress++ % progressSymbols.size];

fun undoBox() {
	Cursor.apply {
		eraseLine()
		up()
		eraseLine()
		up()
		eraseLine()
		toLeft()
	}
}

/** 
 * prints a single line in a box. the box is (length + 4) in width and 3 in height.
 * @param addLines should be true if the box's being drawn in the bottom of the terminal
 **/
fun boxprint(text: String, addLines: Boolean = true) {
	if (addLines) {
		print("\n\n")
		Cursor.up(2)
	}
	
	val length = 2 + text.length
	
	print('┏')
	repeat(length) { print('━') }
	print('┓')
	
	Cursor.down()
	Cursor.left(length + 2)
	
	print('┃')
	print(' ')
	print(text)
	print(' ')
	print('┃')
	
	Cursor.down()
	Cursor.left(length + 2)
	
	print('┗')
	repeat(length) { print('━') }
	print('┛')
}
