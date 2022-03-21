package de.caritas.cob.messageservice.api.service;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/** Service for logging. */
public class LogService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

  private static final String MESSAGESERVICE_API = "MessageService API: {}";
  private static final String RC_SERVICE_ERROR = "Rocket.Chat service error: ";
  private static final String RC_ENCRYPTION_SERVICE_ERROR = "Encryption service error: ";
  private static final String RC_ENCRYPTION_BAD_KEY_SERVICE_ERROR =
      "Encryption service error - possible bad key error: ";
  private static final String USERSERVICE_HELPER_ERROR = "UserServiceHelper error: ";
  private static final String RC_BAD_REQUEST_ERROR = "Rocket.Chat Bad Request service error: ";
  private static final String INTERNAL_SERVER_ERROR_TEXT = "Internal Server Error: ";
  private static final String BAD_REQUEST_TEXT = "Bad Request: ";
  private static final String STATISTICS_EVENT_PROCESSING_ERROR = "StatisticsEventProcessing error: ";
  private static final String STATISTICS_EVENT_PROCESSING_WARNING = "StatisticsEventProcessing warning: ";

  private LogService() {}

  /**
   * Logs a Rocket.Chat service error.
   *
   * @param exception the exception to be logged
   */
  public static void logRocketChatServiceError(Exception exception) {
    LOGGER.error(RC_SERVICE_ERROR + "{}", getStackTrace(exception));
  }

  /**
   * Logs a Rocket.Chat service error.
   *
   * @param message the message to be logged
   */
  public static void logRocketChatServiceError(String message) {
    LOGGER.error(RC_SERVICE_ERROR + "{}", message);
  }

  /**
   * Logs a Rocket.Chat service error.
   *
   * @param message the message to be logged
   * @param exception the exception to be logged
   */
  public static void logRocketChatServiceError(String message, Exception exception) {
    LOGGER.error(RC_SERVICE_ERROR + "{}", message);
    LOGGER.error(RC_SERVICE_ERROR + "{}", getStackTrace(exception));
  }

  /**
   * Logs a Encryption service error.
   *
   * @param exception the exception to be logged
   */
  public static void logEncryptionServiceError(Exception exception) {
    LOGGER.error(RC_ENCRYPTION_SERVICE_ERROR + "{}", getStackTrace(exception));
  }

  /**
   * Logs a excryption error exception.
   *
   * @param exception the exception to be logged
   */
  public static void logEncryptionPossibleBadKeyError(Exception exception) {
    LOGGER.error(RC_ENCRYPTION_BAD_KEY_SERVICE_ERROR + "{}", getStackTrace(exception));
  }

  /**
   * Logs a Rocket.Chat Bad Request error.
   *
   * @param message the message to be logged
   */
  public static void logRocketChatBadRequestError(String message) {
    LOGGER.error(RC_BAD_REQUEST_ERROR + "{}", message);
  }

  /**
   * Logs a UserServiceHelper error.
   *
   * @param exception the exception to be logged
   */
  public static void logUserServiceHelperError(Exception exception) {
    LOGGER.error(USERSERVICE_HELPER_ERROR + "{}", getStackTrace(exception));
  }

  /**
   * Logs a Info message.
   *
   * @param msg The message
   */
  public static void logInfo(String msg) {
    LOGGER.info(msg);
  }

  /**
   * Internal Server Error/Exception.
   *
   * @param message the message to be logged
   * @param exception the exception to be logged
   */
  public static void logInternalServerError(String message, Exception exception) {
    LOGGER.error("{}{}", INTERNAL_SERVER_ERROR_TEXT, message);
    LOGGER.error("{}", getStackTrace(exception));
  }

  /**
   * Internal Server Error/Exception.
   *
   * @param exception the exception to be logged
   */
  public static void logInternalServerError(Exception exception) {
    LOGGER.error("{}{}", INTERNAL_SERVER_ERROR_TEXT, getStackTrace(exception));
  }

  /**
   * Bad Request.
   *
   * @param message the message to be logged
   */
  public static void logBadRequest(String message) {
    LOGGER.warn(BAD_REQUEST_TEXT + "{}", message);
  }

  /**
   * Logs a warning.
   *
   * @param status the http status to be logged
   * @param ex the exception to be logged
   */
  public static void logWarning(final HttpStatus status, final Exception ex) {
    LOGGER.warn(MESSAGESERVICE_API + ": {}", status.getReasonPhrase(), getStackTrace(ex));
  }

  /**
   * Logs a warning.
   *
   * @param ex the exception to be logged
   */
  public static void logWarning(final Exception ex) {
    LOGGER.warn(MESSAGESERVICE_API, getStackTrace(ex));
  }

  /**
   * Logs a warning.
   *
   * @param message the message to be logged
   */
  public static void logWarning(final String message) {
    LOGGER.warn(MESSAGESERVICE_API, message);
  }

  /**
   * Logs a debug message.
   *
   * @param message the message to be logged
   */
  public static void logDebug(final String message) {
    LOGGER.debug(MESSAGESERVICE_API, message);
  }

  /**
   * Error while processing statistics event.
   *
   * @param exception Exception
   */
  public static void logStatisticsEventError(Exception exception) {
    LOGGER.error("{}{}", STATISTICS_EVENT_PROCESSING_ERROR, getStackTrace(exception));
  }

  /**
   * Warning while processing statistics event.
   *
   * @param message error message
   */
  public static void logStatisticsEventWarning(String message) {
    LOGGER.warn("{}{}", STATISTICS_EVENT_PROCESSING_WARNING, message);
  }
}
