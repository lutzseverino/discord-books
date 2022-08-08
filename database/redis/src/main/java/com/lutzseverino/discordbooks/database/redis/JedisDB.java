package com.lutzseverino.discordbooks.database.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.database.BookDB;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDB implements BookDB {
    private final ObjectMapper mapper = new ObjectMapper();
    private final JedisPool pool;

    public JedisDB() {
        this.pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
    }

    public JedisDB(JedisPool pool) {
        this.pool = pool;
    }

    @Override public Book get(String key) {
        try (var jedis = pool.getResource()) {
            var json = jedis.get(key);
            return json == null ? null : mapper.readValue(json, Book.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override public void set(String key, Book value) {
        try (var jedis = pool.getResource()) {
            jedis.set(key, mapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
