/**
 * Created by John Purviance on 12/30/15.
 * Generic execption for when user files are invlaid.
 */

public class InvalidConfigurationException extends Exception {
    public InvalidConfigurationException() {
    }

    public InvalidConfigurationException(String message) {
        super(message);
    }

    public InvalidConfigurationException(Throwable cause) {
        super(cause);
    }

    public InvalidConfigurationException(String mesaage, Throwable cause) {
        super(mesaage, cause);
    }

    public InvalidConfigurationException(String message, Throwable cause,
                                         boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

/**
 * Exception for when a simulation cannot find the specified cache.
 * Is used for if the cache does not exist or if the cache name is spelled wrong.
 */
class CacheNotFoundException extends InvalidConfigurationException {
    public CacheNotFoundException() {
    }


    public CacheNotFoundException(String message) {
        super(message);
    }

    public CacheNotFoundException(Throwable cause) {
        super(cause);
    }

    public CacheNotFoundException(String mesaage, Throwable cause) {
        super(mesaage, cause);
    }

    public CacheNotFoundException(String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

/**
 * Exception for when an invalid simulation configuration is found.
 */
class InvalidSimulationException extends InvalidConfigurationException {
    public InvalidSimulationException() {
    }


    public InvalidSimulationException(String message) {
        super(message);
    }

    public InvalidSimulationException(Throwable cause) {
        super(cause);
    }

    public InvalidSimulationException(String mesaage, Throwable cause) {
        super(mesaage, cause);
    }

    public InvalidSimulationException(String message, Throwable cause,
                                      boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

/**
 * Exception for when an invalid cache configuration is found.
 */
class InvalidCacheException extends InvalidConfigurationException {
    public InvalidCacheException() {
    }


    public InvalidCacheException(String message) {
        super(message);
    }

    public InvalidCacheException(Throwable cause) {
        super(cause);
    }

    public InvalidCacheException(String mesaage, Throwable cause) {
        super(mesaage, cause);
    }

    public InvalidCacheException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


