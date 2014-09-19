package com.google.javascript.jscomp;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

public class SuppressCollector {
    static String DIAGNOSTIC_GROUP_NAMES =
              "accessControls, ambiguousFunctionDecl, checkEventfulObjectDisposal, " +
              "checkRegExp, checkStructDictInheritance, checkTypes, checkVars, " +
              "const, constantProperty, deprecated, duplicateMessage, es3, " +
              "es5Strict, externsValidation, fileoverviewTags, globalThis, " +
              "inferredConstCheck, " +
              "internetExplorerChecks, invalidCasts, misplacedTypeAnnotation, " +
              "missingGetCssName, missingProperties, " +
              "missingProvide, missingRequire, missingReturn, " +
              "newCheckTypes, nonStandardJsDocs, reportUnknownTypes, suspiciousCode, " +
              "strictModuleDepCheck, typeInvalidation, " +
              "undefinedNames, undefinedVars, unknownDefines, uselessCode, " +
              "useOfGoogBase, visibility";
    
    public static void main(String[] args) {
        Collection entries = new ArrayList<SuppressEntry>();
        DiagnosticGroups diagnosticGroups = new DiagnosticGroups();
        String[] groupNames = DIAGNOSTIC_GROUP_NAMES.split(", ");
        for (String groupName: groupNames) {
            DiagnosticGroup x = diagnosticGroups.forName(groupName);
            for (DiagnosticType y : x.getTypes()) {
                Object[] obj = {};
                String formatMessage = y.format.format(obj); 
                entries.add(new SuppressEntry(groupName, formatMessage));
            }
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(entries));
    }
}

class SuppressEntry {
    String group;
    String formatMessage;
    public SuppressEntry(String group, String formatMessage) {
        super();
        this.group = group;
        this.formatMessage = formatMessage;
    }
}