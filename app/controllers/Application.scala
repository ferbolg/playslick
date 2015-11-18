package controllers

import models._
import play.api._
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.Play.current
import play.api.mvc.BodyParsers._
import play.api.libs.json.Json
import play.api.libs.json.Json._

object Application extends Controller{

  //create an instance of the table
  val persons = TableQuery[PersonsTable] //see a way to architect your app in the computers-database-slick sample

  //JSON read/write macro
  implicit val personFormat = Json.format[Person]

  def index = DBAction { implicit rs =>
    Ok(views.html.index(persons.list))
  }

  val personForm = Form(
    mapping(
      "name" -> text(),
      "surname" -> text(),
      "email" -> text()
    )(Person.apply)(Person.unapply)
  )

  def insert = DBAction { implicit rs =>
    val person = personForm.bindFromRequest.get
    persons.insert(person)

    Redirect(routes.Application.index)
  }

  def jsonFindAll = DBAction { implicit rs =>
    Ok(toJson(persons.list))
  }

  def jsonInsert = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Person].map { person =>
        persons.insert(person)
        Ok(toJson(person))
    }.getOrElse(BadRequest("invalid json"))    
  }
  
}
