package org.scalakoans


import org.scalakoans.support.KoanSuite

import scala.annotation.tailrec

class AboutFunctionalProgramming extends KoanSuite {
  koan("not quite lisp") {

    // You have a meeting in a large apartment building, but the directions you got
    // are a little confusing:
    //
    // You start on the ground floor (floor 0) and then follow the instructions one
    // character at a time.
    //
    // An opening parenthesis, `(`, means you should go up one floor, and a closing
    // parenthesis, `)`, means you should go down one floor.
    //
    // The apartment building is very tall, and the basement is very deep;
    // you will never find the top or bottom floors.

    def targetFloor(instructions: String): Int = {
      @tailrec
      def targetFloorAcc(instructions: List[Char], acc: Int): Int = {
        instructions match {
          case Nil => acc
          case head :: tail => {
            val diff = head match {
              case '(' => 1
              case ')' => -1
              case _ => 0
            }
            targetFloorAcc(tail, acc + diff)
          }
        }
      }
      targetFloorAcc(instructions.toList, 0)
    }

    targetFloor("(())") should be(0)
    targetFloor("()()") should be(0)
    targetFloor("(((") should be(3)
    targetFloor("(()(()(") should be(3)
    targetFloor("))(((((") should be(3)
    targetFloor("())") should be(-1)
    targetFloor("))(") should be(-1)
    targetFloor(")))") should be(-3)
    targetFloor(")())())") should be(-3)
  }

  koan("I Was Told There Would Be No Math") {

    // You need to wrap a bunch of boxes, but you are running low on
    // wrapping paper, and so you need to submit an order for more.
    //
    // You have a list of the dimensions (length `l`, width `w`, and height `h`)
    // of each box, and only want to order exactly as much as they need.
    //
    // Calculating the required wrapping paper for each box is easy: find the
    // surface area of the box, which is `2*l*w + 2*w*h + 2*h*l`.
    //
    // You also need a little extra paper for each box: the area of the smallest side.

    def wrappingPaperToOrder(boxes: String): Int = {
      @tailrec
      def wrappingPaperToOrderAcc(boxes: List[String], acc: Int): Int ={
        def boxArea(box: String): Int = {
          if (box.matches("\\d+x\\d+x\\d+")) {
            val list = box.split("x").toList.map(_.toInt)
            2 * list(0) * list(1) + 2 * list(1) * list(2) + 2 * list(2) * list(0) + math.min(math.min(list(0) * list(1), list(1) * list(2)), list(2) * list(0))
          }
          else 0
        }
        boxes match {
          case Nil => acc
          case ::(head, tl) => wrappingPaperToOrderAcc(tl, acc + boxArea(head))
        }
      }
      wrappingPaperToOrderAcc(boxes.split(",").toList, 0)
    }

    wrappingPaperToOrder("2x3x4") should be(58)
    wrappingPaperToOrder("1x1x10") should be(43)
    wrappingPaperToOrder("2x4x9,1x7x7,10x8x3") should be(557)

    // Can you handle badly formatted input?
    wrappingPaperToOrder("2x3x4,7x7y7z,1x1x10") should be(101)

  }
}
