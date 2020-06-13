/*
 * Copyright 2019-2020 Shaun Laurens.
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

package com.aeroncookbook.cluster.rfq;

import com.aeroncookbook.cluster.rfq.demuxer.InstrumentDemuxer;
import com.aeroncookbook.cluster.rfq.gen.AddInstrumentCommand;
import com.aeroncookbook.cluster.rfq.instruments.Instruments;
import org.agrona.ExpandableDirectByteBuffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstrumentTests
{
    public static final String CUSIP_0001 = "CUSIP0001";
    public static final String CUSIP_0002 = "CUSIP0002";
    final ExpandableDirectByteBuffer addInstBuffer = new ExpandableDirectByteBuffer(AddInstrumentCommand.BUFFER_LENGTH);

    @Test
    public void canAddInstrument()
    {
        final Instruments underTest = new Instruments();
        final InstrumentDemuxer demuxer = new InstrumentDemuxer(underTest);

        final AddInstrumentCommand instrumentCommand = new AddInstrumentCommand();
        instrumentCommand.setUnderlyingBuffer(addInstBuffer, 0);
        instrumentCommand.writeHeader();
        instrumentCommand.writeCusip(CUSIP_0001);
        instrumentCommand.writeMinLevel(10);
        instrumentCommand.writeSecurityId(1);

        demuxer.onFragment(addInstBuffer, 0, AddInstrumentCommand.BUFFER_LENGTH, null);

        assertTrue(underTest.knownCusip(CUSIP_0001));
        assertFalse(underTest.knownCusip(CUSIP_0002));
        assertEquals(10, underTest.getMinValue(CUSIP_0001));
        assertEquals(0, underTest.getMinValue(CUSIP_0002));
    }
}
