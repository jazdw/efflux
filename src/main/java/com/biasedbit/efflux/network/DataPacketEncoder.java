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

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

import com.biasedbit.efflux.packet.DataPacket;

import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * @author <a href="http://bruno.biasedbit.com/">Bruno de Carvalho</a>
 */
@ChannelHandler.Sharable
public class DataPacketEncoder extends MessageToMessageEncoder<AddressedEnvelope<DataPacket, SocketAddress>> {

    // constructors ---------------------------------------------------------------------------------------------------

    private DataPacketEncoder() {
    }

    // public static methods ------------------------------------------------------------------------------------------

    public static DataPacketEncoder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // OneToOneEncoder ------------------------------------------------------------------------------------------------

	@Override
	protected void encode(ChannelHandlerContext ctx, AddressedEnvelope<DataPacket, SocketAddress> msg,
			List<Object> out) throws Exception {
		DataPacket packet = msg.content();
		
		if (packet.getDataSize() == 0) {
			return;
		}
		
		out.add(new DatagramPacket(packet.encode(), (InetSocketAddress) msg.recipient()));
	}
    
    // private classes ------------------------------------------------------------------------------------------------

    private static final class InstanceHolder {
        private static final DataPacketEncoder INSTANCE = new DataPacketEncoder();
    }
}
