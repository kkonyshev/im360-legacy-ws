package im360.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import im360.util.JsonNodeRowMapper;
import im360.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("unused")
@RestController
public class OptionsController {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @RequestMapping("/options/{restaurantID}/{active}")
    public ObjectNode options(
            @PathVariable("restaurantID") Long restaurantID,
            @PathVariable("active") Integer active
    ) {
        ObjectMapper mapper = new ObjectMapper();

        List<JsonNode> resultList =
                jdbcTemplate.query(
                        "select id, name, active from im_strain_option where active = ? order by name",
                        new Object[]{active},
                        new JsonNodeRowMapper(mapper)
                );

        ResponseBuilder resp = ResponseBuilder.create(mapper);
        if (resultList!=null && !resultList.isEmpty()) {
            resp.success().withArray("options", resultList);
        } else {
            resp.fail();
        }

        return resp.build();
    }
}
