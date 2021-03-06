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

package me.neilellis.dollar.docs;

import com.google.common.io.CharStreams;
import me.neilellis.dollar.script.DollarParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;

public class DocTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDocs() throws Exception {
        new DollarParser().parseMarkdown(CharStreams.toString(new InputStreamReader(getClass().getResourceAsStream("/pages/scripting.md"))));
    }
}