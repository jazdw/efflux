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
import com.biasedbit.efflux.packet.ControlPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.DatagramPacket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

/**
 * @author <a href="http://bruno.biasedbit.com/">Bruno de Carvalho</a>
 */
@ChannelHandler.Sharable
public class ControlPacketEncoder extends ChannelOutboundHandlerAdapter {

    // constants ------------------------------------------------------------------------------------------------------

    protected static final Logger LOG = LoggerFactory.getLogger(ControlPacketEncoder.class);

    // constructors ---------------------------------------------------------------------------------------------------

    private ControlPacketEncoder() {
    }

    // public static methods ------------------------------------------------------------------------------------------

    public static ControlPacketEncoder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // ChannelOutboundHandlerAdapter ---------------------------------------------------------------------------------------

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        try {
        	if (msg instanceof AddressedEnvelope) {
        		@SuppressWarnings("unchecked")
				AddressedEnvelope<Object, SocketAddress> envelope = (AddressedEnvelope<Object, SocketAddress>) msg;
        		
        		Object packet = envelope.content();
        		
        		if (packet instanceof ControlPacket) {
                	ctx.writeAndFlush(new DatagramPacket(((ControlPacket) packet).encode(),
                			(InetSocketAddress) envelope.recipient()), promise);
                } else if (packet instanceof CompoundControlPacket) {
                    List<ControlPacket> packets = ((CompoundControlPacket) packet).getControlPackets();
                    ByteBuf[] buffers = new ByteBuf[packets.size()];
                    for (int i = 0; i < buffers.length; i++) {
                        buffers[i] = packets.get(i).encode();
                    }
                    
                    ByteBuf compoundBuffer = Unpooled.wrappedBuffer(buffers);
                    ctx.writeAndFlush(new DatagramPacket(compoundBuffer, (InetSocketAddress) envelope.recipient()), promise);
                }
        	}
        } catch (Exception e1) {
            LOG.error("Failed to encode compound RTCP packet to send.", e1);
        }

        // Otherwise do nothing.
    }

    // private classes ------------------------------------------------------------------------------------------------

    private static final class InstanceHolder {
        private static final ControlPacketEncoder INSTANCE = new ControlPacketEncoder();
    }
}
