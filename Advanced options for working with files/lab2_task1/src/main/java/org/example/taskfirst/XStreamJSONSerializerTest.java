package org.example.taskfirst;

import org.junit.Test;

import java.io.IOException;

public class XStreamJSONSerializerTest {
    @Test
    public void testSerializeAndDeserialize() throws IOException {
        // create test data
        ListSubwStream station = ListSubwStream.createSubwayStation();

        // serialize test data to json file
        String filename = "station_test.json";
        XStreamJSONSerializer.serialize(station, filename);

        // deserialize test data from json file
        ListSubwStream deserializedStation = XStreamJSONSerializer.deserialize(filename);

    }
}


