package io.medialog.googleyolo

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * A decoded JSON Web Token from a [[io.medialog.googleyolo.GoogleYoloCredentials]] instance.
  * For definitions on these fields, look up your friendly neighborhood JWT spec.
  *
  * There may be more fields in the JWT, but these are the fields defined in the documentation
  * from Google themselves so for now, these are the fields being exposed in this library.
  */
@js.native
trait GoogleYoloToken extends js.Object {
  val iss: String = js.native
  val sub: String = js.native
  val azp: String = js.native
  val aud: String = js.native
  val iat: String = js.native
  val exp: String = js.native

  val email: js.UndefOr[String] = js.native

  @JSName("email_verified")
  val emailVerified: js.UndefOr[Boolean] = js.native

  val name: js.UndefOr[String] = js.native

  @JSName("picture_url")
  val pictureURL: js.UndefOr[String] = js.native

  @JSName("given_name")
  val givenName: js.UndefOr[String] = js.native

  @JSName("family_name")
  val familyName: js.UndefOr[String] = js.native

}
