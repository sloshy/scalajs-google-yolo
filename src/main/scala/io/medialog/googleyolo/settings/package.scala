package io.medialog.googleyolo

import scala.scalajs.js

/**
  * Contains settings used throughout configuring Google YOLO.
  */
package object settings {

  /**
    * Settings used by the [[io.medialog.googleyolo.GoogleYolo]] 'hint' and 'retrieve' methods.
    */
  @js.native
  trait GoogleYoloSettings extends js.Object {
    val supportedAuthMethods: js.Array[String] = js.native
    val supportedIdTokenProviders: js.Array[SupportedIdTokenProvider] =
      js.native
    val context: String = js.native
  }

  /**
    * An instance of [[io.medialog.googleyolo.settings.GoogleYoloSettings]] that is meant only for the 'hint' method
    */
  @js.native
  trait GoogleYoloHintSettings extends GoogleYoloSettings

  /**
    * An instance of [[io.medialog.googleyolo.settings.GoogleYoloSettings]] that is meant only for the 'retrieve' method
    */
  @js.native
  trait GoogleYoloRetrieveSettings extends GoogleYoloSettings

}
