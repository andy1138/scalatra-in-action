package com.constructiveproof.hackertracker

import org.scalatra._
import org.scalatra.util.conversion.TypeConverter

class HackersController extends HackerTrackerStack {

  case class Name(lastName: String, firstName: String)

  def toName(str: String) = str.split(',').map(_.trim) match {
    case Array(lastName, firstName) => Name(lastName, firstName)
  }

  implicit val stringToName: TypeConverter[String, Name] = safe { str =>
    toName(str)
  }

  before() {
    contentType="text/html"
    DataBase.connect
  }

  after() {
    DataBase.disconnect
  }

  post("/hackers") {
    val name = params.getAs[Name]("name").getOrElse(
      halt(BadRequest("Please provide a name")))

    val motto = params("motto")
    val birthYear = params.getAs[Int]("birth-year").getOrElse(
      halt(BadRequest("Please provide a year of birth.")))

    // create a new hacker and redirect to /hackers/:slug
  }

  get("/hackers/:slug") {
    val slug = params("slug")
    // retrieve and display info about this hacker
  }

  get("/results") {
    val searchQuery = params("search_query")
    // search for and display matching hackers
  }

  get("/hackers/tagged") {
    val tags = multiParams("tag")
    // retrieve and display hackers matching all the given tags
  }
}

object DataBase {
  def connect = {
    println("Connecting to database.")
  }
  def disconnect = {
    println("Disconnecting from database.")
  }
  def insert(message: String) {
    println("Inserting '" + message + "' into the database")
  }
}