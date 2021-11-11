import graphql.language.Document;
import graphql.language.EnumTypeDefinition;
import graphql.language.EnumValueDefinition;
import graphql.language.FieldDefinition;
import graphql.language.InputValueDefinition;
import graphql.language.ObjectTypeDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphqlSchemaUtils {
    public static Map<String,String> getGraphqlQueryFieldsMap(Document document) {
        Map<String,String> graphqlQueryFieldsMap = new HashMap<>();
        List<ObjectTypeDefinition> objectTypeDefinitions = document.getDefinitionsOfType(ObjectTypeDefinition.class);
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions) {
            if (objectTypeDefinition.getName().equals(Constants.QueryObjectTypeName)) {
                List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
                for (FieldDefinition fieldDefinition: fieldDefinitions) {
                    graphqlQueryFieldsMap.put(fieldDefinition.getName(), fieldDefinition.getType().toString());
                }
                break;
            }
        }
        return graphqlQueryFieldsMap;
    }

    // Generic
    public static Map<String,String> getGraphqlObjectTypeFieldsMap(Document document, String objectTypeName) {
        Map<String,String> graphqlQueryFieldsMap = new HashMap<>();
        List<ObjectTypeDefinition> objectTypeDefinitions = document.getDefinitionsOfType(ObjectTypeDefinition.class);
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions) {
            if (objectTypeDefinition.getName().equals(objectTypeName)) {
                List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
                for (FieldDefinition fieldDefinition: fieldDefinitions) {
                    graphqlQueryFieldsMap.put(fieldDefinition.getName(), fieldDefinition.getType().toString());
                }
                break;
            }
        }
        return graphqlQueryFieldsMap;
    }

    public static Map<String,String> getGraphqlQueryFieldArgumentsMap(Document document, String fieldName) {
        Map<String,String> graphqlQueryFieldArgumentsMap = new HashMap<>();
        List<ObjectTypeDefinition> objectTypeDefinitions = document.getDefinitionsOfType(ObjectTypeDefinition.class);
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions) {
            if (objectTypeDefinition.getName().equals(Constants.QueryObjectTypeName)) {
                List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
                for (FieldDefinition fieldDefinition: fieldDefinitions) {
                    if (fieldDefinition.getName().equals(fieldName)) {
                        List<InputValueDefinition> inputValueDefinitions = fieldDefinition.getInputValueDefinitions();
                        for (InputValueDefinition inputValueDefinition: inputValueDefinitions) {
                            graphqlQueryFieldArgumentsMap.put(inputValueDefinition.getName(),
                                    inputValueDefinition.getType().toString());
                        }
                        break;
                    }
                }
                break;
            }
        }
        return graphqlQueryFieldArgumentsMap;
    }

    // Generic
    public static Map<String,String> getGraphqlObjectTypeFieldArgumentsMap(Document document, String objectTypeName,
                                                                           String fieldName) {
        Map<String,String> graphqlQueryFieldArgumentsMap = new HashMap<>();
        List<ObjectTypeDefinition> objectTypeDefinitions = document.getDefinitionsOfType(ObjectTypeDefinition.class);
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions) {
            if (objectTypeDefinition.getName().equals(objectTypeName)) {
                List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
                for (FieldDefinition fieldDefinition: fieldDefinitions) {
                    if (fieldDefinition.getName().equals(fieldName)) {
                        List<InputValueDefinition> inputValueDefinitions = fieldDefinition.getInputValueDefinitions();
                        for (InputValueDefinition inputValueDefinition: inputValueDefinitions) {
                            graphqlQueryFieldArgumentsMap.put(inputValueDefinition.getName(),
                                    inputValueDefinition.getType().toString());
                        }
                        break;
                    }
                }
                break;
            }
        }
        return graphqlQueryFieldArgumentsMap;
    }

    public static List<String> getGraphqlObjectTypesNames(Document document) {
        List<String> graphqlObjectTypesNames = new ArrayList<>();
        List<ObjectTypeDefinition> objectTypeDefinitions = document.getDefinitionsOfType(ObjectTypeDefinition.class);
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions) {
            graphqlObjectTypesNames.add(objectTypeDefinition.getName());
        }
        return graphqlObjectTypesNames;
    }

    public static List<String> getGraphqlEnumNames(Document document) {
        List<String> graphqlEnumNames = new ArrayList<>();
        List<EnumTypeDefinition> enumTypeDefinitions = document.getDefinitionsOfType(EnumTypeDefinition.class);
        for(EnumTypeDefinition enumTypeDefinition: enumTypeDefinitions) {
            graphqlEnumNames.add(enumTypeDefinition.getName());
        }
        return graphqlEnumNames;
    }

    public static List<String> getGraphqlEnumValues(Document document, String enumName) {
        List<String> graphqlEnumValues = new ArrayList<>();
        List<EnumTypeDefinition> enumTypeDefinitions = document.getDefinitionsOfType(EnumTypeDefinition.class);
        for(EnumTypeDefinition enumTypeDefinition: enumTypeDefinitions) {
            if (enumTypeDefinition.getName().equals(enumName)) {
                List<EnumValueDefinition> enumValueDefinitions = enumTypeDefinition.getEnumValueDefinitions();
                for (EnumValueDefinition enumValueDefinition: enumValueDefinitions) {
                    graphqlEnumValues.add(enumValueDefinition.getName());
                }
            }
            break;
        }
        return graphqlEnumValues;
    }

    public static Map<String,Object> getGraphqlEnumMap(Document document) {
        Map<String,Object> graphqlEnumMap = new HashMap<>();
        List<EnumTypeDefinition> enumTypeDefinitions = document.getDefinitionsOfType(EnumTypeDefinition.class);
        for(EnumTypeDefinition enumTypeDefinition: enumTypeDefinitions) {
            List<String> graphqlEnumValues = new ArrayList<>();
            List<EnumValueDefinition> enumValueDefinitions = enumTypeDefinition.getEnumValueDefinitions();
            for (EnumValueDefinition enumValueDefinition: enumValueDefinitions) {
                graphqlEnumValues.add(enumValueDefinition.getName());
            }
            graphqlEnumMap.put(enumTypeDefinition.getName(), graphqlEnumValues);
        }
        return graphqlEnumMap;
    }
}
