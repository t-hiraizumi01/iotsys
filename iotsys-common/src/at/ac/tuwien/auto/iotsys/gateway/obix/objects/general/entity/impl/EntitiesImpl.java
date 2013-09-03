/*******************************************************************************
 * Copyright (c) 2013
 * Institute of Computer Aided Automation, Automation Systems Group, TU Wien.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the Institute nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE INSTITUTE AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE INSTITUTE OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * This file is part of the IoTSyS project.
 ******************************************************************************/

package at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.entity.impl;

import java.util.ArrayList;

import obix.Contract;
import obix.List;
import obix.Obj;
import obix.Uri;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.entity.Entities;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.entity.Entity;
import at.ac.tuwien.auto.iotsys.gateway.util.UriEncoder;

public class EntitiesImpl extends List implements Entities
{
	private ArrayList<Entity> entities;

	public EntitiesImpl()
	{
		this.setName("entities");
		this.setIs(new Contract("knx:entities"));
		this.setHref(new Uri("entities"));
		this.setHidden(true);

		this.entities = new ArrayList<Entity>();
	}

	public void addEntity(Entity entity)
	{
		if (entity instanceof Obj)
		{
			entity.setHref(getHref(entity.getDisplayName()));
			this.add((Obj)entity);
			this.add(entity.getReference());
		}
	}

	private Uri getHref(String displayName)
	{
		int count = 1;
		for (Entity e : entities)
		{
			if (UriEncoder.getEscapedUri(e.getDisplayName()).equals(UriEncoder.getEscapedUri(displayName)))
			{
				count++;
			}
		}
		return new Uri(UriEncoder.getEscapedUri(displayName) + "/" + count);
	}
}