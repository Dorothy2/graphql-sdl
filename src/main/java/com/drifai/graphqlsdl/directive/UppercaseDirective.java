package com.drifai.graphqlsdl.directive;

import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.hibernate.graph.Graph;

import java.util.Objects;

public class UppercaseDirective implements SchemaDirectiveWiring {
    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition fieldDefinition = environment.getElement();
        GraphQLFieldsContainer parentType = environment.getFieldsContainer();
        DataFetcher originalDataFetcher = environment.getCodeRegistry().getDataFetcher(parentType, fieldDefinition);

        DataFetcher dataFetcher = DataFetcherFactories.wrapDataFetcher(originalDataFetcher, (dataFetchingEnvironment, value) -> {
        // where to transform values
                    if (Objects.isNull(value)) {
                        return null;
                    }
                    return ((String) value).toUpperCase();
       });
        FieldCoordinates coordinates = FieldCoordinates.coordinates(parentType, fieldDefinition);
        environment.getCodeRegistry().dataFetcher(coordinates, dataFetcher);
        return fieldDefinition;
    }
}
