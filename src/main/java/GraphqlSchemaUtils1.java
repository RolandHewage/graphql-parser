import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.language.Definition;
import graphql.language.Document;
import graphql.language.EnumTypeDefinition;
import graphql.language.EnumValueDefinition;
import graphql.language.FieldDefinition;
import graphql.language.InputObjectTypeDefinition;
import graphql.language.InputValueDefinition;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ScalarTypeDefinition;
import graphql.parser.Parser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphqlSchemaUtils1 {

    private Document document;

    public GraphqlSchemaUtils1(String graphqlSchema) {
        try {
//            ClassLoader classLoader = this.getClass().getClassLoader();
//            final File graphQlSchema = new File(Objects.requireNonNull(classLoader.getResource(graphqlSchema)).getFile());
//            Reader sdl = new FileReader(graphQlSchema);

            // Need `com.google.guava:guava` dependency
            URL url = Resources.getResource(graphqlSchema);
            String sdl = Resources.toString(url, Charsets.UTF_8);

            Parser parser = new Parser();
            this.document = parser.parseDocument(sdl);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<String> getDefinitions() {
        List<String> definitions = new ArrayList<>();
        List<Definition> definitionList = this.document.getDefinitions();
        if (definitionList.size() >= 1) {
            for(Definition definition: definitionList) {
                definitions.add(definition.toString());
            }
        }
        return definitions;
    }

    //// Level 1 - Names

    public List<String> getObjectTypeNames() {
        List<String> objectNames = new ArrayList<>();
        List<ObjectTypeDefinition> objectTypeDefinitions = this.document.getDefinitionsOfType(ObjectTypeDefinition.class);
        if (objectTypeDefinitions.size() >= 1) {
            for(ObjectTypeDefinition definition: objectTypeDefinitions) {
                objectNames.add(definition.getName());
            }
        }
        return objectNames;
    }

    public List<String> getInputObjectTypeNames() {
        List<String> inputObjectTypeNames = new ArrayList<>();
        List<InputObjectTypeDefinition> inputObjectTypes = this.document.getDefinitionsOfType(InputObjectTypeDefinition.class);
        if (inputObjectTypes.size() >= 1) {
            for(InputObjectTypeDefinition inputObjectType: inputObjectTypes) {
                inputObjectTypeNames.add(inputObjectType.getName());
            }
        }
        return inputObjectTypeNames;
    }

    public List<String> getEnumTypeNames() {
        List<String> enumTypeNames = new ArrayList<>();
        List<EnumTypeDefinition> enumTypes = this.document.getDefinitionsOfType(EnumTypeDefinition.class);
        if (enumTypes.size() >= 1) {
            for(EnumTypeDefinition enumType: enumTypes) {
                enumTypeNames.add(enumType.getName());
            }
        }
        return enumTypeNames;
    }

    public List<String> getScalarTypeNames() {
        List<String> scalarNames = new ArrayList<>();
        List<ScalarTypeDefinition> scalarTypeDefinitions = this.document.getDefinitionsOfType(ScalarTypeDefinition.class);
        if (scalarTypeDefinitions.size() >= 1) {
            for(ScalarTypeDefinition definition: scalarTypeDefinitions) {
                scalarNames.add(definition.getName());
            }
        }
        return scalarNames;
    }

    //// Level 2 - Field Names

    public List<ObjectTypeDefinition> getObjectTypes() {
        List<ObjectTypeDefinition> objectTypes = this.document.getDefinitionsOfType(ObjectTypeDefinition.class);
        return objectTypes;
    }

    public List<String> getObjectTypeFieldNames(ObjectTypeDefinition objectTypeDefinition) {
        List<String> objectTypeFieldNames = new ArrayList<>();
        List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
        for (FieldDefinition fieldDefinition: fieldDefinitions) {
            objectTypeFieldNames.add(fieldDefinition.getName());
        }
        return objectTypeFieldNames;
    }

    public List<InputObjectTypeDefinition> getInputObjectTypes() {
        List<InputObjectTypeDefinition> inputObjectTypes = this.document.getDefinitionsOfType(InputObjectTypeDefinition.class);
        return inputObjectTypes;
    }

    public List<String> getInputObjectTypeFieldNames(InputObjectTypeDefinition inputObjectTypeDefinition) {
        List<String> inputObjectTypesFieldNames = new ArrayList<>();
        List<InputValueDefinition> inputValueDefinitions = inputObjectTypeDefinition.getInputValueDefinitions();
        for (InputValueDefinition inputValueDefinition: inputValueDefinitions) {
            inputObjectTypesFieldNames.add(inputValueDefinition.getName());
        }
        return inputObjectTypesFieldNames;
    }

    public List<EnumTypeDefinition> getEnumTypes() {
        List<EnumTypeDefinition> enumTypes = this.document.getDefinitionsOfType(EnumTypeDefinition.class);
        return enumTypes;
    }

    public List<String> getEnumValueNames(EnumTypeDefinition enumTypeDefinition) {
        List<String> enumValueNames = new ArrayList<>();
        List<EnumValueDefinition> enumValueDefinitions = enumTypeDefinition.getEnumValueDefinitions();
        for (EnumValueDefinition enumValueDefinition: enumValueDefinitions) {
            enumValueNames.add(enumValueDefinition.getName());
        }
        return enumValueNames;
    }

    //// Level 3 - Field Return Types

    public List<String> getObjectTypeFieldTypes(ObjectTypeDefinition objectTypeDefinition) {
        List<String> objectTypeFieldTypes = new ArrayList<>();
        List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
        for (FieldDefinition fieldDefinition: fieldDefinitions) {
            objectTypeFieldTypes.add(fieldDefinition.getType().toString());
        }
        return objectTypeFieldTypes;
    }

    public List<FieldDefinition> getObjectTypeFields(ObjectTypeDefinition objectTypeDefinition) {
        List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
        return fieldDefinitions;
    }

    public String getObjectTypeFieldType(FieldDefinition fieldDefinition) {
        return fieldDefinition.getType().toString();
    }

    public List<String> getInputObjectTypeFieldTypes(InputObjectTypeDefinition inputObjectTypeDefinition) {
        List<String> inputObjectTypeFieldTypes = new ArrayList<>();
        List<InputValueDefinition> inputValueDefinitions = inputObjectTypeDefinition.getInputValueDefinitions();
        for (InputValueDefinition inputValueDefinition: inputValueDefinitions) {
            inputObjectTypeFieldTypes.add(inputValueDefinition.getType().toString());
        }
        return inputObjectTypeFieldTypes;
    }

    public List<InputValueDefinition> getInputObjectTypeFields(InputObjectTypeDefinition inputObjectTypeDefinition) {
        List<InputValueDefinition> inputValueDefinitions = inputObjectTypeDefinition.getInputValueDefinitions();
        return inputValueDefinitions;
    }

    public String getInputObjectTypeFieldType(InputValueDefinition inputValueDefinition) {
        return inputValueDefinition.getType().toString();
    }

    //// Level 4 - Field Names & Field Return Types

    public Map<String,String> getObjectTypeFieldKeyValuePairs(ObjectTypeDefinition objectTypeDefinition) {
        Map<String,String> objectTypeFieldKeyValuePairs = new HashMap<>();
        List<FieldDefinition> fieldDefinitions = objectTypeDefinition.getFieldDefinitions();
        for (FieldDefinition fieldDefinition: fieldDefinitions) {
            objectTypeFieldKeyValuePairs.put(fieldDefinition.getName(), fieldDefinition.getType().toString());
        }
        return objectTypeFieldKeyValuePairs;
    }

    public Map<String,String> getInputObjectTypeFieldKeyValuePairs(InputObjectTypeDefinition inputObjectTypeDefinition) {
        Map<String,String> inputObjectTypeFieldKeyValuePairs = new HashMap<>();
        List<InputValueDefinition> inputValueDefinitions = inputObjectTypeDefinition.getInputValueDefinitions();
        for (InputValueDefinition inputValueDefinition: inputValueDefinitions) {
            inputObjectTypeFieldKeyValuePairs.put(inputValueDefinition.getName(), inputValueDefinition.getType().toString());
        }
        return inputObjectTypeFieldKeyValuePairs;
    }

    //// Level 5 - Argument Names & Argument Types

    public Map<String,String> getInputFieldsKeyValuePairs(FieldDefinition fieldDefinition) {
        Map<String,String> inputFieldsKeyValuePairs = new HashMap<>();
        List<InputValueDefinition> inputValueDefinitions = fieldDefinition.getInputValueDefinitions();
        for (InputValueDefinition inputValueDefinition: inputValueDefinitions) {
            inputFieldsKeyValuePairs.put(inputValueDefinition.getName(), inputValueDefinition.getType().toString());
        }
        return inputFieldsKeyValuePairs;
    }

    //// Level 6 - Object Type Additional Data

    public Map<String,String> getObjectTypeAdditionalData(ObjectTypeDefinition objectTypeDefinition) {
        Map<String,String> objectTypeAdditionalData = objectTypeDefinition.getAdditionalData();
        return objectTypeAdditionalData;
    }
}
