package models

import play.api.db.slick.Config.driver.simple._

case class Person(name: String,surname: String, email: String)

/* Table mapping
 */
class PersonsTable(tag: Tag) extends Table[Person](tag, "Person") {

  def name = column[String]("name")
  def surname = column[String]("surname")
  def email = column[String]("color", O.NotNull)
  def pk = primaryKey("pk_person", (name, surname))

  def * = (name,surname, email) <> (Person.tupled, Person.unapply _)
}
