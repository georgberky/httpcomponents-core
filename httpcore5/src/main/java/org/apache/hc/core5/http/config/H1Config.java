/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.hc.core5.http.config;

import org.apache.hc.core5.util.Args;

/**
 * HTTP/1.1 protocol parameters.
 * <p>
 * Please note that line length is defined in bytes and not characters.
 * This is only relevant however when using non-standard HTTP charsets
 * for protocol elements such as UTF-8.
 * </p>
 *
 * @since 4.3
 */
public class H1Config {

    public static final H1Config DEFAULT = new Builder().build();

    private final int bufferSize;
    private final int chunkSizeHint;
    private final int waitForContinueTimeoutMillis;
    private final int maxLineLength;
    private final int maxHeaderCount;
    private final int maxEmptyLineCount;

    H1Config(final int bufferSize, final int chunkSizeHint, final int waitForContinueTimeout,
             final int maxLineLength, final int maxHeaderCount, final int maxEmptyLineCount) {
        super();
        this.bufferSize = bufferSize;
        this.chunkSizeHint = chunkSizeHint;
        this.waitForContinueTimeoutMillis = waitForContinueTimeout;
        this.maxLineLength = maxLineLength;
        this.maxHeaderCount = maxHeaderCount;
        this.maxEmptyLineCount = maxEmptyLineCount;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public int getChunkSizeHint() {
        return chunkSizeHint;
    }

    public int getWaitForContinueTimeoutMillis() {
        return waitForContinueTimeoutMillis;
    }

    public int getMaxLineLength() {
        return maxLineLength;
    }

    public int getMaxHeaderCount() {
        return maxHeaderCount;
    }

    public int getMaxEmptyLineCount() {
        return this.maxEmptyLineCount;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[bufferSize=").append(bufferSize)
                .append(", chunkSizeHint=").append(chunkSizeHint)
                .append(", waitForContinueTimeout=").append(waitForContinueTimeoutMillis)
                .append(", maxLineLength=").append(maxLineLength)
                .append(", maxHeaderCount=").append(maxHeaderCount)
                .append(", maxEmptyLineCount=").append(maxEmptyLineCount)
                .append("]");
        return builder.toString();
    }

    public static H1Config.Builder custom() {
        return new Builder();
    }

    public static H1Config.Builder copy(final H1Config config) {
        Args.notNull(config, "Config");
        return new Builder()
                .setBufferSize(config.getBufferSize())
                .setChunkSizeHint(config.getChunkSizeHint())
                .setWaitForContinueTimeoutMillis(config.getWaitForContinueTimeoutMillis())
                .setMaxHeaderCount(config.getMaxHeaderCount())
                .setMaxLineLength(config.getMaxLineLength())
                .setMaxEmptyLineCount(config.maxEmptyLineCount);
    }

    public static class Builder {

        private int bufferSize;
        private int chunkSizeHint;
        private int waitForContinueTimeout;
        private int maxLineLength;
        private int maxHeaderCount;
        private int maxEmptyLineCount;

        Builder() {
            this.bufferSize = -1;
            this.chunkSizeHint = -1;
            this.waitForContinueTimeout = 3000;
            this.maxLineLength = -1;
            this.maxHeaderCount = -1;
            this.maxEmptyLineCount = 10;
        }

        public Builder setBufferSize(final int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public Builder setChunkSizeHint(final int chunkSizeHint) {
            this.chunkSizeHint = chunkSizeHint;
            return this;
        }

        public Builder setWaitForContinueTimeoutMillis(final int waitForContinueTimeout) {
            this.waitForContinueTimeout = waitForContinueTimeout;
            return this;
        }

        public Builder setMaxLineLength(final int maxLineLength) {
            this.maxLineLength = maxLineLength;
            return this;
        }

        public Builder setMaxHeaderCount(final int maxHeaderCount) {
            this.maxHeaderCount = maxHeaderCount;
            return this;
        }

        public Builder setMaxEmptyLineCount(final int maxEmptyLineCount) {
            this.maxEmptyLineCount = maxEmptyLineCount;
            return this;
        }

        public H1Config build() {
            return new H1Config(bufferSize > 0 ? bufferSize : 8192, chunkSizeHint, waitForContinueTimeout, maxLineLength, maxHeaderCount, maxEmptyLineCount);
        }

    }

}
