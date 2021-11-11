import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.language.Document;
import graphql.parser.Parser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class EntryPoint {
    private static final Log log = LogFactory.getLog(EntryPoint.class);

    public static void main(String[] args) {
        try {
            URL url = Resources.getResource("schema.graphql");
            String sdl = Resources.toString(url, Charsets.UTF_8);
            Parser parser = new Parser();
            Document document = parser.parseDocument(sdl);

            // Get Graphql Query Fields Map
            Map<String,String> graphqlQueryFieldsMap = GraphqlSchemaUtils.getGraphqlQueryFieldsMap(document);
            for (String fieldName: graphqlQueryFieldsMap.keySet()) {
                log.info("Field name : " + fieldName + " | Field Return Type : " + graphqlQueryFieldsMap.get(fieldName));
            }

            // Get Graphql Query Field Arguments Map
            Map<String,String> graphqlQueryFieldsMap1 = GraphqlSchemaUtils.getGraphqlQueryFieldsMap(document);
            for (String fieldName: graphqlQueryFieldsMap1.keySet()) {
                log.info("Field name : " + fieldName);
                Map<String,String> graphqlQueryFieldArgumentsMap =
                        GraphqlSchemaUtils.getGraphqlQueryFieldArgumentsMap(document, fieldName);
                for (String argumentName: graphqlQueryFieldArgumentsMap.keySet()) {
                    log.info("Argument name : " + argumentName + " | Argument type : " +
                            graphqlQueryFieldArgumentsMap.get(argumentName));
                }
            }

            // Get Graphql Object Types Names
            List<String> graphqlObjectTypesNames = GraphqlSchemaUtils.getGraphqlObjectTypesNames(document);
            for (String graphqlObjectTypeName: graphqlObjectTypesNames) {
                log.info("Object Type name : " + graphqlObjectTypeName);
                // Get Graphql Object Type Fields Map
                Map<String,String> graphqlObjectTypeFieldsMap =
                        GraphqlSchemaUtils.getGraphqlObjectTypeFieldsMap(document, graphqlObjectTypeName);
                for (String fieldName: graphqlObjectTypeFieldsMap.keySet()) {
                    log.info("Field name : " + fieldName + " | Field Return Type : " +
                            graphqlObjectTypeFieldsMap.get(fieldName));
                }
            }

            // Get Graphql Enum Names
            List<String> graphqlEnumNames = GraphqlSchemaUtils.getGraphqlEnumNames(document);
            for (String graphqlEnumName: graphqlEnumNames) {
                log.info("Enum name : " + graphqlEnumName);
                // Get Graphql Enum Values
                List<String> graphqlEnumValues = GraphqlSchemaUtils.getGraphqlEnumValues(document, graphqlEnumName);
                for (String graphqlEnumValue: graphqlEnumValues) {
                    log.info("Enum value : " + graphqlEnumValue);
                }
            }

        } catch (IOException e) {
            log.error("IOException Occurred: ", e);
        }
    }
}
