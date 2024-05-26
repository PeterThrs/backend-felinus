package com.felinus.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import org.hibernate.engine.jdbc.BlobProxy;

public class BlobDeserializer extends JsonDeserializer<Blob> {

    @Override
    public Blob deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        byte[] data = p.getBinaryValue();
        return BlobProxy.generateProxy(data);
    }
}