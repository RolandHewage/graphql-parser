import graphql.language.EnumTypeDefinition;
import graphql.language.FieldDefinition;
import graphql.language.InputObjectTypeDefinition;
import graphql.language.ObjectTypeDefinition;

import java.util.List;
import java.util.Map;

public class EntryPoint1 {
    public static void main(String[] args) {
        GraphqlSchemaUtils1 utils = new GraphqlSchemaUtils1("schema.graphql");

        // Get Definitions
        List<String> definitions = utils.getDefinitions();
        for (String definition: definitions) {
            System.out.println(definition);
        }
        System.out.println("-------------------------------");

        //// Level 1 - Names

        // Get ObjectType Names
        for(String name: utils.getObjectTypeNames()) {
            System.out.println("Object type name : " + name);
        }
        System.out.println("-------------------------------");
        // Get InputObjectType Names
        List<String> inputObjectTypeNames = utils.getInputObjectTypeNames();
        for (String inputObjectTypeName: inputObjectTypeNames) {
            System.out.println("Input object type name : " + inputObjectTypeName);
        }
        System.out.println("-------------------------------");
        // Get EnumType Names
        List<String> enumTypeNames = utils.getEnumTypeNames();
        for (String enumTypeName: enumTypeNames) {
            System.out.println("Enum type name : " + enumTypeName);
        }
        System.out.println("-------------------------------");
        // Get ScalarType Names
        for(String name: utils.getScalarTypeNames()) {
            System.out.println("Scalar type name : " + name);
        }
        System.out.println("-------------------------------");

        //// Level 2 - Field Names

        // Get ObjectType Field Names
        List<ObjectTypeDefinition> objectTypeDefinitions = utils.getObjectTypes();
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions) {
            System.out.println("Object type name : " + objectTypeDefinition.getName());
            List<String> objectTypesFieldNames = utils.getObjectTypeFieldNames(objectTypeDefinition);
            for(String name: objectTypesFieldNames) {
                System.out.println("Field name : " + name);
            }
        }
        System.out.println("-------------------------------");
        // Get InputObjectType Field Names
        List<InputObjectTypeDefinition> inputObjectTypeDefinitions = utils.getInputObjectTypes();
        for(InputObjectTypeDefinition inputObjectTypeDefinition: inputObjectTypeDefinitions) {
            System.out.println("Input object type name : " + inputObjectTypeDefinition.getName());
            List<String> inputObjectTypesFieldNames = utils.getInputObjectTypeFieldNames(inputObjectTypeDefinition);
            for(String name: inputObjectTypesFieldNames) {
                System.out.println("Field name : " + name);
            }
        }
        System.out.println("-------------------------------");
        // Get EnumType Field Names
        List<EnumTypeDefinition> enumTypeDefinitions = utils.getEnumTypes();
        for(EnumTypeDefinition enumTypeDefinition: enumTypeDefinitions) {
            System.out.println("Enum type name : " + enumTypeDefinition.getName());
            List<String> enumValueNames = utils.getEnumValueNames(enumTypeDefinition);
            for(String name: enumValueNames) {
                System.out.println("Enum value : " + name);
            }
        }
        System.out.println("-------------------------------");

        //// Level 3 - Field Return Types

        // Get ObjectType Field Types
        List<ObjectTypeDefinition> objectTypeDefinitions1 = utils.getObjectTypes();
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions1) {
            System.out.println("Object type name : " + objectTypeDefinition.getName());
            List<String> objectTypesFieldTypes = utils.getObjectTypeFieldTypes(objectTypeDefinition);
            for(String name: objectTypesFieldTypes) {
                System.out.println("Field Return Type : " + name);
            }
        }
        System.out.println("-------------------------------");
        // Get InputObjectType Field Types
        List<InputObjectTypeDefinition> inputObjectTypeDefinitions1 = utils.getInputObjectTypes();
        for(InputObjectTypeDefinition inputObjectTypeDefinition: inputObjectTypeDefinitions1) {
            System.out.println("Input object type name : " + inputObjectTypeDefinition.getName());
            List<String> inputObjectTypeFieldTypes = utils.getInputObjectTypeFieldTypes(inputObjectTypeDefinition);
            for(String name: inputObjectTypeFieldTypes) {
                System.out.println("Field Return Type : " + name);
            }
        }
        System.out.println("-------------------------------");

        //// Level 4 - Field Names & Field Return Types

        // Get ObjectType Fields Key Value Pairs
        List<ObjectTypeDefinition> objectTypeDefinitions2 = utils.getObjectTypes();
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions2) {
            System.out.println("Object type name : " + objectTypeDefinition.getName());
            Map<String,String> objectTypeFieldKeyValuePairs = utils.getObjectTypeFieldKeyValuePairs(objectTypeDefinition);
            for(String name: objectTypeFieldKeyValuePairs.keySet()) {
                System.out.println("Field name : " + name + " | Field Return Type : " + objectTypeFieldKeyValuePairs.get(name));
            }
        }
        System.out.println("-------------------------------");
        // Get InputObjectType Fields Key Value Pairs
        List<InputObjectTypeDefinition> inputObjectTypeDefinitions2 = utils.getInputObjectTypes();
        for(InputObjectTypeDefinition inputObjectTypeDefinition: inputObjectTypeDefinitions2) {
            System.out.println("Input object type name : " + inputObjectTypeDefinition.getName());
            Map<String,String> inputObjectTypeFieldKeyValuePairs = utils.getInputObjectTypeFieldKeyValuePairs(inputObjectTypeDefinition);
            for(String name: inputObjectTypeFieldKeyValuePairs.keySet()) {
                System.out.println("Field name : " + name + " | Field Return Type : " + inputObjectTypeFieldKeyValuePairs.get(name));
            }
        }
        System.out.println("-------------------------------");

        //// Level 5 - Argument Names & Argument Types

        List<ObjectTypeDefinition> objectTypeDefinitions3 = utils.getObjectTypes();
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions3) {
            System.out.println("Object type name : " + objectTypeDefinition.getName());
            List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
            for (FieldDefinition fieldDefinition: fieldDefinitions) {
                System.out.println("Field name : " + fieldDefinition.getName());
                Map<String,String> inputFieldsKeyValuePairs = utils.getInputFieldsKeyValuePairs(fieldDefinition);
                for(String name: inputFieldsKeyValuePairs.keySet()) {
                    System.out.println("Argument name : " + name + " | Argument type : " + inputFieldsKeyValuePairs.get(name));
                }
            }
        }
        System.out.println("-------------------------------");

        //// Level 6 - Object Type Additional Data

        // Get ObjectType Additional Data
        List<ObjectTypeDefinition> objectTypeDefinitions4 = utils.getObjectTypes();
        for(ObjectTypeDefinition objectTypeDefinition: objectTypeDefinitions4) {
            System.out.println("Object type name : " + objectTypeDefinition.getName());
            Map<String,String> objectTypeFieldKeyValuePairs = utils.getObjectTypeAdditionalData(objectTypeDefinition);
            for(String name: objectTypeFieldKeyValuePairs.keySet()) {
                System.out.println("Key : " + name + " | Value : " + objectTypeFieldKeyValuePairs.get(name));
            }
        }
    }
}
