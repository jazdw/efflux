/*
 * Copyright 2010 Bruno de Carvalho
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

package com.biasedbit.efflux.network;

import com.biasedbit.efflux.packet.CompoundControlPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="http://bruno.biasedbit.com/">Bruno de Carvalho</a>
 */
public class ControlHandler extends ChannelInboundHandlerAdapter {

    // constants ------------------------------------------------------------------------------------------------------

    private static final Logger LOG = LoggerFactory.getLogger(ControlHandler.class);

    // internal vars --------------------------------------------------------------------------------------------------

    private final AtomicInteger counter;
    private final ControlPacketReceiver receiver;

    // constructors ---------------------------------------------------------------------------------------------------

    public ControlHandler(ControlPacketReceiver receiver) {
        this.receiver = receiver;
        this.counter = new AtomicInteger();
    }

    // ChannelInboundHandlerAdapter -----------------------------------------------------------------------------------

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        if (message instanceof CompoundControlPacket) {
            this.receiver.controlPacketReceived(ctx.channel().remoteAddress(), (CompoundControlPacket) message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Just log and proceed...
        LOG.error("Caught exception on channel {}.", cause.getCause(), ctx.channel());
    }
    
    // public methods -------------------------------------------------------------------------------------------------

    public int getPacketsReceived() {
        return this.counter.get();
    }
}
