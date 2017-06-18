package com.heiko.placelocator

Binding binding = new Binding()
GroovyShell shell = new GroovyShell(binding)

shell.setVariable("args", ['48.473127', '35.288328'] as String[]) // in the middle of nowhere
//shell.setVariable("args", ['48.464411', '35.047567'] as String[]) // Grand Plaza
//shell.setVariable("args", ['48.469550', '35.066052'] as String[]) // Dnipro

String result = shell.evaluate(new File("Locator.groovy"))

println()

println result.
        replaceAll("\\{", '\n\t\t {').
        replaceAll("\"name", '\n\n\t"name')