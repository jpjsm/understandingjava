package com.booleanexpressioncalculator;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {

        List<StringPair> uData = new ArrayList<>(List.of(
			new StringPair("Read", "4246b7a7-1e49-40dd-8fa6-7aebdd70f34d"),
			new StringPair("Update", "44379cdf-2521-42f9-904e-c31d7244ed6c"),
			new StringPair("Insert", "d31aeb5b-e357-4a50-9a0f-3dda18b632ff"),
			new StringPair("Delete", "aa1ee703-e889-4b0d-8fa3-a39118a3443e"),
			new StringPair("Create", "d341c9da-1f00-414b-9d02-d109f01d4f70"),
			new StringPair("Alter", "b0e88dc8-a852-4e32-b4f7-42da2bd170fe"),
			new StringPair("Execute", "a5b2a69b-d7d5-46bf-bce9-d1cdaca88f54"),
			new StringPair("Take_Ownership", "6c639a12-53fd-4575-abfb-0bd61913c2af"),
			new StringPair("Impersonate", "eb4caa4d-7931-4e9e-b223-59b60b827461")
        ));
        Universe u = new Universe();
        try {
            u.TryAdd(uData, false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        List<String> expressions = new ArrayList<>(List.of(
            "Read, Update, Insert, Delete, Create, Alter, Execute, Take_Ownership, Impersonate",
            "Read | Update | Insert | Delete | Create | Alter | Execute | Take_Ownership | Impersonate",
            "Take_Ownership",
            "!Take_Ownership",
            "BBBBBBBB-BBBB-BBBB-BBBB-BBBBBBBBBBBB",
            "Update&Delete&Alter",
            "!(Update&Delete&Alter)",
            "(Update | Insert) & !Execute)",
            "Update+Delete*Alter",
            "!(mañana * (pingüino,árbol,garçon))"
        ));

        List<Context> ctxs = new ArrayList<>();
    
        ctxs.add(new Context(new ArrayList<>(List.of("44379cdf-2521-42f9-904e-c31d7244ed6c")),u)); // Update
        ctxs.add(new Context(new ArrayList<>(List.of("aa1ee703-e889-4b0d-8fa3-a39118a3443e")),u)); // Delete
        ctxs.add(new Context(new ArrayList<>(List.of("b0e88dc8-a852-4e32-b4f7-42da2bd170fe")),u)); // Alter
        ctxs.add(new Context(new ArrayList<>(List.of("6c639a12-53fd-4575-abfb-0bd61913c2af")),u)); // Take_Ownership
        ctxs.add(new Context(new ArrayList<>(List.of("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")),u)); // Not in universe
        ctxs.add(new Context(new ArrayList<>(List.of("44379cdf-2521-42f9-904e-c31d7244ed6c", "aa1ee703-e889-4b0d-8fa3-a39118a3443e")), u, true));
        ctxs.add(new Context(new ArrayList<>(List.of("aa1ee703-e889-4b0d-8fa3-a39118a3443e", "b0e88dc8-a852-4e32-b4f7-42da2bd170fe")), u, true));
        ctxs.add(new Context(new ArrayList<>(List.of("b0e88dc8-a852-4e32-b4f7-42da2bd170fe", "6c639a12-53fd-4575-abfb-0bd61913c2af")), u, true));
        ctxs.add(new Context(new ArrayList<>(List.of("6c639a12-53fd-4575-abfb-0bd61913c2af","bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")), u, true));
        ctxs.add(new Context(new ArrayList<>(List.of("44379cdf-2521-42f9-904e-c31d7244ed6c", "b0e88dc8-a852-4e32-b4f7-42da2bd170fe","bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")), u, true));


       
        try {
            
            for (String expression : expressions) {
                BooleanParser bp = new BooleanParser(expression, ctxs, u);
                switch (bp.Status()) {
                    case SUCCESSFULPARSE:
                            for (String result : bp.ContextEvaluationResults()) {
                                System.out.println(result);
                            }

                            System.out.println();
                            System.out.println("===================================================================================================");
                            System.out.println();
                        break;
                
                    default:
                        System.out.printf("Status: %s, error msg: %s", bp.Status(), bp.ErrorMessages());
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
