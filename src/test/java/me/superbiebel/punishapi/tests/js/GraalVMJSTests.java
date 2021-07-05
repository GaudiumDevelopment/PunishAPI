package me.superbiebel.punishapi.tests.js;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class GraalVMJSTests {
    
    @Test
    void basicJSTest() {
        Context polyglot = Context.create();
        Value array = polyglot.eval("js", "1+1");
        int result = array.asInt();
        assertSame(2,result);
    }
    
}
