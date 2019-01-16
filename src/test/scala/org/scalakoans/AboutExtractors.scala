package org.scalakoans

import org.scalakoans.support.BlankValues.__
import org.scalakoans.support.KoanSuite

class AboutExtractors extends KoanSuite {

  koan("An extractor is the opposite of a constructor") {
    class Email(val value: String)
    object Email {
      def unapply(email: Email): Option[String] = Option(email.value)
    }

    val mailstring = "foo@bar.com"
    val email = new Email(mailstring)

    val Email(extractedString) = email
    // What the compiler actually does:
    // val extractedString = Email.unapply(email).get

    (extractedString == mailstring) should be(__)
  }

  koan("Extractors can have multiple return values") {
    class Email(val value: String, val spamRatio: Integer)
    object Email {
      def unapply(email: Email): Option[(String, Integer)] = Option((email.value, email.spamRatio))
    }

    val email = new Email("foo@bar.com", 5)
    val Email(extractedString, extractedRatio) = email

    extractedRatio should be(__)
    extractedString should be(__)
  }

  koan("An extractor is defined by default for case classes") {
    case class Email(value: String)

    val mailstring = "foo@bar.com"
    val email = Email(mailstring)
    val Email(extractedString) = email

    extractedString should be(__)
  }

  koan("Pattern matching is similar to switch cases") {
    val string = "B"

    val actual = "B" match {
      case "A" => "stringA"
      case "B" => "stringB"
      case "C" => "stringC"
      // The notation `_` define something that do not need naming
      // The case _ handles every other cases and avoids MatchErrors
      case _ => "DEFAULT"
    }

    actual should be(__)

    val nextActual = "E" match {
      case "A" => "stringA"
      case "B" => "stringB"
      case "C" => "stringC"
      case _   => "DEFAULT"
    }

    nextActual should be(__)
  }

  koan("Order matters inside a pattern matching") {
    val actual = "A" match {
      case _   => "DEFAULT"
      case "A" => "found A"
    }

    actual should be(__)
  }

  koan("You can use pattern matching with case classes to capture inner values") {
    case class A(a: String, b: String)

    val aValue: A = A(a = "string", b = "B")

    val actual = aValue match {
      case A(aa, bb) => aa + bb
      case _       => "DEFAULT"
    }

    actual should be(__)
  }

  koan("You do not have to capture all values") {
    case class A(a: String, b: String)
    val aValue: A = A(a = "string", b = "B")

    val actual = aValue match {
      case A(aa, _) => aa
      case _       => "DEFAULT"
    }

    actual should be(__)
  }


  koan("Lists have several pattern matching") {
    val s = Seq("a", "b", "c")
    val actual = s match {
      case Seq("a", "b", "c") => "ok"
      case _                  => "DEFAULT"
    }

    actual should be(__)

    val consActual = s match {
      case "a" :: Nil               => "ko"
      case "a" :: "b" :: "c" :: Nil => "ok"
      case _                        => "DEFAULT"
    }

    consActual should be(__)

    val headtailActual = s match {
      case head :: tail => tail
      case _            => "DEFAULT"
    }

    headtailActual should be(__)
  }

}
