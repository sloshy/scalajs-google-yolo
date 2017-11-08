package io.medialog.googleyolo

import scala.scalajs.js

/** Defines a supported ID token provider. */
@js.native
trait SupportedIdTokenProvider extends js.Object {

  /** The URI of the ID token provider */
  val uri: String = js.native

  /** The Google API Client ID of the token provider */
  val clientId: String = js.native

}
