package aias_lab9.ontoptop

import scala.io.Source
import chisel3.iotesters.{PeekPokeTester, Driver}
import scala.language.implicitConversions

class toptopTest(dut: toptop) extends PeekPokeTester(dut) {

  implicit def bigint2boolean(b: BigInt): Boolean = if (b > 0) true else false

  val filename = "./src/main/resource/VectorCPU/inst.asm"
  val lines    = Source.fromFile(filename).getLines.toList

  while (!peek(dut.io.Hcf)) {
    var current_pc = peek(dut.io.pc).toInt
    println("Cycle: " + peek(dut.io.cycle_count).toString)
    println("PC: " + peek(dut.io.pc).toString)
    println("Inst: " + lines(current_pc >> 2))
    println("==============================")

    step(1)
  }

  println("Cycle: " + peek(dut.io.cycle_count).toString)
  println("Inst: Hcf")
  println("This is the end of the program!!")
  println("==============================")
  println("")

  step(1)

  println("Value in the RegFile")
  for (i <- 0 until 4) {
    var value_0 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 0)).toString(16))
      .replace(' ', '0')
    var value_1 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 1)).toString(16))
      .replace(' ', '0')
    var value_2 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 2)).toString(16))
      .replace(' ', '0')
    var value_3 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 3)).toString(16))
      .replace(' ', '0')
    var value_4 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 4)).toString(16))
      .replace(' ', '0')
    var value_5 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 5)).toString(16))
      .replace(' ', '0')
    var value_6 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 6)).toString(16))
      .replace(' ', '0')
    var value_7 = String
      .format("%" + 8 + "s", peek(dut.io.regs(8 * i + 7)).toString(16))
      .replace(' ', '0')

    println(
      s"reg[${"%02d".format(8 * i + 0)}]：0x${value_0} " +
        s"reg[${"%02d".format(8 * i + 1)}]：0x${value_1} " +
        s"reg[${"%02d".format(8 * i + 2)}]：0x${value_2} " +
        s"reg[${"%02d".format(8 * i + 3)}]：0x${value_3} " +
        s"reg[${"%02d".format(8 * i + 4)}]：0x${value_4} " +
        s"reg[${"%02d".format(8 * i + 5)}]：0x${value_5} " +
        s"reg[${"%02d".format(8 * i + 6)}]：0x${value_6} " +
        s"reg[${"%02d".format(8 * i + 7)}]：0x${value_7} "
    )
  }

  println("")
  println("Value in the Vector RegFile")
  for (i <- 0 until 8) {
    var value_0 = String
      .format("%" + 16 + "s", peek(dut.io.vector_regs(4 * i + 0)).toString(16))
      .replace(' ', '0')
    var value_1 = String
      .format("%" + 16 + "s", peek(dut.io.vector_regs(4 * i + 1)).toString(16))
      .replace(' ', '0')
    var value_2 = String
      .format("%" + 16 + "s", peek(dut.io.vector_regs(4 * i + 2)).toString(16))
      .replace(' ', '0')
    var value_3 = String
      .format("%" + 16 + "s", peek(dut.io.vector_regs(4 * i + 3)).toString(16))
      .replace(' ', '0')

    println(
      s"vector_reg[${"%02d".format(4 * i + 0)}]：0x${value_0} " +
        s"vector_reg[${"%02d".format(4 * i + 1)}]：0x${value_1} " +
        s"vector_reg[${"%02d".format(4 * i + 2)}]：0x${value_2} " +
        s"vector_reg[${"%02d".format(4 * i + 3)}]：0x${value_3} "
    )
  }
  
  // 測試
  
    while(peek(dut.io.finish) == 0){
  		step(1)
  }
    // peek讀output poke寫入input
	
	
  println("A_mat :")
  poke(dut.io.dm_addr, 0x0)
  var value_0 = String
		.format("%" + 16 + "s", peek(dut.io.dm_data).toString(16))
		.replace(' ','0')
  println(s"MEM[07] ~ MEM[00] : 0x${value_0} ")
  step(5)
  poke(dut.io.dm_addr, 0x8)
  var value_1 = String
		.format("%" + 16 + "s", peek(dut.io.dm_data).toString(16))
		.replace(' ','0')
  println(s"MEM[15] ~ MEM[08] : 0x${value_1} ")
  step(5)
  
  
  println("B_mat :")
  poke(dut.io.dm_addr, 0x10)
  var value_2 = String
		.format("%" + 16 + "s", peek(dut.io.dm_data).toString(16))
		.replace(' ','0')
  println(s"MEM[23] ~ MEM[16] : 0x${value_2} ")
  step(5)
  
  poke(dut.io.dm_addr, 0x18)
  var value_3 = String
		.format("%" + 16 + "s", peek(dut.io.dm_data).toString(16))
		.replace(' ','0')
  println(s"MEM[31] ~ MEM[24] : 0x${value_3} ")
  step(5)
  
  
  println("C_mat :")
  poke(dut.io.dm_addr, 0x20)
  var value_4 = String
		.format("%" + 16 + "s", peek(dut.io.dm_data).toString(16))
		.replace(' ','0')
  println(s"MEM[39] ~ MEM[32] : 0x${value_4} ")
  step(5)
  
  poke(dut.io.dm_addr, 0x28)
  var value_5 = String
		.format("%" + 16 + "s", peek(dut.io.dm_data).toString(16))
		.replace(' ','0')
  println(s"MEM[47] ~ MEM[40] : 0x${value_5} ")
  step(5)
}

object toptopTest extends App {
  Driver.execute(
    Array("-td", "./generated", "-tbn", "verilator"),
    () => new toptop
  ) { c: toptop =>
    new toptopTest(c)
  }
}
