/*
 * Copyright 2016 dmfs GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.oauth2.client.scope;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class StringScopeTest
{
    @Test
    public void testTokenCount() throws Exception
    {
        assertThat(new StringScope("").tokenCount(), is(0));
        assertThat(new StringScope("discover").tokenCount(), is(1));
        assertThat(new StringScope("discover calendar").tokenCount(), is(2));
    }


    @Test
    public void testIsEmpty()
    {
        assertTrue(new StringScope("").isEmpty());
        assertFalse(new StringScope("discover").isEmpty());
        assertFalse(new StringScope("discover calendar").isEmpty());
    }


    @Test
    public void testHasToken()
    {
        assertFalse(new StringScope("").hasToken("discover"));
        assertTrue(new StringScope("discover").hasToken("discover"));
        assertFalse(new StringScope("discover").hasToken("calendar"));
        assertTrue(new StringScope("discover calendar").hasToken("discover"));
        assertTrue(new StringScope("discover calendar").hasToken("calendar"));
        assertFalse(new StringScope("discover calendar").hasToken("foo"));
    }


    @Test
    public void testToString()
    {
        assertEquals("", new StringScope("").toString());
        assertEquals("discover", new StringScope("discover").toString());
        assertEquals("discover calendar", new StringScope("discover calendar").toString());
    }

}
