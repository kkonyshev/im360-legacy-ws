package im360.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Collection;

public class ResponseBuilder {
    public static final String RESULT_CODE_FIELD = "resultCode";
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_FAIL = "fail";

    protected ObjectMapper mapper;
    protected ObjectNode objectNode;

    public static ResponseBuilder create(ObjectMapper mapper) {
        return new ResponseBuilder(mapper);
    }

    protected ResponseBuilder(ObjectMapper mapper) {
        this.mapper = mapper;
        this.objectNode = mapper.createObjectNode();
    }
    public ResponseBuilder success() {
        this.objectNode.put(RESULT_CODE_FIELD, RESULT_SUCCESS);
        return this;
    }
    public ResponseBuilder fail() {
        this.objectNode.put(RESULT_CODE_FIELD, RESULT_FAIL);
        return this;
    }
    public ResponseBuilder withArray(String fieldName, Collection<JsonNode> collection) {
        ArrayNode arr = new ArrayNode(mapper.getNodeFactory());
        arr.addAll(collection);
        objectNode.set(fieldName, arr);
        return this;
    }
    public ObjectNode build() {
        return this.objectNode;
    }
}
