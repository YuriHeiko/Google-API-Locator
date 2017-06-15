package com.heiko.placelocator

Binding binding = new Binding();
GroovyShell shell = new GroovyShell(binding);

shell.setVariable("args", ['48.464411', '35.047567'] as String[])

String result = shell.evaluate(new File("Locator.groovy"));

println result.
        replaceAll("\\{", '\n\t\t {').
        replaceAll("\"name", '\n\t"name')