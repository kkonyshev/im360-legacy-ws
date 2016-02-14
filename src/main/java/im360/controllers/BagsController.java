package im360.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wordnik.swagger.annotations.*;
import im360.util.JsonNodeRowMapper;
import im360.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(basePath = "/bags", value = "Bags", description = "List of bags", produces = "application/json")
@SuppressWarnings("unused")
@RestController
@RequestMapping("/bags")
public class BagsController {

    @Autowired
    protected JdbcTemplate jdbcTemplate;


    @ApiOperation(value = "Get list of bags", notes = "by parameters: restaurant, status and arrt1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request") }
    )
    @RequestMapping(value = "/{restaurantID}/{status}/{ATTR1}", method = RequestMethod.GET)
    public ObjectNode options(
            @ApiParam(value = "restaurant identifier", required = true)
            @PathVariable("restaurantID") Long restaurantID,
            @ApiParam(value = "status type", required = true)
            @PathVariable("status") Integer status,
            @ApiParam(value = "attr1 value", required = true)
            @PathVariable("ATTR1") String attr1
    ) {
        ObjectMapper mapper = new ObjectMapper();

        List<JsonNode> resultList =
                jdbcTemplate.query(
                                "select ud.id as id" +
                                "     , ud.visibleId as visibleId           " +
                                "     , ud.price_per_unit as price_per_unit " +
                                "     , ti.tier_name as tier_name           " +
                                "  from im_strain_units_details ud, im_strain_units ut, posper_category ca, im_strain_type ty, im_strain_tier ti " +
                                "where /*ud.restaurant_id = ? " +
                                "  and */ud.status = ? and ud.ATTR1 = ? and ud.im_strain_units_id = ut.id and ut.posper_category_id = ca.id " +
                                "  and ty.posper_category_id = ca.id and ty.im_strain_tier_id = ti.id   " +
                                "order by ud.visibleId",
                        new Object[]{status, attr1},
                        new JsonNodeRowMapper(mapper)
                );

        ResponseBuilder resp = ResponseBuilder.create(mapper);
        if (resultList!=null && !resultList.isEmpty()) {
            resp.success().withArray("bags", resultList);
        } else {
            resp.fail();
        }

        return resp.build();
    }
}
