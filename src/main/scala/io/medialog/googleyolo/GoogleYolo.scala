package io.medialog.googleyolo

import scala.scalajs.js
import settings._

/** The main "googleyolo" library object passed into your defined callback. */
@js.native
trait GoogleYolo extends js.Object {

  /**
    * Closes the credential selector
    *
    * @return A promise that resolves when the credential selector is closed
    */
  def cancelLastOperation(): js.Promise[Unit] = js.native

  /**
    * Presents the user with a user account selector UI
    *
    * @param settings A configured [[io.medialog.googleyolo.settings.GoogleYoloHintSettings]] instance
    * @return A promise that resolves with the user's login credentials if successful
    */
  def hint(
      settings: GoogleYoloHintSettings): js.Promise[GoogleYoloCredentials] =
    js.native

  /**
    * Attempts to automatically login the user with their default account
    *
    * @param settings A configured [[io.medialog.googleyolo.settings.GoogleYoloRetrieveSettings]] instance
    * @return A promise that resolves with the user's login credentials if successful
    */
  def retrieve(
      settings: GoogleYoloRetrieveSettings): js.Promise[GoogleYoloCredentials] =
    js.native

  /**
    * Disables automatic sign-in attempts. Useful if the user has just logged out.
    *
    * @return A promise that resolves once disabling auto-sign-in is successful
    */
  def disableAutoSignIn(): js.Promise[Unit] = js.native

}
