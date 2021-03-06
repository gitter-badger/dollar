/*
 * Copyright (c) 2014 Neil Ellis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.neilellis.dollar.redis;

import me.neilellis.dollar.uri.URIHandler;
import me.neilellis.dollar.uri.URIHandlerFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URISyntaxException;

/**
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public class RedisURIHandlerFactory implements URIHandlerFactory {


    private static final JedisPoolConfig poolConfig = new JedisPoolConfig();

    static {
        poolConfig.setMaxTotal(128);

    }


    @Override
    public boolean handlesScheme(String scheme) {
        return scheme.equals("redis");
    }

    @Override
    public URIHandler forURI(String scheme, String uri) {
        try {
            return new RedisURIHandler(uri, poolConfig);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public URIHandlerFactory copy() {
        return this;
    }
}

