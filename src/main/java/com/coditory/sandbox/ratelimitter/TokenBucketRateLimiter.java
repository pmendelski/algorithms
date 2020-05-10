package com.coditory.sandbox.ratelimitter;

import java.time.Clock;

import static java.util.Objects.requireNonNull;

public class TokenBucketRateLimiter {
    private final Clock clock;
    private final int capacity;
    private final int refillRatePerSecond;
    private int tokens;
    private long lastRefill;

    public TokenBucketRateLimiter(Clock clock, int capacity, int refillRatePerSecond) {
        if (capacity < 1 && refillRatePerSecond < 1) {
            throw new IllegalArgumentException("Expected maxBucketSize > 0 && refillRate > 0");
        }
        this.clock = requireNonNull(clock);
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.lastRefill = clock.instant().toEpochMilli();
        this.tokens = capacity;
    }

    public boolean allowRequest(int cost) {
        refill();
        if (tokens > cost) {
            tokens = tokens - cost;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = clock.instant().toEpochMilli();
        double diff = (now - lastRefill) / 1000.0;
        tokens += diff * refillRatePerSecond;
        tokens = Math.min(tokens, capacity);
        lastRefill = now;
    }
}
