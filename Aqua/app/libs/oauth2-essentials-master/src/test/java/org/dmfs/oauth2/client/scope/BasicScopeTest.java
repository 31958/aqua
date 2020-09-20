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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BasicScopeTest
{

    @Test
    public void testIsEmpty()
    {
        assertTrue(new BasicScope().isEmpty());
        assertFalse(new BasicScope("discover").isEmpty());
        assertFalse(new BasicScope("discover", "calendar").isEmpty());
    }


    @Test
    public void testHasToken()
    {
        assertFalse(new BasicScope().hasToken("discover"));
        assertTrue(new BasicScope("discover").hasToken("discover"));
        assertFalse(new BasicScope("discover").hasToken("calendar"));
        assertTrue(new BasicScope("discover", "calendar").hasToken("discover"));
        assertTrue(new BasicScope("discover", "calendar").hasToken("calendar"));
        assertFalse(new BasicScope("discover", "calendar").hasToken("foo"));
    }


    @Test
    public void testToString()
    {
        assertEquals("", new BasicScope().toString());
        assertEquals("discover", new BasicScope("discover").toString());
        assertEquals("discover calendar", new BasicScope("discover", "calendar").toString());
    }

}
