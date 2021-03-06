/*
 * Copyright (c) 2014 Neil Ellis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.neilellis.dollar.script;

import com.google.common.io.CharStreams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;

public class ParserTest {

    private boolean parallel;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testArrays() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_arrays.ds"), parallel);
    }

    @Test
    public void testBasics1() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test1.ds"), parallel);
    }

    @Test
    public void testBasics3() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test3.ds"), parallel);
    }

    @Test
    public void testBuiltins() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_builtins.ds"), parallel);
    }

    @Test
    public void testCasting() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_casting.ds"), parallel);
    }

    @Test
    public void testConcurrency() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_concurrency.ds"), parallel);
    }

    @Test
    public void testControlFlow() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_control_flow.ds"), parallel);
    }

    @Test
    public void testDate() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_date.ds"), parallel);
    }

    @Test
    public void testFix() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_fix.ds"), parallel);
    }

    @Test
    public void testIteration() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_iteration.ds"), parallel);
    }

    @Test
    public void testJava() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_java.ds"), parallel);
    }

    @Test
    public void testLogic() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_logic.ds"), parallel);
    }

    @Test
    public void testMarkdown1() throws Exception {
        new DollarParser().parseMarkdown(
                CharStreams.toString(new InputStreamReader(getClass().getResourceAsStream("/test1.md"))));
    }

    @Test
    public void testModules() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_modules.ds"), parallel);
    }

    @Test
    public void testNumeric() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_numeric.ds"), parallel);
    }

    @Test
    public void testParameters() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_parameters.ds"), parallel);
    }

    @Test
    public void testRanges() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_ranges.ds"), parallel);
    }

    @Test
    public void testReactive() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_reactive.ds"), parallel);
    }

    @Test
    public void testRedis() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_redis.ds"), parallel);
    }

    @Test
    public void testStrings() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_strings.ds"), parallel);
    }

    @Test
    public void testURIs() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_uris.ds"), parallel);
    }

    @Test
    public void testVariables() throws Exception {
        new DollarParser().parse(getClass().getResourceAsStream("/test_variables.ds"), parallel);
    }
}