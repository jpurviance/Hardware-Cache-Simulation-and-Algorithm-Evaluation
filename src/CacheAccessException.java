/**
 * Created by John Purviance on 12/25/15.
 *
 * Cache exception parent class. All other cache exceptions inherit from this class.
 * This is done so that at run time multiple exceptions can be caught easily with clean code.
 */
public class CacheAccessException extends Exception {
    public CacheAccessException() {
    }

    public CacheAccessException(String message) {
        super(message);
    }

    public CacheAccessException(Throwable cause) {
        super(cause);
    }

    public CacheAccessException(String mesaage, Throwable cause) {
        super(mesaage, cause);
    }

    public CacheAccessException(String message, Throwable cause,
                                boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

/**
 * Thrown when there is a cache read exception including invalid memory address, data is not in the cache or the cache is full.
 */
class CacheReadMissException extends CacheAccessException {
    public CacheReadMissException() {
    }

    public CacheReadMissException(String message) {
        super(message);
    }

    public CacheReadMissException(Throwable cause) {
        super(cause);
    }

    public CacheReadMissException(String mesaage, Throwable cause) {
        super(mesaage, cause);
    }

    public CacheReadMissException(String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

/**
 * Thrown when data is not in the cache but there is still space available in the cache.
 */
class CacheColdMissException extends CacheAccessException {
    public CacheColdMissException() {
    }

    public CacheColdMissException(String message) {
        super(message);
    }

    public CacheColdMissException(Throwable cause) {
        super(cause);
    }

    public CacheColdMissException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheColdMissException(String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

/**
 * Thrown when the memory address is not a valid address.
 */
class InvalidCacheAddressException extends CacheAccessException {
    public InvalidCacheAddressException() {
    }

    public InvalidCacheAddressException(String message) {
        super(message);
    }

    public InvalidCacheAddressException(Throwable cause) {
        super(cause);
    }

    public InvalidCacheAddressException(String mesaage, Throwable cause) {
        super(mesaage, cause);
    }

    public InvalidCacheAddressException(String message, Throwable cause,
                                        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

