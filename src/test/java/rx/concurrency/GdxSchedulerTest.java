/**
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rx.concurrency;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import rx.functions.Action0;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

public class GdxSchedulerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testInvalidDelayValues() {
        final GdxScheduler scheduler = GdxScheduler.get();
        final Action0 action = mock(Action0.class);

        exception.expect(IllegalArgumentException.class);
        scheduler.createWorker().schedulePeriodically(action, -1L, 100L, TimeUnit.SECONDS);

        exception.expect(IllegalArgumentException.class);
        scheduler.createWorker().schedulePeriodically(action, 100L, -1L, TimeUnit.SECONDS);

        exception.expect(IllegalArgumentException.class);
        scheduler.createWorker().schedulePeriodically(action, 1L + Integer.MAX_VALUE, 100L, TimeUnit.MILLISECONDS);

        exception.expect(IllegalArgumentException.class);
        scheduler.createWorker().schedulePeriodically(action, 100L, 1L + Integer.MAX_VALUE / 1000, TimeUnit.SECONDS);
    }

}
