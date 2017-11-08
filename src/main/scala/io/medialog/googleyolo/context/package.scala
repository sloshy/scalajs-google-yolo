package io.medialog.googleyolo

/**
  * Contains representations of YOLO context types and their valid applications.
  */
package object context {

  /**
    * Represents any valid YOLO context
    */
  sealed trait YoloContext

  /**
    * Represents a YOLO context that is valid when displaying a "hint" (account selector) to the user.
    */
  sealed trait YoloHintContext extends YoloContext

  /**
    * Represents a YOLO context that is valid when automatically retrieving a third-party account.
    */
  sealed trait YoloRetrieveContext extends YoloContext

  /**
    * Represents the YOLO "signIn" context
    * This is used when signing the user in using their third-party account.
    */
  case object SignInContext extends YoloHintContext with YoloRetrieveContext

  /**
    * Represents the YOLO "signUp" context.
    * This is used when creating an account for the user using their third-party account.
    */
  case object SignUpContext extends YoloHintContext

  /**
    * Represents the YOLO "continue" context.
    * This is used for applications that do not require sign-in or sign-up.
    */
  case object ContinueContext extends YoloHintContext with YoloRetrieveContext

}
