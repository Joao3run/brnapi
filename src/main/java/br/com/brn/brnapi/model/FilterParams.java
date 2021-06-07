package br.com.brn.brnapi.model;
/*
 * @author BRUN
 */

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class FilterParams {

	@GraphQLQuery(name = "id", description = "Id to use as filter (long)")
	private Long id;

	@GraphQLQuery(name = "filterByActive", description = "Active filter to be applied (boolean)")
	private Boolean filterByActive;

	@GraphQLQuery(name = "active", description = "Active filter to be applied (boolean)")
	private Boolean active;

}
