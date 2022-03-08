package com.github.mnemotechnician.givefuck

import java.io.*
import java.util.concurrent.*
import kotlin.math.*

val csi = "\u001b["

fun columnNumber(): Int {
	ProcessBuilder("tput", "cols", "2")
		.redirectOutput(ProcessBuilder.Redirect.PIPE)
		.redirectError(ProcessBuilder.Redirect.PIPE)
		.start().let {
			it.waitFor(450L, TimeUnit.MILLISECONDS)
			return it.inputStream.reader().readText().trim().let { if (it.isEmpty()) -1 else it.toInt() }
		}
}

object Cursor {
	fun up(amount: Int = 1, stream: PrintStream = System.out) = stream.apply {
		print(csi)
		print(amount)
		print('A')
	};
	
	fun down(amount: Int = 1, stream: PrintStream = System.out) = stream.apply {
		print(csi)
		print(amount)
		print('B')
	};
	
	fun right(amount: Int = 1, stream: PrintStream = System.out) = stream.apply {
		print(csi)
		print(amount)
		print('C')
	};
	
	fun left(amount: Int = 1, stream: PrintStream = System.out) = stream.apply {
		print(csi)
		print(amount)
		print('D')
	};
	
	/** prints the \r symbol */
	fun toLeft(stream: PrintStream = System.out) = stream.apply {
		print('\r')
	};
	
	fun line(amount: Int, stream: PrintStream = System.out) = stream.apply {
		print(csi)
		print(abs(amount))
		print(if (amount > 0) 'E' else 'F')
	};
	
	fun eraseLine(whole: Boolean = true, stream: PrintStream = System.out) = stream.apply {
		print(csi)
		print(if (whole) 2 else 0)
		print('K')
	};
}		
