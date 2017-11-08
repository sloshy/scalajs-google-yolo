package io.medialog.googleyolo

import scala.scalajs.js

/**
  * A set of response credentials from a Google YOLO action.
  * If the idToken field exists, then the user was authenticated successfully.
  */
@js.native
trait GoogleYoloCredentials extends js.Object {

  /** The domain the auth request initiated from (your application). */
  val authDomain: String = js.native

  /** The URI of the auth method used. */
  val authMethod: String = js.native

  /** The authenticated user's display name. */
  val displayName: String = js.native

  /** The unique identifier for this user, typically an email address. */
  val id: js.UndefOr[String] = js.native

  /**
    * An encrypted [[io.medialog.googleyolo.GoogleYoloToken]], a JSON Web Token with certain fields.
    * If this token is present, then all other possibly undefined fields should exist.
    */
  val idToken: js.UndefOr[String] = js.native

  /** The URL for the current user's profile picture. */
  val profilePicture: js.UndefOr[String] = js.native

}
